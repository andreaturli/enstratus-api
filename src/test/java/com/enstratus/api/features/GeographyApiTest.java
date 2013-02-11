package com.enstratus.api.features;

import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.Cloud;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Region;
import com.enstratus.api.model.Subscription;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

@Test(groups = "live", testName = "GeographyApiTest")
public class GeographyApiTest {

    private GeographyApi api;

    @BeforeClass
    public void beforeClass() {
        api = EnstratusAPI.getGeographyApi();
        assertNotNull(api);
    }

    public void testListClouds() throws Exception {
        for (Cloud cloud : api.listClouds()) {
            assertNotNull(cloud.getCloudId());
        }
    }

    public void testListRegions() throws Exception {
        for (Region region : api.listRegions()) {
            assertNotNull(region.getRegionId());
        }
    }
    
    public void testGetRegion() throws Exception {
        Region region = Iterables.tryFind(api.listRegions(), Predicates.notNull()).orNull();
        if(region == null)
            Assert.fail();
        System.out.println(api.getRegion(region.getRegionId()));
    }    

    public void testListDatacenters() throws Exception {
        Region region = Iterables.tryFind(api.listRegions(), Predicates.notNull()).orNull();
        if(region == null)
            Assert.fail();
        for (Datacenter datacenter : api.listDatacenters(region.getRegionId())) {
            assertNotNull(datacenter.getDataCenterId());
        }
    }
    
    public void testSubscriptions() throws Exception {
        for (Subscription subscription : api.listSubscriptions()) {
            assertNotNull(subscription.getRegionId());
        }
    }
}
