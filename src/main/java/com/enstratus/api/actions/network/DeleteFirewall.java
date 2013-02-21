package com.enstratus.api.actions.network;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.HttpMethod;
import com.enstratus.api.actions.AbstractAction;
import com.enstratus.api.actions.Action;

public class DeleteFirewall extends AbstractAction implements Action {

    private final String API_CALL = "network/Firewall/%s";
    
    private final String firewallId;
    private final String reason;
    
    public DeleteFirewall(String firewallId, String reason) {
        this.firewallId = checkNotNull(firewallId, "firewallId");
        this.reason = checkNotNull(reason, "reason");     
    }

    @Override
    public String getURI() {
        return String.format(resolveUri(API_CALL), firewallId);
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
