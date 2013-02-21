package com.enstratus.api.features;

import static com.google.common.base.Preconditions.*;
import static com.enstratus.api.utils.EnstratusConstants.*;
import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.client.httpclient.EnstratusHttpClient;

public abstract class BasicEnstratusLiveTest {

    protected final String baseUrl;
    protected final String apiVersion;
    protected final String accessKey;
    protected final String secretKey;

    protected final EnstratusAPI enstratusAPI;
    
    protected BasicEnstratusLiveTest() {
        this.baseUrl = checkNotNull(System.getProperty(ENSTRATUS_API_ENDPOINT, DEFAULT_BASEURL), ENSTRATUS_API_ENDPOINT);
        this.apiVersion = checkNotNull(System.getProperty(ENSTRATUS_API_VERSION, DEFAULT_VERSION),
                ENSTRATUS_API_VERSION);
        this.accessKey = checkNotNull(System.getProperty(ENSTRATUS_API_ACCESS_KEY), ENSTRATUS_API_ACCESS_KEY);
        this.secretKey = checkNotNull(System.getProperty(ENSTRATUS_API_SECRET_KEY), ENSTRATUS_API_SECRET_KEY);
        this.enstratusAPI = new EnstratusAPI(new EnstratusHttpClient(accessKey, secretKey));
    }

}
