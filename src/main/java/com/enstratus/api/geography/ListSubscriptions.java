package com.enstratus.api.geography;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

/**
 * A subscription describes the capabilities of a specific region as matched by
 * your subscription to the region.
 * 
 * @author andrea
 * 
 */
public class ListSubscriptions extends Action {

    private final static String API_CALL = "geography/Subscription";

    @Override
    protected HttpResponse execute() throws Exception {
        ApiRequest req = new ApiRequest(HttpMethod.GET, API_CALL, accessKey, secretKey);
        return req.call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        HttpResponse response = new ListSubscriptions().execute();
        Printer.print(response, "subscriptions");
    }
}
