package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.MachineImage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LaunchServer extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/Server";
    private final String name;
    private final String budget;
    private final String description;
    private final String machineImageId;
    private final String dataCenterId;

    public LaunchServer(String name, String budget, String description, String machineImageId, String dataCenterId) {
        this.name = checkNotNull(name, "name");
        this.budget = checkNotNull(budget, "budget");
        this.description = checkNotNull(description, "description");
        this.machineImageId = checkNotNull(machineImageId, "machineImageId");
        this.dataCenterId = checkNotNull(dataCenterId, "dataCenterId");
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
     *   "dataCenter":{"dataCenterId":xxxxx}
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
        launchMap.put("budget", budget);
        launchMap.put("description", description);
        launchMap.put("machineImage", machineImageDetails);
        launchMap.put("dataCenter", datacenterDetails);
        
        List<Object> launchList = Lists.newArrayList();
        launchList.add(launchMap);        
        lauchServerBody.put("launch", launchList);

        return lauchServerBody;
    }

    public static void main(String[] args) throws Exception {
        String name = "andrea-enstratus-test";
        String budget = "10725";
        String description = "andrea enstratus test";
        String machineImageId = "296131";
        String dataCenterId = "20827";
        List<Job> jobs = EnstratusAPI.getInfrastructureApi()
                                     .launchServer(name, budget, description, machineImageId, dataCenterId);
        for (Job job : jobs) {
            System.out.println(job);
        }

    }
}
