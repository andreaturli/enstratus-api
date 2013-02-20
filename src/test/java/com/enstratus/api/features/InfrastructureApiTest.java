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

import com.enstratus.api.EnstratusAPI;
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

public class InfrastructureApiTest {

    private InfrastructureApi api;
    private String regionId;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = EnstratusAPI.getInfrastructureApi();
        assertNotNull(api);
        Region region = tryFindRegionOrNull(Jurisdiction.EU);
        if (region == null)
            Assert.fail();
        regionId = region.getRegionId();
    }

    @Test
    public void launchServer() throws Exception {
        String name = "serverTest";
        String description = "server test";

        BillingCode billingCode = tryFindBillingCodeOrNull(regionId);
        if (billingCode == null)
            Assert.fail();
        String budgetId = billingCode.getBillingCodeId();

        Datacenter datacenter = tryFindDatacenterOrNull(regionId);
        if (datacenter == null)
            Assert.fail();
        String dataCenterId = checkNotNull(datacenter.getDataCenterId(), "dataCenterId");

        MachineImage machineImage = tryFindMachineImageOrNull(regionId);
        String machineImageId = checkNotNull(machineImage.getMachineImageId(), "machineImageId");
        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, null);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job);
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

        BillingCode billingCode = tryFindBillingCodeOrNull(regionId);
        if (billingCode == null)
            Assert.fail();
        String budgetId = billingCode.getBillingCodeId();

        Datacenter datacenter = tryFindDatacenterOrNull(regionId);
        if (datacenter == null)
            Assert.fail();
        String dataCenterId = checkNotNull(datacenter.getDataCenterId(), "dataCenterId");

        Firewall firewall = tryFindFirewallOrNull(regionId);
        if (firewall == null)
            Assert.fail();
        String firewallId = checkNotNull(firewall.getFirewallId(), "firewallId");

        MachineImage machineImage = tryFindMachineImageOrNull(regionId);
        String machineImageId = checkNotNull(machineImage.getMachineImageId(), "machineImageId");
        Job job = null;
        try {
            job = api.launchServer(name, description, budgetId, machineImageId, dataCenterId, firewallId);
            assertNotNull(job.getJobId());
            job = Jobs.waitForJob(job);
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
