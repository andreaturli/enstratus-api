package com.enstratus.api;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusHttpClient;
import com.enstratus.api.features.AdminApi;
import com.enstratus.api.features.GeographyApi;
import com.enstratus.api.features.InfrastructureApi;
import com.enstratus.api.features.NetworkApi;

public class EnstratusAPI {
    
    static EnstratusClient enstratusClient = new EnstratusHttpClient();
    
    public static AdminApi getAdminApi() {
        return new AdminApi(enstratusClient);
    }
    
    public static GeographyApi getGeographyApi() {
        return new GeographyApi(enstratusClient);
    }

    public static InfrastructureApi getInfrastructureApi() {
        return new InfrastructureApi(enstratusClient);
    }

    public static NetworkApi getNetworkApi() {
        return new NetworkApi(enstratusClient);
    }
}
