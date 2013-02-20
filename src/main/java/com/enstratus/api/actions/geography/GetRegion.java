package com.enstratus.api.actions.geography;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Jurisdiction;

public class GetRegion extends AbstractAction implements Action {

    String API_CALL = "geography/Region/%s";
    private final String regionId;
    private final String accountId;
    private final Jurisdiction jurisdiction; 
    private final String scope;
    
    public GetRegion(String regionId) {
        this(checkNotNull(regionId, "regionId"), null, null, null);
    }
    
    public GetRegion(String regionId, String accountId, Jurisdiction jurisdiction, String scope) {
        this.regionId = checkNotNull(regionId, "regionId");
        this.accountId = accountId;
        this.jurisdiction = jurisdiction;
        this.scope = scope;
    }

    @Override
    public String getURI() {
        return String.format(resolveUri(API_CALL), regionId);
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
