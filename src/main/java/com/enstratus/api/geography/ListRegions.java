package com.enstratus.api.geography;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;

import java.util.List;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.client.EnstratusClient;
import com.enstratus.api.client.EnstratusHttpClient;
import com.enstratus.api.client.EnstratusResult;
import com.enstratus.api.model.Cloud;
import com.enstratus.api.model.Region;

public class ListRegions extends AbstractAction implements Action {

    String API_CALL = "geography/Region";

    @Override
    public String getURI() {
        return String.format("/api/enstratus/%s/%s", DEFAULT_VERSION, API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public String getPathToResult() {
        return "regions";
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        EnstratusClient enstratusClient = new EnstratusHttpClient();
        EnstratusResult enstratusResult = enstratusClient.execute(new ListRegions());
        System.out.println(enstratusResult.getJsonString());

        List<Region> regions = enstratusResult.getSourceAsObjectList(Region.class);
        for (Region region : regions) {
            System.out.println(region);
        }
    }

}
