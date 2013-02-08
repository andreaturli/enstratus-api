package com.enstratus.api;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusHttpClient;

public class EnstratusAPI {
    
    static EnstratusClient enstratusClient = new EnstratusHttpClient();
    
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
