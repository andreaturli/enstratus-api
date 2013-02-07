package com.enstratus.api.client;

import java.util.LinkedHashSet;

import com.enstratus.api.Action;

public interface EnstratusClient {

    EnstratusResult execute(Action clientRequest) throws Exception;

    //void executeAsync(Action clientRequest, EnstratusResultHandler<EnstratusResult> enstratusResultHandler) throws ExecutionException, InterruptedException, IOException;
}
