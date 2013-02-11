package com.enstratus.api.features;

import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Region;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class AdminApiTest {

    private AdminApi api;
    private Region region;

    @BeforeClass
    public void beforeClass() throws Exception {
        api = EnstratusAPI.getAdminApi();
        assertNotNull(api);
        region = Iterables.tryFind(EnstratusAPI.getGeographyApi().listRegions(), Predicates.notNull()).orNull();
        if (region == null)
            Assert.fail();
    }

    @Test
    public void listBillingCodes() throws Exception {
        for (BillingCode billingCode : api.listBillingCodes(region.getRegionId())) {
            assertNotNull(billingCode.getBillingCodeId());
        }
    }

    @Test
    public void listJobs() throws Exception {
        for (Job job : api.listJobs()) {
            assertNotNull(job.getJobId());
        }
    }
}
