package com.enstratus.api.admin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.enstratus.api.Action;
import com.enstratus.api.ApiRequest;
import com.enstratus.api.Details;
import com.enstratus.api.HttpMethod;
import com.fasterxml.jackson.core.type.TypeReference;

public class ListJobs extends Action {

    private final ApiRequest req;
    private final String apiCall = "admin/Job";
    
    public ListJobs() throws MalformedURLException, URISyntaxException {
        this.req = new ApiRequest(HttpMethod.GET, apiCall, apiVersion, baseUrl, accessKey, secretKey, USER_AGENT, true,
                Details.BASIC, null, null);
    }

    public void list() throws Exception {
        System.out.println(req.toString());
        final HttpResponse response = req.call();
        System.out.println("Result:  " + response.getStatusLine());
        print(response);
    }

    void print(HttpResponse response) throws IOException {
        final Map<String, Object> list = mapper.readValue(textFromResponse(response),
                                                               new TypeReference<Map<String, Object>>() {});
        if (response.getStatusLine().getStatusCode() != 200) {
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
            System.out.println("job - id(" + job.get("jobId") + "), " +
            		                 "status(" + job.get("status") + "), " +
            		                 "message(" + job.get("message") + ")");
        }

    }

    // for IDE quick-access/debug
    public static void main(String[] args) throws Exception {
        new ListJobs().list();
    }
}
