package com.enstratus.api.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enstratus.api.model.Datacenter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnstratusResult {

    private static final Logger log = LoggerFactory.getLogger(EnstratusResult.class);

    private Map jsonMap;
    private String jsonString;
    private String pathToResult;
    private boolean isSucceeded;

    public String getPathToResult() {
        return pathToResult;
    }

    public void setPathToResult(String pathToResult) {
        this.pathToResult = pathToResult;
    }

    public Object getValue(String key) {
        return jsonMap.get(key);
    }

    public boolean isSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(boolean succeeded) {
        isSucceeded = succeeded;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getErrorMessage() {
        return (String) jsonMap.get("error");
    }

    public Map getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map jsonMap) {
        this.jsonMap = jsonMap;
    }

    public <T> T getSourceAsObjectList(Class<?> type) throws JsonParseException, JsonMappingException, IOException {
        List<Object> objectList = new ArrayList<Object>();
        if (!isSucceeded) return (T) objectList;
        List<Object> sourceList = (List<Object>) extractSource();
        for (Object source : sourceList) {
            Object obj = createSourceObject(source, type);
            if (obj != null) objectList.add(obj);
        }
        return (T) objectList;
    }

    private <T> T createSourceObject(Object source, Class<?> type) {
        Object obj = null;
        try {
            if (source instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonBall = mapper.writeValueAsString(source);
                obj = mapper.readValue(jsonBall, type);
            } else {
                obj = type.cast(source);
            }

        } catch (Exception e) {
            log.error("Unhandled exception occurred while converting source to the object ." + type.getCanonicalName(), e);
        }
        return (T) obj;
    }


    protected List<Object> extractSource() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        final Map<String,Object> mapResponse = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        if (!isSucceeded) {
            throw new RuntimeException();
        } 
        if (!mapResponse.containsKey(pathToResult)) {
            log.error("Expected {} envelope in the response JSON", pathToResult);
            throw new RuntimeException();
        }
        return (List<Object>) mapResponse.get(pathToResult);
    }

}