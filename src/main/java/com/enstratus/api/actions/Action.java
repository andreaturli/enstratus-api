package com.enstratus.api.actions;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.enstratus.api.HttpMethod;

public interface Action {

    String getURI();

    HttpMethod getRestMethodName();
    
    String getPathToResult();
    
    List<NameValuePair> getQueryParameters();
    
    Map<String, Object> getBody();
}