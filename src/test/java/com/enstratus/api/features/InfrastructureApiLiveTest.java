package com.enstratus.api.features;

import static com.enstratus.api.utils.Helpers.tryFindBillingCodeOrNull;
import static com.enstratus.api.utils.Helpers.tryFindDatacenterOrNull;
import static com.enstratus.api.utils.Helpers.tryFindFirewallOrNull;
import static com.enstratus.api.utils.Helpers.tryFindMachineImageOrNull;
import static com.enstratus.api.utils.Helpers.tryFindRegionOrNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Jurisdiction;
import com.enstratus.api.model.MachineImage;
import com.enstratus.api.model.Region;
import com.enstratus.api.model.Server;
import com.enstratus.api.model.ServerProduct;
import com.enstratus.api.utils.Jobs;

public class InfrastructureApiLiveTest extends BasicEnstratusLiveTest {

    private InfrastructureApi api;
    private String regionId;
    private String budgetId;
    private String dataCenterId;
    private String machineImageId;
    private String firewallId;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = enstratusAPI.getInfrastructureApi();
        assertNotNull(api);
        Region region = tryFindRegionOrNull(Jurisdiction.EU, enstratusAPI.getGeographyApi());
        if (region == null)
            Assert.fail();
        regionId = region.getRegionId();
        
        BillingCode billingCode = tryFindBillingCodeOrNull(regionId, enstratusAPI.getAdminApi());
        Assert.assertNotNull(billingCode);
        budgetId = billingCode.getBillingCodeId();

        Datacenter datacenter = tryFindDatacenterOrNull(regionId, enstratusAPI.getGeographyApi());
        Assert.assertNotNull(datacenter);
        dataCenterId = checkNotNull(datacenter.getDataCenterId(), "dataCenterId");

        MachineImage machineImage = tryFindMachineImageOrNull(regionId, enstratusAPI.getInfrastructureApi());
        Assert.assertNotNull(machineImage);
        machineImageId = checkNotNull(machineImage.getMachineImageId(), "machineImageId");
        
        Firewall firewall = tryFindFirewallOrNull(regionId, enstratusAPI.getNetworkApi());
        Assert.assertNotNull(firewall);
        firewallId = checkNotNull(firewall.getFirewallId(), "firewallId");
    }

    @Test
    public void launchServer() throws Exception {
        String name = "serverTest";
        String description = "server test";
        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, null);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job, enstratusAPI.getAdminApi());
        } finally {
            if (Jobs.isComplete(job)) {
                String serverId = job.getMessage();
                api.deleteServer(serverId, "just a test");
            }
        }
    }

    @Test
    public void launchServerWithFirewall() throws Exception {
        String name = "serverTestWithFirewall";
        String description = "server test with firewall";

        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, firewallId);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job, enstratusAPI.getAdminApi());
        } finally {
            if (Jobs.isComplete(job)) {
                String serverId = job.getMessage();
                api.deleteServer(serverId, "just a test");
            }
        }
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
