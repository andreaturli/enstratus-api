package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;

public class ListServers extends Action {

    private final String apiCall = "infrastructure/Server";
    private final String regionId;
    
    public ListServers(String regionId) throws MalformedURLException, URISyntaxException {
        this.regionId = checkNotNull(regionId, "regionId");
    }

    @Override
    public HttpResponse execute() throws Exception {
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        queryParams.add(new BasicNameValuePair("regionId", regionId));
        return new ApiRequest(HttpMethod.GET, apiCall, accessKey, secretKey).call();
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        HttpResponse response = new ListServers(regionId).execute();
        Printer.print(response, "servers");
    }
}
