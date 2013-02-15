package com.enstratus.api.features;

import java.util.List;

import com.enstratus.api.actions.infrastructure.DeleteServer;
import com.enstratus.api.actions.infrastructure.GetServer;
import com.enstratus.api.actions.infrastructure.LaunchServer;
import com.enstratus.api.actions.infrastructure.ListMachineImages;
import com.enstratus.api.actions.infrastructure.ListServerProducts;
import com.enstratus.api.actions.infrastructure.ListServers;
import com.enstratus.api.client.EnstratusClient;
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

    public Server getServer(String serverId, String regionId) throws Exception {
        return enstratusClient.execute(new GetServer(serverId, regionId)).getSourceAsObject(Server.class);
    }
    
    public Job launchServer(String name, String description, String budgetId, String machineImageId,
            String dataCenterId, String firewallId) throws Exception {
        return enstratusClient.execute(new LaunchServer(name, description, budgetId, machineImageId, dataCenterId, firewallId))
                .getSourceAsObject(Job.class);
    }

    public void deleteServer(String serverId, String reason) throws Exception {
            if(reason.length() < 10)
                throw new RuntimeException();
            enstratusClient.execute(new DeleteServer(serverId, reason));
    }
}
