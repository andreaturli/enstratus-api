package com.enstratus.api.features;

import java.util.List;

import com.enstratus.api.actions.network.AddFirewall;
import com.enstratus.api.actions.network.AddFirewallRule;
import com.enstratus.api.actions.network.DeleteFirewall;
import com.enstratus.api.actions.network.ListFirewalls;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.model.Color;
import com.enstratus.api.model.Direction;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Protocol;

public class NetworkApi {

    private final EnstratusClient enstratusClient;

    public NetworkApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<Firewall> listFirewalls(String regionId) {
        return enstratusClient.execute(new ListFirewalls(regionId)).getSourceAsObjectList(Firewall.class);
    }
    
    public Job addFirewall(String name, String description, String budgetId, String regionId,
            Color label) throws Exception {
        return enstratusClient.execute(new AddFirewall(name, description, budgetId, regionId, label))
                .getSourceAsObject(Job.class);
    }
    
    /* TODO 
     * FirewallRule is created correctly but the HTTP response is weird
     * Receiving response: HTTP/1.1 418 
     * << HTTP/1.1 418 
     * << Server: Apache-Coyote/1.1
     * << x-es-request: msp0xde08c860
     * << Content-Type: application/json;charset=UTF-8
     * << Transfer-Encoding: chunked
     * << Date: Fri, 15 Feb 2013 11:20:23 GMT
     * << X-Backend-Server: api-b
     * << "{ "error" : { "message" : "No job was created and no resource was created." } }[\n]"
     */
    public void addFirewallRule(String firewallId, String cidr, String startPort, String endPort, Direction direction, Protocol protocol) throws Exception {
        // TODO at the moment no returned object
        enstratusClient.execute(new AddFirewallRule(firewallId, cidr, startPort, endPort, direction, protocol));
    }    

    public void deleteFirewall(String firewallId, String reason) throws Exception {
        if(reason.length() < 10)
            throw new RuntimeException();
        enstratusClient.execute(new DeleteFirewall(firewallId, reason));
    }
}
