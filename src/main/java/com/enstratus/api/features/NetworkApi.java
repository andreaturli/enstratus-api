package com.enstratus.api.features;

import java.util.List;

import com.enstratus.api.actions.network.ListFirewalls;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.model.Firewall;

public class NetworkApi {

    private final EnstratusClient enstratusClient;

    public NetworkApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<Firewall> listFirewalls(String regionId) throws Exception {
        return enstratusClient.execute(new ListFirewalls(regionId)).getSourceAsObjectList(Firewall.class);
    }
}
