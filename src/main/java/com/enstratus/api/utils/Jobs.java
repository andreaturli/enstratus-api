package com.enstratus.api.utils;

import java.util.concurrent.TimeUnit;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Status;
import com.google.common.util.concurrent.Uninterruptibles;

public class Jobs {
    
    public static Job waitForJob(Job job) throws Exception {
        while(job.getStatus()!=Status.COMPLETE) {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            job = EnstratusAPI.getAdminApi().getJob(job.getJobId());
            if(job.getStatus()==Status.ERROR)
                return job;
        }
        return job;
    }

    public static boolean isComplete(Job job) throws Exception {
        return job.getStatus() == Status.COMPLETE;
    }
}
