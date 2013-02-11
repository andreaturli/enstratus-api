package com.enstratus.api.actions.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;

public class GetServer extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/Server/%s";
    private final String serverId;
    private final String regionId;
    
    public GetServer(String serverId, String regionId) throws MalformedURLException, URISyntaxException {
        this.serverId = checkNotNull(serverId, "serverId");
        this.regionId = checkNotNull(regionId, "regionId");
    }
    
    @Override
    public String getURI() {
        return String.format(resolveUri(API_CALL), serverId);
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
        return "servers";
    }

}
