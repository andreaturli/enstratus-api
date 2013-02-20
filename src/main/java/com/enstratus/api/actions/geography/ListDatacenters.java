package com.enstratus.api.actions.geography;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;

/**
 * A datacenter is a zone on aws terminology
 * 
 * @author andrea
 *
 */
public class ListDatacenters extends AbstractAction implements Action {

    private final static String API_CALL = "geography/DataCenter";
    private final String regionId;
    
    public ListDatacenters(String regionId) {
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
        return "dataCenters";
    }
}
