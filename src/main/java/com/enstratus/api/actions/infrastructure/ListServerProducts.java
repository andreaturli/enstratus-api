package com.enstratus.api.actions.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;

/**
 * Server products represent the available options and pricing for launching a virtual machine.
 * 
 * @author andrea
 *
 */
public class ListServerProducts extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/ServerProduct";
    private final String regionId;
    
    public ListServerProducts(String regionId) {
        this.regionId = checkNotNull(regionId, "regionId");
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
        queryParams.add(new BasicNameValuePair("regionId", regionId));
        return queryParams;
    }
    
    @Override
    public String getPathToResult() {
        return "serverProducts";
    }
}
