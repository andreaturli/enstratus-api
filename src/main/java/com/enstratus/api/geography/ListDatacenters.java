package com.enstratus.api.geography;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;
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
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusHttpClient;
import com.enstratus.api.client.EnstratusResult;
import com.enstratus.api.model.Datacenter;

/**
 * A datacenter is a zone on aws terminology
 * 
 * @author andrea
 *
 */
public class ListDatacenters extends AbstractAction implements Action {

    private final static String API_CALL = "geography/DataCenter";
    private final String regionId;
    
    public ListDatacenters(String regionId) throws MalformedURLException, URISyntaxException {
        this.regionId = checkNotNull(regionId, "regionId");
    }
    
    @Override
    public String getURI() {
        return String.format("/api/enstratus/%s/%s", DEFAULT_VERSION, API_CALL);
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

    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        EnstratusClient enstratusClient = new EnstratusHttpClient();
        EnstratusResult enstratusResult = enstratusClient.execute(new ListDatacenters(regionId));
        System.out.println(enstratusResult.getJsonString());
        
        List<Datacenter> datacenters = enstratusResult.getSourceAsObjectList(Datacenter.class);
        for (Datacenter datacenter : datacenters) {
            System.out.println(datacenter);            
        }
    }

}
