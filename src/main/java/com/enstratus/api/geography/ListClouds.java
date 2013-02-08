package com.enstratus.api.geography;

import java.util.List;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.model.Cloud;

public class ListClouds extends AbstractAction implements Action {

    private final static String API_CALL = "geography/Cloud";

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
        return "clouds";
    }

    public static void main(String[] args) throws Exception {
        List<Cloud> clouds = EnstratusAPI.getGeographyApi().listClouds();
        for (Cloud cloud : clouds) {
            System.out.println(cloud);
        }
    }
}
