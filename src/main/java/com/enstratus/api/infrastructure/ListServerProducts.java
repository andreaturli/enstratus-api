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

/**
 * Server products represent the available options and pricing for launching a virtual machine.
 * 
 * @author andrea
 *
 */
public class ListServerProducts extends Action {

    private final String apiCall = "infrastructure/ServerProduct";
    private final String regionId;
    
    public ListServerProducts(String regionId) throws MalformedURLException, URISyntaxException {
        this.regionId = checkNotNull(regionId, "regionId");
    }

    @Override
    public HttpResponse execute() throws Exception {
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        queryParams.add(new BasicNameValuePair("regionId", regionId));
        return new ApiRequest(HttpMethod.GET, apiCall, accessKey, secretKey).call(queryParams);
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        String regionId = "20827";
        final HttpResponse response = new ListServerProducts(regionId).execute();
        Printer.print(response, "serverProducts");
    }
}
