package com.enstratus.api.geography;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

public class ListRegions extends Action {

    String apiCall = "geography/Region";

    @Override
    protected HttpResponse execute() throws Exception {
        return new ApiRequest(HttpMethod.GET, apiCall, accessKey, secretKey).call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        HttpResponse response = new ListRegions().execute();
        Printer.print(response, "regions");
    }

}
