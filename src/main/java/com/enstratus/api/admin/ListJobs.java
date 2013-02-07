package com.enstratus.api.admin;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;

import java.util.List;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusHttpClient;
import com.enstratus.api.client.EnstratusResult;
import com.enstratus.api.model.Job;

public class ListJobs extends AbstractAction implements Action {

    private final static String API_CALL = "admin/Job";

    @Override
    public String getURI() {
        return String.format("/api/enstratus/%s/%s", DEFAULT_VERSION, API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public String getPathToResult() {
        return "jobs";
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        EnstratusClient enstratusClient = new EnstratusHttpClient();
        EnstratusResult enstratusResult = enstratusClient.execute(new ListJobs());
        System.out.println(enstratusResult.getJsonString());
   
        List<Job> jobs = enstratusResult.getSourceAsObjectList(Job.class);
        for (Job job : jobs) {
            System.out.println(job);            
        }
    }
}
