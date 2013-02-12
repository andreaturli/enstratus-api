package com.enstratus.api.features;

import static com.google.common.collect.Iterables.tryFind;
import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Region;
import com.google.common.base.Predicates;

public class NetworkApiTest {

    private NetworkApi api;
    private String regionId;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = EnstratusAPI.getNetworkApi();
        assertNotNull(api);
        Region region = tryFind(EnstratusAPI.getGeographyApi().listRegions(), Predicates.notNull()).orNull();
        if (region == null)
            Assert.fail();
        regionId = region.getRegionId();
    }

    @Test
    public void listFirewalls() throws Exception {
        for (Firewall firewall : api.listFirewalls(regionId)) {
            System.err.println(firewall);
            assertNotNull(firewall);
        }
    }
}
