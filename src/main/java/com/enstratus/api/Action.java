package com.enstratus.api;

import static com.enstratus.api.utils.EnstratusConstants.*;
import static com.google.common.base.Preconditions.checkNotNull;
import org.apache.http.HttpResponse;

public abstract class Action {

    protected final String baseUrl;
    protected final String apiVersion;
    protected final String accessKey;
    protected final String secretKey;

    public Action() {
        this.baseUrl = checkNotNull(System.getProperty(ENSTRATUS_API_ENDPOINT, DEFAULT_BASEURL), ENSTRATUS_API_ENDPOINT);
        this.apiVersion = checkNotNull(System.getProperty(ENSTRATUS_API_VERSION, DEFAULT_VERSION), ENSTRATUS_API_VERSION);
        this.accessKey = checkNotNull(System.getProperty(ENSTRATUS_API_ACCESS_KEY), ENSTRATUS_API_ACCESS_KEY);
        this.secretKey = checkNotNull(System.getProperty(ENSTRATUS_API_SECRET_KEY), ENSTRATUS_API_SECRET_KEY);
    }

    /**
     * Supply keys and URL yourself instead of using environment variables.
     *
     * @param baseUrl e.g. "https://api.enstratus.com"
     * @param apiVersion e.g.  "2012-06-15"
     * @param accessKey access key
     * @param secretKey raw secret key
     */
    public Action(String baseUrl, String apiVersion, String accessKey, String secretKey) {
        this.baseUrl = checkNotNull(baseUrl, ENSTRATUS_API_ENDPOINT);
        this.apiVersion = checkNotNull(apiVersion, ENSTRATUS_API_VERSION);
        this.accessKey = checkNotNull(accessKey, ENSTRATUS_API_ACCESS_KEY);
        this.secretKey = checkNotNull(secretKey, ENSTRATUS_API_SECRET_KEY);
    }
    
    protected abstract HttpResponse execute() throws Exception;
    
}
