package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.MachineImage;

public class ListMachineImages extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/MachineImage";
    private final String regionId;
    
    public ListMachineImages(String regionId) throws MalformedURLException, URISyntaxException {
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
        return "images";
    }

    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        List<MachineImage> machineImages = EnstratusAPI.getInfrastructureApi().listMachineImages(regionId);
        for (MachineImage machineImage : machineImages) {
            System.out.println(machineImage);
        }
    }
}
