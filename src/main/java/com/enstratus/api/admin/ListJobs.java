package com.enstratus.api.admin;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

public class ListJobs extends Action {

    private final String apiCall = "admin/Job";

    @Override
    public HttpResponse execute() throws Exception {
        ApiRequest req = new ApiRequest(HttpMethod.GET, apiCall, accessKey, secretKey);
        return req.call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        HttpResponse response = new ListJobs().execute();
        Printer.print(response, "jobs");
    }
}
