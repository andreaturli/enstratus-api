package com.enstratus.api.actions;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

public abstract class AbstractAction implements Action {
    
    protected String resolveUri(String apiCall) {
        return String.format("/api/enstratus/%s/%s", DEFAULT_VERSION, apiCall);
    }

    @Override
    public Map<String, Object> getBody() {
        return null;
    }    
    
    @Override
    public List<NameValuePair> getQueryParameters() {
        return null;
    }
    

    @Override
    public String getPathToResult() {
        return null;
    }
}
