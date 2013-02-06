package com.enstratus.api.infrastructure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.Details;
import com.enstratus.api.HttpMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LauchServer extends Action {

    private final ApiRequest req;
    private final String apiCall = "infrastructure/Server";
    
    public LauchServer(String config) throws MalformedURLException, URISyntaxException {
        String body = checkNotNull(config);
        this.req = new ApiRequest(HttpMethod.POST, apiCall, apiVersion, baseUrl, accessKey, secretKey, USER_AGENT, true,
                Details.BASIC, body , null);
    }

    public void execute() throws Exception {
        System.out.println(req.toString());
        final HttpResponse response = req.call();
        System.out.println("Result:  " + response.getStatusLine());
        print(response);
    }

    void print(HttpResponse response) throws IOException {
        final Map<String, Object> list = mapper.readValue(textFromResponse(response),
                                                               new TypeReference<Map<String, Object>>() {});
        if (response.getStatusLine().getStatusCode() != 202) {
            System.err.println("Problem:" + textFromResponse(response));
            return;
        }
        if (!list.containsKey("jobs")) {
            System.err.println("Expected 'jobs' envelope in the response JSON");
            return;
        }
        final Iterable<?> jobs = (Iterable<?>) list.get("jobs");

        for (Object o : jobs) {
            final Map<String, Object> job = (Map<String, Object>) o;
            System.out.println("Job - id(" + job.get("jobId") + "), status(" + job.get("status") + ")");
        }

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

        try {
            body = mapper.writeValueAsString(lauchServerBody);
          } catch (Exception e) {
              throw Throwables.propagate(e);
          }
        new LauchServer(body).execute();
    }
}
