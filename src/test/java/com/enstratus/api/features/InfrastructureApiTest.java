package com.enstratus.api.features;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.tryFind;
import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.MachineImage;
import com.enstratus.api.model.Region;
import com.enstratus.api.model.Server;
import com.enstratus.api.model.ServerProduct;
import com.enstratus.api.utils.Jobs;
import com.google.common.base.Predicates;

public class InfrastructureApiTest {

    private InfrastructureApi api;
    private String regionId;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = EnstratusAPI.getInfrastructureApi();
        assertNotNull(api);
        Region region = tryFind(EnstratusAPI.getGeographyApi().listRegions(null, "EU", null), Predicates.notNull()).orNull();
        if (region == null)
            Assert.fail();
        regionId = region.getRegionId();
    }

    @Test
    public void launchServer() throws Exception {
        String name = "serverTest";
        String description = "server test";
        
        BillingCode billingCode = tryFindBillingCode();
        if (billingCode == null)
            Assert.fail();
        String budgetId = billingCode.getBillingCodeId();
        
        Datacenter datacenter = tryFindDatacenter();
        if (datacenter == null)
            Assert.fail();
        String dataCenterId = checkNotNull(datacenter.getDataCenterId(), "dataCenterId");

        MachineImage machineImage = tryFindMachineImage();
        String machineImageId = checkNotNull(machineImage.getMachineImageId(), "machineImageId");
        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, null);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job);            
        } finally {
            if(Jobs.isComplete(job)) {
                String serverId = job.getMessage();
                api.deleteServer(serverId, "just a test");
            }
        }
    }
    
    @Test
    public void launchServerWithFirewall() throws Exception {
        String name = "serverTestWithFirewall";
        String description = "server test with firewall";
        
        BillingCode billingCode = tryFindBillingCode();
        if (billingCode == null)
            Assert.fail();
        String budgetId = billingCode.getBillingCodeId();
        
        Datacenter datacenter = tryFindDatacenter();
        if (datacenter == null)
            Assert.fail();
        String dataCenterId = checkNotNull(datacenter.getDataCenterId(), "dataCenterId");

        Firewall firewall = tryFindFirewall();
        if (firewall == null)
            Assert.fail();
        String firewallId = checkNotNull(firewall.getFirewallId(), "firewallId");
        
        MachineImage machineImage = tryFindMachineImage();
        String machineImageId = checkNotNull(machineImage.getMachineImageId(), "machineImageId");
        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, firewallId);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job);            
        } finally {
            if(Jobs.isComplete(job)) {
                String serverId = job.getMessage();
                api.deleteServer(serverId, "just a test");
            }
        }
    }    
    
    private BillingCode tryFindBillingCode() throws Exception {
        return tryFind(EnstratusAPI.getAdminApi().listBillingCodes(regionId), Predicates.notNull()).orNull();
    }
    
    private Datacenter tryFindDatacenter() throws Exception {
        return tryFind(EnstratusAPI.getGeographyApi().listDatacenters(regionId), Predicates.notNull()).orNull();
    }

    private MachineImage tryFindMachineImage() throws Exception {
        return tryFind(EnstratusAPI.getInfrastructureApi().listMachineImages(regionId), Predicates.notNull()).orNull();
    }
    
    private Firewall tryFindFirewall() throws Exception {
        return tryFind(EnstratusAPI.getNetworkApi().listFirewalls(regionId), Predicates.notNull()).orNull();
    }    
    
    @Test
    public void listMachineImages() throws Exception {
        for (MachineImage machineImage : api.listMachineImages(regionId)) {
            assertNotNull(machineImage.getMachineImageId());
        }
    }

    @Test
    public void listServerProducts() throws Exception {
        for (ServerProduct serverProduct : api.listServerProducts(regionId)) {
            assertNotNull(serverProduct.getProductId());
        }
    }

    @Test
    public void listServers() throws Exception {
        for (Server server : api.listServers(regionId)) {
            assertNotNull(server.getServerId());
        }
    }

}
