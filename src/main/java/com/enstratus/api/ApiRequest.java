package com.enstratus.api;

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
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableList;

/**
 * Represents one request. Creates a new HttpClient for every call: this is
 * inefficient for large numbers of simultaneous calls.
 */
public class ApiRequest {

    private final String path;
    private final String accessKey;
    private final String secretKey;
    private final String userAgent;
    private final boolean json;
    private final Details details;
    private final HttpMethod method;
    private final HttpUriRequest request;
    
    /**
     * @param method
     *            GET, POST, PUT, DELETE, HEAD
     * @param apiCall
     *            call e.g. "geography/Cloud"
     * @param version
     *            version e.g. "2012-02-29"
     * @param baseUrl
     *            "https://api.enstratus.com"
     * @param accessKey
     *            access key
     * @param secretKey
     *            raw secret key
     * @param userAgent
     *            desired user agent, may be null for default
     * @param json
     *            true if json is desired, false for xml
     * @param details
     *            'none', 'basic', or 'extended'
     * @param requestBody
     *            POST or PUT may include request body, or null
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ApiRequest(HttpMethod method, String apiCall, String version, String baseUrl, String accessKey,
            String secretKey, String userAgent, boolean json, Details details, String requestBody, List<NameValuePair> queryParams )
            throws MalformedURLException, URISyntaxException {
        this.path = "/api/enstratus/" + checkNotNull(version, "version") + '/' + checkNotNull(apiCall, "apiCall");
        this.accessKey = checkNotNull(accessKey, "accessKey");
        this.secretKey = checkNotNull(secretKey, "secretKey");
        this.userAgent = checkNotNull(userAgent, "userAgent");
        this.json = checkNotNull(json, "json");
        this.details = checkNotNull(details, "details");
        this.method = methodCheck(method);
        URL base = new URL(baseUrl);
        URI uri = new URI(base.getProtocol(), base.getHost(), path, requestBody, null);

        try {
            this.request = createRequest(method, uri, requestBody, queryParams);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * Returns call result (including error statuses) or throws an exception for
     * serious issues
     * 
     * @return result
     */
    public HttpResponse call() throws Exception {
        final DefaultHttpClient httpclient = new DefaultHttpClient();
        final String timestamp = Long.toString(System.currentTimeMillis());
        final String toSign = Joiner.on(":").join(
                ImmutableList.of(accessKey, method.toString(), path, timestamp, userAgent));
        final String signature = RequestSignature.sign(secretKey.getBytes(), toSign);
        addHeaders(httpclient, signature, timestamp);
        return httpclient.execute(request);
    }

    public String getUrl() {
        return request.getURI().toASCIIString();
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

    private static HttpUriRequest createRequest(HttpMethod httpMethod, URI uri, String body, List<NameValuePair> queryParameters) throws UnsupportedEncodingException, URISyntaxException {
        switch (httpMethod) {
            case GET:
                if(queryParameters != null && !queryParameters.isEmpty()) {
                    uri = new URIBuilder(uri).setQuery(URLEncodedUtils.format(queryParameters, "UTF-8")).build();
                }
                return new HttpGet(uri);
            case POST:
                final HttpPost post = new HttpPost(uri);
                if (body != null) {
                    post.setEntity(new StringEntity(body));
                }
            /*
             * StringRequestEntity requestEntity = new StringRequestEntity(JSON_STRING, "application/json", "UTF-8");
             */
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

    private static HttpMethod methodCheck(HttpMethod val) {
        if (val == null) {
            throw new IllegalArgumentException("no http method");
        }
        return val;
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
        ToStringHelper toStringHelper = Objects.toStringHelper(this).addValue(this.request.getMethod())
                .addValue(this.request.getURI().toASCIIString());
        toStringHelper.addValue(this.request.getProtocolVersion());
        return toStringHelper.toString();
    }

}
