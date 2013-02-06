package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.Details;
import com.enstratus.api.HttpMethod;
import com.fasterxml.jackson.core.type.TypeReference;

public class ListServers extends Action {

    private final ApiRequest req;
    private final String apiCall = "infrastructure/Server";
    
    public ListServers(String regionID) throws MalformedURLException, URISyntaxException {
        String regionId = checkNotNull(regionID, "regionId");
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        queryParams.add(new BasicNameValuePair("regionId", regionId));
        this.req = new ApiRequest(HttpMethod.GET, apiCall, apiVersion, baseUrl, accessKey, secretKey, USER_AGENT, true,
                Details.BASIC, null, queryParams);
    }

    public void list() throws Exception {
        System.out.println(req.toString());
        final HttpResponse response = req.call();
        System.out.println("Result:  " + response.getStatusLine());
        print(response);
    }

    void print(HttpResponse response) throws IOException {
        final Map<String, Object> list = mapper.readValue(textFromResponse(response),
                                                               new TypeReference<Map<String, Object>>() {});
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Problem:" + textFromResponse(response));
            return;
        }
        if (!list.containsKey("servers")) {
            System.err.println("Expected 'servers' envelope in the response JSON");
            return;
        }
        final Iterable<?> servers = (Iterable<?>) list.get("servers");

        for (Object o : servers) {
            final Map<String, Object> server = (Map<String, Object>) o;
            final String serverName = (String) server.get("name");
            final Map<String, Object> region = (Map<String, Object>) server.get("region");
            final Map<String, Object> customer = (Map<String, Object>) server.get("customer");
            System.out.println("Server - name(" + serverName + "), " +
            		                    "customerId(" + customer.get("customerId")+ "), " +
            		                    "regionId(" + region.get("regionId") + ")");
        }

    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        new ListServers(regionId).list();
    }
}
