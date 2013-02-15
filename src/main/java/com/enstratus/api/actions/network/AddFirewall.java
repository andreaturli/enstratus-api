package com.enstratus.api.actions.network;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Color;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AddFirewall extends AbstractAction implements Action {

    private final String API_CALL = "network/Firewall";
    
    private final String name;
    private final String budgetId;
    private final String description;
    private final String regionId;
    private final Color label;

    public AddFirewall(String name, String description, String budgetId, String regionId, Color label) {
        this.name = checkNotNull(name, "name");
        this.description = checkNotNull(description, "description");
        this.budgetId = checkNotNull(budgetId, "budgetId");
        this.regionId = checkNotNull(regionId, "regionId");
        this.label = checkNotNull(label, "label");
    }

    @Override
    public String getURI() {
        return resolveUri(API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.POST;
    }

    /*
     * (non-Javadoc)
     * @see com.enstratus.api.AbstractAction#getBody()
     * {
     * "addFirewall":[
     *   {
     *      "region":{"regionId":53101},
     *      "budget":50420,
     *      "description":"Created by the enStratus unit tests.",
     *      "name":"utjson-1334622797",
     *      "label":"red"
     *    }
     * ]
     * }
     * 
     */
    @Override
    public Map<String, Object> getBody() {
        Map<String, Object> addFirewallBody = Maps.newLinkedHashMap();
        
        Map<String, Object> regionDetails = Maps.newLinkedHashMap();
        regionDetails.put("regionId", regionId);
        
        Map<String, Object> addFirewallMap = Maps.newLinkedHashMap();
        addFirewallMap.put("name", name);
        addFirewallMap.put("budget", budgetId);
        addFirewallMap.put("description", description);
        addFirewallMap.put("region", regionDetails);
        addFirewallMap.put("label", label);
        
        List<Object> firewallList = Lists.newArrayList();
        firewallList.add(addFirewallMap);        
        addFirewallBody.put("addFirewall", firewallList);
        return addFirewallBody;
    }
    
    @Override
    public String getPathToResult() {
        return "jobs";
    }
}
