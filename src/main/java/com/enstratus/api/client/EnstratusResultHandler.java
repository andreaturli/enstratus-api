package com.enstratus.api.client;

public interface EnstratusResultHandler<T> {

    void completed(T result);

    void failed(Exception ex);
    
}
