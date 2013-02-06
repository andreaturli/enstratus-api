package com.enstratus.api.geography;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.Details;
import com.enstratus.api.HttpMethod;
import com.fasterxml.jackson.core.type.TypeReference;

public class ListClouds extends Action {

    final ApiRequest req;
    String apiCall = "geography/Cloud";

    public ListClouds() throws MalformedURLException, URISyntaxException {
        this.req = new ApiRequest(HttpMethod.GET, apiCall, apiVersion, baseUrl,
                        accessKey, secretKey, USER_AGENT, true, Details.BASIC, null, null);
    }

    public void list() throws Exception {
        System.out.println(req);
        final HttpResponse response = req.call();
        System.out.println("Result:  " + response.getStatusLine());
        print(response);
    }

    void print(HttpResponse response) throws IOException {
        final Map<String,Object> cloudList = mapper.readValue(textFromResponse(response), new TypeReference<Map<String, Object>>() {});
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Problem:" + textFromResponse(response));
            return;
        } 
        if (!cloudList.containsKey("clouds")) {
            System.err.println("Expected 'clouds' envelope in the response JSON");
            return;
        }
        final Iterable<?> clouds = (Iterable<?>) cloudList.get("clouds");
        
        for (Object o: clouds) {
            final Map<String,Object> cloud = (Map<String,Object>)o;
            final String cloudProviderName = (String) cloud.get("name");
            System.out.println(cloudProviderName);
        }

    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        new ListClouds().list();
    }
}
