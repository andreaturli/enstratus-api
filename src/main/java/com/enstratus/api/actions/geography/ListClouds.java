package com.enstratus.api.actions.geography;

import com.enstratus.api.AbstractAction;
import com.enstratus.api.Action;
import com.enstratus.api.HttpMethod;

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
}
