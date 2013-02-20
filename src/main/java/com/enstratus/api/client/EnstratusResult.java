package com.enstratus.api.client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class EnstratusResult {

    private static final Logger log = LoggerFactory.getLogger(EnstratusResult.class);

    private Map<String, Object> jsonMap;
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

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    @SuppressWarnings("unchecked")
    public <T> T getSourceAsObject(Class<?> type) {
        return (T) Iterables.getOnlyElement((List<T>) getSourceAsObjectList(type));
    } 
    
    @SuppressWarnings("unchecked")
    public <T> T getSourceAsObjectList(Class<?> type) {
        List<Object> objectList = Lists.newArrayList();
        if (!isSucceeded) return (T) objectList;
        List<Object> sourceList = extractSource();
        for (Object source : sourceList) {
            Object obj = createSourceObject(source, type);
            if (obj != null) objectList.add(obj);
        }
        return (T) objectList;
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    protected List<Object> extractSource() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapResponse;
        try {
            mapResponse = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
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