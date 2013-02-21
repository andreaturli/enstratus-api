package com.enstratus.api;

import static com.google.common.base.Preconditions.checkNotNull;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.features.AdminApi;
import com.enstratus.api.features.GeographyApi;
import com.enstratus.api.features.InfrastructureApi;
import com.enstratus.api.features.NetworkApi;

public class EnstratusAPI {

    private final EnstratusClient enstratusClient;

    private final AdminApi adminApi;
    private final GeographyApi geographyApi;
    private final InfrastructureApi infrastructureApi;
    private final NetworkApi networkApi;

    public EnstratusAPI(EnstratusClient enstratusClient) {
        this.enstratusClient = checkNotNull(enstratusClient, "enstratusClient");
        this.adminApi = new AdminApi(enstratusClient);
        this.geographyApi = new GeographyApi(enstratusClient);
        this.infrastructureApi = new InfrastructureApi(enstratusClient);
        this.networkApi = new NetworkApi(enstratusClient);
    }

    public EnstratusClient getEnstratusClient() {
        return enstratusClient;
    }

    public AdminApi getAdminApi() {
        return adminApi;
    }

    public GeographyApi getGeographyApi() {
        return geographyApi;
    }

    public InfrastructureApi getInfrastructureApi() {
        return infrastructureApi;
    }

    public NetworkApi getNetworkApi() {
        return networkApi;
    }

}
