package com.enstratus.api.client.httpclient;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_BASEURL;
import static com.enstratus.api.utils.EnstratusConstants.USER_AGENT;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
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
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enstratus.api.Details;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.RequestSignature;
import com.enstratus.api.actions.Action;
import com.enstratus.api.client.AbstractEnstratusClient;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusResult;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class EnstratusHttpClient extends AbstractEnstratusClient implements EnstratusClient {

    public EnstratusHttpClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    private static final Logger log = LoggerFactory.getLogger(EnstratusHttpClient.class);

    private HttpClient httpClient = new DefaultHttpClient();

    public EnstratusResult execute(Action action) {
        try {
            URL base = new URL(DEFAULT_BASEURL);
            URI uri = new URI(base.getProtocol(), base.getHost(), action.getURI(), null);

            HttpUriRequest request = constructHttpMethod(action.getRestMethodName(), uri, action.getQueryParameters(),
                    action.getBody());


            addHeadersToRequest(request, action);

            HttpResponse response = httpClient.execute(request);

            // If head method returns no content, it is added according to
            // response code thanks to https://github.com/hlassiege
            if (request.getMethod().equalsIgnoreCase("HEAD")) {
                if (response.getEntity() == null) {
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        response.setEntity(new StringEntity("{\"ok\" : true, \"found\" : true}"));
                    } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                        response.setEntity(new StringEntity("{\"ok\" : false, \"found\" : false}"));
                    }
                }
            }
            return deserializeResponse(response, action.getPathToResult());
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private void addHeadersToRequest(HttpUriRequest request, Action action) {
        Map<String, String> headers = createHeaders(getAccessKey(), getSecretKey(), action);
        for (Entry<String, String> header : headers.entrySet()) {
            request.addHeader(header.getKey(), header.getValue());
        }
    }

    protected HttpUriRequest constructHttpMethod(HttpMethod httpMethod, URI uri,
            List<NameValuePair> queryParameters, Map<String, Object> body) throws Exception {
        switch (httpMethod) {
            case GET:
                if (queryParameters != null && !queryParameters.isEmpty()) {
                    uri = new URIBuilder(uri).setQuery(URLEncodedUtils.format(queryParameters, "UTF-8")).build();
                }
                HttpGet httpGet = new HttpGet(uri);
                log.debug(httpGet.toString());
                return httpGet;
            case POST:
                HttpPost httpPost = new HttpPost(uri);
                if (body != null) httpPost.setEntity(new StringEntity(createJsonStringEntity(body), "UTF-8"));
                log.debug(httpPost.toString());
                return httpPost;
            case PUT:
                HttpPut httpPut = new HttpPut(uri);
                if (body != null) httpPut.setEntity(new StringEntity(createJsonStringEntity(body), "UTF-8"));
                log.debug(httpPut.toString());                
                return httpPut;
            case DELETE:
                HttpDelete httpDelete = new HttpDelete(uri);
                log.debug(httpDelete.toString());                
                return httpDelete;
            case HEAD:
                HttpHead httpHead = new HttpHead(uri);
                log.debug(httpHead.toString());                
                return httpHead;
            default:
                throw new IllegalStateException("Unknown HTTP method: " + httpMethod);                
        }
    }

    
    private String createJsonStringEntity(Map<String, Object> data) throws JsonGenerationException, JsonMappingException, IOException {
      return new ObjectMapper().writeValueAsString(data);
    }

    private EnstratusResult deserializeResponse(HttpResponse response, String pathToResult) throws IOException {
        String jsonString = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity());
        return createNewEnstratusResult(jsonString, response.getStatusLine(), pathToResult);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    public Map<String, String> createHeaders(String accessKey, String secretKey, Action action) {
        Map<String, String> headers = Maps.newLinkedHashMap();
        headers.put("User-Agent", USER_AGENT);
        headers.put("Accept", "application/json");
        String timestamp = Long.toString(System.currentTimeMillis());
        String toSign = Joiner.on(":").join(ImmutableList.of(accessKey, action.getRestMethodName().toString(), action.getURI(), timestamp, USER_AGENT));
        String signature;
        try {
            signature = RequestSignature.sign(secretKey, toSign);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        headers.put("x-es-details", Details.BASIC.toString());
        headers.put("x-es-with-perms", "false");
        headers.put("x-esauth-access", accessKey);
        headers.put("x-esauth-signature", signature);
        headers.put("x-esauth-timestamp", timestamp);
        return headers;
    }

}