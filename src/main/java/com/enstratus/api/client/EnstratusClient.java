package com.enstratus.api.client;

import com.enstratus.api.actions.Action;

public interface EnstratusClient {

    EnstratusResult execute(Action clientRequest);

}
