package com.enstratus.api.actions.admin;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;

public class GetJob extends AbstractAction implements Action {

    private final static String API_CALL = "admin/Job/%s";
    private String jobId;
    
    public GetJob(String jobId) {
        this.jobId = jobId;
    }
    
    @Override
    public String getURI() {
        return String.format(resolveUri(API_CALL), jobId);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public String getPathToResult() {
        return "jobs";
    }

}
