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

public class ListRegions extends Action {

    final ApiRequest req;
    String apiCall = "geography/Region";

    public ListRegions() throws MalformedURLException, URISyntaxException {
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
        if (!cloudList.containsKey("regions")) {
            System.err.println("Expected 'regions' envelope in the response JSON");
            return;
        }
        final Iterable<?> regions = (Iterable<?>) cloudList.get("regions");
        
        for (Object o: regions) {
            final Map<String,Object> region = (Map<String,Object>)o;
            final String regionName = (String) region.get("name");
            final Integer regionId = (Integer) region.get("regionId");
            System.out.println("region name(" + regionName + "), id(" + regionId + ")");
        } 

    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        new ListRegions().list();
    }
}
