package com.enstratus.api.admin;

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

public class ListBillingCodes extends Action {

    private final ApiRequest req;
    private final String apiCall = "admin/BillingCode";
    
    public ListBillingCodes(String regionID) throws MalformedURLException, URISyntaxException {
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
        if (!list.containsKey("billingCodes")) {
            System.err.println("Expected 'billingCodes' envelope in the response JSON");
            return;
        }
        final Iterable<?> billingCodes = (Iterable<?>) list.get("billingCodes");

        for (Object o : billingCodes) {
            final Map<String, Object> billingCode = (Map<String, Object>) o;
            System.out.println("machineImage - name(" + billingCode.get("name") + "), id(" + billingCode.get("billingCodeId") + ")");
        }

    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        new ListBillingCodes(regionId).list();
    }
}
