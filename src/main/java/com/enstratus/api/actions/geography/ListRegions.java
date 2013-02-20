package com.enstratus.api.actions.geography;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Jurisdiction;

public class ListRegions extends AbstractAction implements Action {

    String API_CALL = "geography/Region";
    private final String accountId;
    private final Jurisdiction jurisdiction; 
    private final String scope;
    
    public ListRegions(String accountId, Jurisdiction jurisdiction, String scope) {
        this.accountId = accountId;
        this.jurisdiction = jurisdiction;
        this.scope = scope;
    }
    
    @Override
    public String getURI() {
        return resolveUri(API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public List<NameValuePair> getQueryParameters() {
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        if(accountId != null) queryParams.add(new BasicNameValuePair("accountId", accountId));
        if(jurisdiction != null) queryParams.add(new BasicNameValuePair("jurisdiction", jurisdiction.toString()));
        if(scope != null) queryParams.add(new BasicNameValuePair("scope", scope));
        return queryParams;
    }
    
    @Override
    public String getPathToResult() {
        return "regions";
    }
}
