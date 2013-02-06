package com.enstratus.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

public class Action {
    public static final String USER_AGENT = "enStratus Java example";
    public static final String DEFAULT_BASEURL = "https://api.enstratus.com";
    public static final String DEFAULT_VERSION = "2012-06-15";

    public static final String ENSTRATUS_API_ENDPOINT = "ENSTRATUS_API_ENDPOINT";
    public static final String ENSTRATUS_API_VERSION = "ENSTRATUS_API_VERSION";
    public static final String ENSTRATUS_API_ACCESS_KEY = "ENSTRATUS_API_ACCESS_KEY";
    public static final String ENSTRATUS_API_SECRET_KEY = "ENSTRATUS_API_SECRET_KEY";

    public static final ObjectMapper mapper = new ObjectMapper();

    protected final String baseUrl;
    protected final String apiVersion;
    protected final String accessKey;
    protected final String secretKey;

    /**
     * Default constructor for using environment variables/defaults. What's documented
     * in the README.
     */
    public Action() {
        this.baseUrl = checkNotNull(System.getProperty(ENSTRATUS_API_ENDPOINT, DEFAULT_BASEURL).trim(), ENSTRATUS_API_ENDPOINT);
        this.apiVersion = checkNotNull(System.getProperty(ENSTRATUS_API_VERSION, DEFAULT_VERSION).trim(), ENSTRATUS_API_VERSION);
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

    protected String textFromResponse(HttpResponse response) throws IOException {
        if (response == null)
            return null;
        return inputStreamToString(response.getEntity().getContent());
    }

    protected String inputStreamToString(InputStream stream) throws IOException {
        String content = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
        Closeables.closeQuietly(stream);
        return content;
    }
}
