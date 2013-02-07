package com.enstratus.api.geography;


import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

public class ListClouds extends Action {

    private final static String API_CALL = "geography/Cloud";

    @Override
    protected HttpResponse execute() throws Exception {
        ApiRequest req = new ApiRequest(HttpMethod.GET, API_CALL, accessKey, secretKey);
        return req.call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        HttpResponse response = new ListClouds().execute();
        Printer.print(response, "clouds");
    }
}
