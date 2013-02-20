package com.enstratus.api.client;

import com.enstratus.api.Action;

public interface EnstratusClient {

    EnstratusResult execute(Action clientRequest);

}
