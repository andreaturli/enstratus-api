package com.enstratus.api.utils;

import java.util.concurrent.TimeUnit;

import com.enstratus.api.features.AdminApi;
import com.enstratus.api.model.Job;
import com.enstratus.api.model.Status;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;

public class Jobs {
    
    public static Job waitForJob(Job job, AdminApi adminApi) {
        return waitForJob(job, 60, adminApi);
    }
    
    public static Job waitForJob(Job job, int maxWaitInSec, AdminApi adminApi) {
        Stopwatch stopWatch = new Stopwatch();
        while(job.getStatus()!=Status.COMPLETE && stopWatch.elapsed(TimeUnit.SECONDS) < maxWaitInSec) {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            job = adminApi.getJob(job.getJobId());
            if(job.getStatus()==Status.ERROR)
                return job;
        }
        return job;
    }

    public static boolean isComplete(Job job) {
        return job.getStatus() == Status.COMPLETE;
    }
}
