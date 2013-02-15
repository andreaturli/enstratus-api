package com.enstratus.api.features;

import static com.google.common.collect.Iterables.tryFind;
import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Color;
import com.enstratus.api.model.Direction;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Protocol;
import com.enstratus.api.model.Region;
import com.enstratus.api.utils.Jobs;
import com.google.common.base.Predicates;

public class NetworkApiTest {

    private NetworkApi api;
    private String regionId;
    private String firewallId;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = EnstratusAPI.getNetworkApi();
        assertNotNull(api);
        Region region = tryFind(EnstratusAPI.getGeographyApi().listRegions(null, "EU", null), Predicates.notNull()).orNull();
        if (region == null)
            Assert.fail();
        regionId = region.getRegionId();
    }

    @Test
    public void addFirewall() throws Exception {
        String name = "firewallIntegrationTest";
        String description = "firewall test";
        Color label = Color.RED;
        
        BillingCode billingCode = tryFindBillingCode();
        if (billingCode == null)
            Assert.fail();
        String budgetId = billingCode.getBillingCodeId();
            Job job = api.addFirewall(name, description, budgetId, regionId, label);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job);            
            Assert.assertTrue((Jobs.isComplete(job)));
            firewallId = job.getMessage();
    }
    
    @Test(dependsOnMethods="addFirewall")
    public void addFirewallRule() throws Exception {
        String cidr = "157.166.224.26/32";
        String startPort = "8080";
        String endPort = "8081";
        Assert.assertNotNull(firewallId);
        try {
            api.addFirewallRule(firewallId, cidr, startPort, endPort, Direction.INGRESS, Protocol.TCP);
        } finally {
            api.deleteFirewall(firewallId, "just a test");
        }
    }
    
    private BillingCode tryFindBillingCode() throws Exception {
        return tryFind(EnstratusAPI.getAdminApi().listBillingCodes(regionId), Predicates.notNull()).orNull();
    }
    
    @Test
    public void listFirewalls() throws Exception {
        for (Firewall firewall : api.listFirewalls(regionId)) {
            assertNotNull(firewall);
        }
    }
}
