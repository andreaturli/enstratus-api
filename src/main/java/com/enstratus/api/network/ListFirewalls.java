package com.enstratus.api.network;


import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

public class ListFirewalls extends Action {

    private final static String API_CALL = "network/Firewall";

    @Override
    protected HttpResponse execute() throws Exception {
        ApiRequest req = new ApiRequest(HttpMethod.GET, API_CALL, accessKey, secretKey);
        return req.call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        HttpResponse response = new ListFirewalls().execute();
        Printer.print(response, "firewalls");
    }
}
