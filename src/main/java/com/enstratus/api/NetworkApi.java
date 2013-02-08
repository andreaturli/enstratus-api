package com.enstratus.api;

import java.util.List;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.network.ListFirewalls;

public class NetworkApi {

    private final EnstratusClient enstratusClient;

    public NetworkApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<Firewall> listFirewalls() throws Exception {
        return enstratusClient.execute(new ListFirewalls()).getSourceAsObjectList(Firewall.class);
    }
}
