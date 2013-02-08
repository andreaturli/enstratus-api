package com.enstratus.api;

import java.util.List;

import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.geography.ListClouds;
import com.enstratus.api.geography.ListDatacenters;
import com.enstratus.api.geography.ListRegions;
import com.enstratus.api.geography.ListSubscriptions;
import com.enstratus.api.model.Cloud;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Region;
import com.enstratus.api.model.Subscription;

public class GeographyApi {
    
    private final EnstratusClient enstratusClient;

    public GeographyApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<Region> listRegions() throws Exception {
        return enstratusClient.execute(new ListRegions()).getSourceAsObjectList(Region.class);
    }

    public List<Cloud> listClouds() throws Exception {
        return enstratusClient.execute(new ListClouds()).getSourceAsObjectList(Cloud.class);
    }

    public List<Datacenter> listDatacenters(String regionId) throws Exception {
        return enstratusClient.execute(new ListDatacenters(regionId)).getSourceAsObjectList(Datacenter.class);
    }

    public List<Subscription> listSubscriptions() throws Exception {
        return enstratusClient.execute(new ListSubscriptions()).getSourceAsObjectList(Subscription.class);
    }
}
