package com.enstratus.api.actions.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LaunchServer extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/Server";
    
    private final String name;
    private final String budgetId;
    private final String description;
    private final String machineImageId;
    private final String dataCenterId;
    private final String firewallId;
    
    public LaunchServer(String name, String description, String budgetId, String machineImageId, String dataCenterId) {
        this(name, description, budgetId, machineImageId, dataCenterId, null);
    }
    
    public LaunchServer(String name, String description, String budgetId, String machineImageId, String dataCenterId,
            String firewallId) {
        this.name = checkNotNull(name, "name");
        this.description = checkNotNull(description, "description");
        this.budgetId = checkNotNull(budgetId, "budgetId");
        this.machineImageId = checkNotNull(machineImageId, "machineImageId");
        this.dataCenterId = checkNotNull(dataCenterId, "dataCenterId");
        this.firewallId = firewallId;
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
    public String getPathToResult() {
        return "jobs";
    }

    /**
     * {
     *   "launch":
     *   [
     *   {
     *   "name":"andrea-enstratus-test",
     *   "budget":"xxx",
     *   "description":"andrea enstratus test",
     *   "machineImage":{"machineImageId":296131},
     *   "dataCenter":{"dataCenterId":xxxxx},
     *   "firewalls":[{"firewallId":3064},{"firewallId":3263}], // optional
     *   }
     *   ]
     *   }
     */
    @Override
    public Map<String, Object> getBody() {
        Map<String, Object> lauchServerBody = Maps.newLinkedHashMap();
        
        Map<String, Object> machineImageDetails = Maps.newLinkedHashMap();
        machineImageDetails.put("machineImageId", machineImageId);

        Map<String, Object> datacenterDetails = Maps.newLinkedHashMap();
        datacenterDetails.put("dataCenterId", dataCenterId);
        
        Map<String, Object> launchMap = Maps.newLinkedHashMap();
        launchMap.put("name", name);
        launchMap.put("budget", budgetId);
        launchMap.put("description", description);
        launchMap.put("machineImage", machineImageDetails);
        launchMap.put("dataCenter", datacenterDetails);
        if(firewallId != null) {
            Map<String, Object> firewallDetails = Maps.newLinkedHashMap();
            firewallDetails.put("firewallId", firewallId);        
            List<Map<String, Object>> firewallList = Lists.newArrayList();
            firewallList.add(firewallDetails);
            launchMap.put("firewalls", firewallList);
        }
        List<Object> launchList = Lists.newArrayList();
        launchList.add(launchMap);        
        lauchServerBody.put("launch", launchList);

        return lauchServerBody;
    }
}
