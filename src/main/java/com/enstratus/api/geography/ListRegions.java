package com.enstratus.api.geography;

import java.util.List;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Region;

public class ListRegions extends AbstractAction implements Action {

    String API_CALL = "geography/Region";

    @Override
    public String getURI() {
        return resolveUri(API_CALL);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.GET;
    }

    @Override
    public String getPathToResult() {
        return "regions";
    }

    public static void main(String[] args) throws Exception {
        List<Region> regions = EnstratusAPI.getGeographyApi().listRegions();
        for (Region region : regions) {
            System.out.println(region);
        }
    }

}
