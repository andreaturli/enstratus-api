package com.enstratus.api.network;


import java.util.List;

import org.apache.http.HttpResponse;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Cloud;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.utils.Printer;

public class ListFirewalls extends AbstractAction implements Action {

    private final static String API_CALL = "network/Firewall";

    @Override
    public String getURI() {
        return resolveUri(API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public String getPathToResult() {
        return "clouds";
    }

    public static void main(String[] args) throws Exception {
        List<Firewall> firewalls = EnstratusAPI.getNetworkApi().listFirewalls();
        for (Firewall firewall : firewalls) {
            System.out.println(firewall);
        }
    }
}
