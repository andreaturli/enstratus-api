package com.enstratus.api.actions.geography;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;

/**
 * A subscription describes the capabilities of a specific region as matched by
 * your subscription to the region.
 * 
 * @author andrea
 * 
 */
public class ListSubscriptions extends AbstractAction implements Action {

    private final static String API_CALL = "geography/Subscription";

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
        return "subscriptions";
    }

}
