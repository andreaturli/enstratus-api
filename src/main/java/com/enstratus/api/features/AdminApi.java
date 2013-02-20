package com.enstratus.api.features;

import java.util.List;

import com.enstratus.api.actions.admin.GetJob;
import com.enstratus.api.actions.admin.ListBillingCodes;
import com.enstratus.api.actions.admin.ListJobs;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Job;

public class AdminApi {

    private final EnstratusClient enstratusClient;

    public AdminApi(EnstratusClient enstratusClient) {
        this.enstratusClient = enstratusClient;
    }

    public List<BillingCode> listBillingCodes(String regionId) {
        return enstratusClient.execute(new ListBillingCodes(regionId)).getSourceAsObjectList(BillingCode.class);
    }

    public List<Job> listJobs() {
        return enstratusClient.execute(new ListJobs()).getSourceAsObjectList(Job.class);
    }

    public Job getJob(String jobId) {
        return enstratusClient.execute(new GetJob(jobId)).getSourceAsObject(Job.class);
    }

}
