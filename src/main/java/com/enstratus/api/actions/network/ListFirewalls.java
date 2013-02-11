package com.enstratus.api.actions.network;

import java.util.List;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Firewall;

public class ListFirewalls extends AbstractAction implements Action {

    protected final static String API_CALL = "network/Firewall";

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
        return "firewalls";
    }

    public static void main(String[] args) throws Exception {
        List<Firewall> firewalls = EnstratusAPI.getNetworkApi().listFirewalls();
        for (Firewall firewall : firewalls) {
            System.out.println(firewall);
        }
    }
}
