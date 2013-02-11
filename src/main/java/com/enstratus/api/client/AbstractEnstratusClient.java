package com.enstratus.api.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractEnstratusClient implements EnstratusClient {

    private static final Logger log = LoggerFactory.getLogger(AbstractEnstratusClient.class);
    
    protected EnstratusResult createNewEnstratusResult(String json, StatusLine statusLine, String pathToResult) {
        EnstratusResult result = new EnstratusResult();
        if (!json.isEmpty()) {
            Map<String, Object> jsonMap = convertJsonStringToMapObject(json);
            result.setJsonString(json);
            result.setJsonMap(jsonMap);
        }
        result.setPathToResult(pathToResult);
        result.setSucceeded(false);
        if (statusLine.getStatusCode() == HttpStatus.SC_OK || 
            statusLine.getStatusCode() == HttpStatus.SC_ACCEPTED) {
            result.setSucceeded(true);
        }
        return result;
    }
    
    protected Map<String, Object> convertJsonStringToMapObject(String jsonBall) {
        try {
            return new ObjectMapper().readValue(jsonBall, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            log.error("An exception occurred while converting json string to map object");
        }
        return new HashMap<String, Object>();
    }

    protected String getRequestURL(String enstratusEndpoint, String version, String uri) {
        String serverUrl = enstratusEndpoint.endsWith("/") ? enstratusEndpoint.substring(0,
                enstratusEndpoint.length() - 1) : enstratusEndpoint;
        return String.format("%s/api/enstratus/%s/%s", serverUrl, version, uri);

    }
}