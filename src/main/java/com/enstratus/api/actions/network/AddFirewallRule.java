package com.enstratus.api.actions.network;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Direction;
import com.enstratus.api.model.Protocol;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AddFirewallRule extends AbstractAction implements Action {

    private final String API_CALL = "network/FirewallRule";
    
    private final String firewallId;
    private final String cidr;
    private final String startPort;
    private final String endPort;
    private final Direction direction;
    private final Protocol protocol;

    public AddFirewallRule(String firewallId, String cidr, String startPort, String endPort, Direction direction, Protocol protocol) {
        this.firewallId = checkNotNull(firewallId, "firewallId");
        this.cidr = checkNotNull(cidr, "cidr");
        this.startPort = checkNotNull(startPort, "startPort");
        this.endPort = checkNotNull(endPort, "endPort");
        this.direction = checkNotNull(direction, "direction");
        this.protocol = checkNotNull(protocol, "protocol");
    }

    @Override
    public String getURI() {
        return resolveUri(API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.POST;
    }

    @Override
    public Map<String, Object> getBody() {
        
        Map<String, Object> firewallRule = Maps.newLinkedHashMap();
        firewallRule.put("firewallId", firewallId);
        firewallRule.put("cidr", cidr);
        firewallRule.put("startPort", startPort);
        firewallRule.put("endPort", endPort);
        firewallRule.put("direction", direction);
        firewallRule.put("protocol", protocol);
        
        List<Object> rulesList = Lists.newArrayList();
        rulesList.add(firewallRule);
        Map<String, Object> addFirewallRuleBody = Maps.newLinkedHashMap();
        addFirewallRuleBody.put("addRule", rulesList);
        return addFirewallRuleBody;
    }
    
    @Override
    public String getPathToResult() {
        return "jobs";
    }
}
