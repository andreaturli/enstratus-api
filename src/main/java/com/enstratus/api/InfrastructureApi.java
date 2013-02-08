package com.enstratus.api;

import java.util.List;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.infrastructure.LaunchServer;
import com.enstratus.api.infrastructure.ListMachineImages;
import com.enstratus.api.infrastructure.ListServerProducts;
import com.enstratus.api.infrastructure.ListServers;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.MachineImage;
import com.enstratus.api.model.Server;
import com.enstratus.api.model.ServerProduct;

public class InfrastructureApi {

    private final EnstratusClient enstratusClient;

    public InfrastructureApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<MachineImage> listMachineImages(String regionId) throws Exception {
        return enstratusClient.execute(new ListMachineImages(regionId)).getSourceAsObjectList(MachineImage.class);
    }

    public List<ServerProduct> listServerProducts(String regionId) throws Exception {
        return enstratusClient.execute(new ListServerProducts(regionId)).getSourceAsObjectList(ServerProduct.class);
    }

    public List<Server> listServers(String regionId) throws Exception {
        return enstratusClient.execute(new ListServers(regionId)).getSourceAsObjectList(Server.class);
    }

    public List<Job> launchServer(String name, String budget, String description, String machineImageId,
            String dataCenterId) throws Exception {
        return enstratusClient.execute(new LaunchServer(name, budget, description, machineImageId, dataCenterId))
                .getSourceAsObjectList(Job.class);
    }

}
