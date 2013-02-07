package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.HttpMethod;
import com.enstratus.api.utils.Printer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LaunchServer extends Action {

    private final String apiCall = "infrastructure/Server";
    private final String body;
    
    public LaunchServer(String body)  {
        this.body = checkNotNull(body, "body");
    }

    public HttpResponse execute() throws Exception {
        return new ApiRequest(HttpMethod.POST, apiCall, accessKey, secretKey).call(body);
    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        String body = "";
        Map<String, Object> lauchServerBody = Maps.newLinkedHashMap();
        
        Map<String, Object> machineImageDetails = Maps.newLinkedHashMap();
        machineImageDetails.put("machineImageId", 296131);

        Map<String, Object> launchMap = Maps.newLinkedHashMap();
        launchMap.put("name", "test");
        launchMap.put("budget", 10725);
        launchMap.put("label", "red");
        launchMap.put("description", "andrea enstratus test");
        launchMap.put("machineImage", machineImageDetails);
        //launchMap.put("startDate", "2013-02-06T15:58:54.254+0000");
        List<Object> launchList = Lists.newArrayList();
        launchList.add(launchMap);        
        lauchServerBody.put("launch", launchList);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            body = mapper.writeValueAsString(lauchServerBody);
          } catch (Exception e) {
              throw Throwables.propagate(e);
          }
        HttpResponse response = new LaunchServer(body).execute();
        Printer.print(response, "jobs", 202);
    }
}
