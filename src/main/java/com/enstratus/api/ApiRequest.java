package com.enstratus.api;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_BASEURL;
import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;
import static com.enstratus.api.utils.EnstratusConstants.USER_AGENT;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

/**
 * Represents one request to Enstratus API
 */
public class ApiRequest {

    private final HttpMethod method;
    private final String apiCall;
    private final String accessKey;
    private final String secretKey;
    private final String baseUrl;
    private final String version;
    private final String userAgent;
    private final boolean json;
    private final Details details;

    final DefaultHttpClient httpclient = new DefaultHttpClient();

    public ApiRequest(HttpMethod method, String apiCall, String accessKey, String secretKey, String baseUrl,
            String version, String userAgent, boolean json, Details details) throws MalformedURLException,
            URISyntaxException {
        this.method = checkNotNull(method, "HTTP method");
        this.apiCall = checkNotNull(apiCall, "apiCall");
        this.accessKey = checkNotNull(accessKey, "accessKey");
        this.secretKey = checkNotNull(secretKey, "secretKey");
        this.baseUrl = checkNotNull(baseUrl, "baseUrl");
        this.version = checkNotNull(version, "version");
        this.userAgent = checkNotNull(userAgent, "userAgent");
        this.json = checkNotNull(json, "json");
        this.details = checkNotNull(details, "details");
    }

    public ApiRequest(HttpMethod method, String apiCall, String accessKey, String secretKey)
            throws MalformedURLException, URISyntaxException {
        this(method, apiCall, accessKey, secretKey, DEFAULT_BASEURL, DEFAULT_VERSION, USER_AGENT, true, Details.BASIC);
    }

    public HttpResponse call(String requestBody, List<NameValuePair> queryParams) throws Exception {
        String path = String.format("/api/enstratus/%s/%s", version, apiCall);
        URL base = new URL(baseUrl);
        URI uri = new URI(base.getProtocol(), base.getHost(), path, null);
        HttpUriRequest request;
        try {
            request = createRequest(method, uri, requestBody, queryParams);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        String timestamp = Long.toString(System.currentTimeMillis());
        String toSign = Joiner.on(":").join(ImmutableList.of(accessKey, method.toString(), path, timestamp, userAgent));
        String signature = RequestSignature.sign(secretKey, toSign);
        addHeaders(httpclient, signature, timestamp);
        return httpclient.execute(request);
    }

    public HttpResponse call() throws Exception {
        return call(null, null);
    }

    public HttpResponse call(List<NameValuePair> queryParams) throws Exception {
        return call(null, queryParams);
    }

    public HttpResponse call(String requestBody) throws Exception {
        return call(requestBody, null);
    }

    // -----------------------------------------------------------------------------------------
    // IMPL
    // -----------------------------------------------------------------------------------------

    private void addHeaders(DefaultHttpClient httpclient, final String signature, final String timestamp) {
        httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                if (userAgent != null) {
                    final Header[] uaHeaders = request.getHeaders("User-Agent");
                    for (Header uaHeader : uaHeaders) {
                        request.removeHeader(uaHeader);
                    }
                    request.addHeader("User-Agent", userAgent);
                }
                if (json) {
                    request.addHeader("Accept", "application/json");
                } else {
                    request.addHeader("Accept", "application/xml");
                }
                request.addHeader("x-es-details", detailsCheck(details));
                request.addHeader("x-es-with-perms", "false");
                request.addHeader("x-esauth-access", accessKey);
                request.addHeader("x-esauth-signature", signature);
                request.addHeader("x-esauth-timestamp", timestamp);
            }
        });
    }

    private static HttpUriRequest createRequest(HttpMethod httpMethod, URI uri, String body,
            List<NameValuePair> queryParameters) throws UnsupportedEncodingException, URISyntaxException {
        switch (httpMethod) {
        case GET:
            if (queryParameters != null && !queryParameters.isEmpty()) {
                uri = new URIBuilder(uri).setQuery(URLEncodedUtils.format(queryParameters, "UTF-8")).build();
            }
            return new HttpGet(uri);
        case POST:
            final HttpPost post = new HttpPost(uri);
            if (body != null) {
                post.setEntity(new StringEntity(body));
            }
            return post;
        case PUT:
            final HttpPut put = new HttpPut(uri);
            if (body != null) {
                put.setEntity(new StringEntity(body));
            }
            return put;
        case DELETE:
            return new HttpDelete(uri);
        case HEAD:
            return new HttpHead(uri);
        default:
            throw new IllegalStateException("Unknown HTTP method: " + httpMethod);
        }
    }

    private static String detailsCheck(Details details) {
        switch (details) {
        case NONE:
            return "none";
        case BASIC:
            return "basic";
        case EXTENDED:
            return "extended";

        default:
            return "none";
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.method).addValue(this.baseUrl).addValue(this.apiCall)
                .toString();
    }

}
