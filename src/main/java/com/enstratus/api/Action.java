package com.enstratus.api;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

public interface Action {

    String getURI();

    HttpMethod getRestMethodName();

    Map<String, Object> getBody();

    String getPathToResult();
    
    Map<String, String> getHeaders() throws Exception;

    List<NameValuePair> getQueryParameters();

}