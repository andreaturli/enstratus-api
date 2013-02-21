package com.enstratus.api.actions.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;

public class DeleteServer extends AbstractAction implements Action {

    private final String API_CALL = "infrastructure/Server/%s";
    
    private final String serverId;
    private final String reason;
    
    public DeleteServer(String serverId, String reason) {
        this.serverId = checkNotNull(serverId, "serverId");
        this.reason = checkNotNull(reason, "reason");     
    }

    @Override
    public String getURI() {
        return String.format(resolveUri(API_CALL), serverId);
    }

    @Override
    public HttpMethod getRestMethodName() {
        return HttpMethod.DELETE;
    }

    @Override
    public List<NameValuePair> getQueryParameters() {
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        queryParams.add(new BasicNameValuePair("reason", reason));
        return queryParams;
    }

}
