package com.enstratus.api.actions.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;
import com.google.common.collect.Lists;

public class ListMachineImages extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/MachineImage";
    private final String regionId;
    
    public ListMachineImages(String regionId) {
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
        List<NameValuePair> queryParams = Lists.newArrayList();
        queryParams.add(new BasicNameValuePair("regionId", regionId));
        return queryParams;
    }
    
    @Override
    public String getPathToResult() {
        return "images";
    }
}
