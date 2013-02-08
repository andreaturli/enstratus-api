package com.enstratus.api;

import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_BASEURL;
import static com.enstratus.api.utils.EnstratusConstants.DEFAULT_VERSION;
import static com.enstratus.api.utils.EnstratusConstants.ENSTRATUS_API_ACCESS_KEY;
import static com.enstratus.api.utils.EnstratusConstants.ENSTRATUS_API_ENDPOINT;
import static com.enstratus.api.utils.EnstratusConstants.ENSTRATUS_API_SECRET_KEY;
import static com.enstratus.api.utils.EnstratusConstants.ENSTRATUS_API_VERSION;
import static com.enstratus.api.utils.EnstratusConstants.USER_AGENT;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public abstract class AbstractAction implements Action {

    protected final String baseUrl;
    protected final String apiVersion;
    protected final String accessKey;
    protected final String secretKey;

    public AbstractAction() {
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
    public AbstractAction(String baseUrl, String apiVersion, String accessKey, String secretKey) {
        this.baseUrl = checkNotNull(baseUrl, ENSTRATUS_API_ENDPOINT);
        this.apiVersion = checkNotNull(apiVersion, ENSTRATUS_API_VERSION);
        this.accessKey = checkNotNull(accessKey, ENSTRATUS_API_ACCESS_KEY);
        this.secretKey = checkNotNull(secretKey, ENSTRATUS_API_SECRET_KEY);
    }
    
    protected String resolveUri(String apiCall) {
        return String.format("/api/enstratus/%s/%s", DEFAULT_VERSION, apiCall);
    }

    @Override
    public Map<String, Object> getBody() {
        return null;
    }    
    
    @Override
    public List<NameValuePair> getQueryParameters() {
        return null;
    }
    
    @Override
    public Map<String, String> getHeaders() throws Exception {        
        Map<String, String> headers = Maps.newLinkedHashMap();
        headers.put("User-Agent", USER_AGENT);
        headers.put("Accept", "application/json");
        String timestamp = Long.toString(System.currentTimeMillis());
        String toSign = Joiner.on(":").join(ImmutableList.of(accessKey, getRestMethodName().toString(), getURI(), timestamp, USER_AGENT));
        String signature = RequestSignature.sign(secretKey, toSign);
        headers.put("x-es-details", Details.BASIC.toString());
        headers.put("x-es-with-perms", "false");
        headers.put("x-esauth-access", accessKey);
        headers.put("x-esauth-signature", signature);
        headers.put("x-esauth-timestamp", timestamp);
        return headers;
    }
}
