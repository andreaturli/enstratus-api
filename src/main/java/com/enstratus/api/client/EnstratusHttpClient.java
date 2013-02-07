package com.enstratus.api.client;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_BASEURL;
import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
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

import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnstratusHttpClient extends AbstractEnstratusClient implements EnstratusClient {

    private static final Logger log = LoggerFactory.getLogger(EnstratusHttpClient.class);

    private HttpClient httpClient = new DefaultHttpClient();

    public EnstratusResult execute(Action action) throws Exception {

        URL base = new URL(DEFAULT_BASEURL);
        URI uri = new URI(base.getProtocol(), base.getHost(), action.getURI(), null);
        
        HttpUriRequest request = constructHttpMethod(action.getRestMethodName(), uri, action.getQueryParameters(), action.getBody());
        
        // add headers added to action
        if (!action.getHeaders().isEmpty()) {
            for (Entry<String, String> header: action.getHeaders().entrySet()) {
                    request.addHeader(header.getKey(), header.getValue());
            }
        }
        
        HttpResponse response = httpClient.execute(request);

        // If head method returns no content, it is added according to response code thanks to https://github.com/hlassiege
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
    }

    protected HttpUriRequest constructHttpMethod(HttpMethod httpMethod, URI uri,
            List<NameValuePair> queryParameters, String body) throws Exception {
        switch (httpMethod) {
            case GET:
                log.debug("GET method created based on client request");
                if (queryParameters != null && !queryParameters.isEmpty()) {
                    uri = new URIBuilder(uri).setQuery(URLEncodedUtils.format(queryParameters, "UTF-8")).build();
                }
                return new HttpGet(uri);
            case POST:
                HttpPost httpPost = new HttpPost(uri);
                log.debug("POST method created based on client request");
                if (body != null) httpPost.setEntity(new StringEntity(createJsonStringEntity(body), "UTF-8"));
                return httpPost;
            case PUT:
                HttpPut httpPut = new HttpPut(uri);
                log.debug("PUT method created based on client request");
                if (body != null) httpPut.setEntity(new StringEntity(createJsonStringEntity(body), "UTF-8"));
                return httpPut;
            case DELETE:
                log.debug("DELETE method created based on client request");
                return new HttpDelete(uri);
            case HEAD:
                log.debug("HEAD method created based on client request");
                return new HttpHead(uri);
            default:
                throw new IllegalStateException("Unknown HTTP method: " + httpMethod);                
        }
    }

    
    private String createJsonStringEntity(Object data) throws JsonGenerationException, JsonMappingException, IOException {
      return new ObjectMapper().writeValueAsString(data);
    }

    private EnstratusResult deserializeResponse(HttpResponse response, String pathToResult) throws IOException {
        return createNewEnstratusResult(EntityUtils.toString(response.getEntity()), response.getStatusLine(), pathToResult);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

}