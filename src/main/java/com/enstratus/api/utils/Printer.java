package com.enstratus.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

public class Printer {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String textFromResponse(HttpResponse response) throws IOException {
        if (response == null)
            return null;
        return inputStreamToString(response.getEntity().getContent());
    }

    public static String inputStreamToString(InputStream stream) throws IOException {
        String content = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
        Closeables.closeQuietly(stream);
        return content;
    }
    
    public static void print(HttpResponse response, String envelope, int errorCode) throws IOException {
        final Map<String,Object> mapResponse = mapper.readValue(textFromResponse(response), new TypeReference<Map<String, Object>>() {});
        if (response.getStatusLine().getStatusCode() != errorCode) {
            System.err.println("Problem:" + textFromResponse(response));
            return;
        } 
        if (!mapResponse.containsKey(envelope)) {
            System.err.println(String.format("Expected '%s' envelope in the response JSON", envelope));
            return;
        }
        final Iterable<?> envelopeMap = (Iterable<?>) mapResponse.get(envelope);
        
        for (Object o: envelopeMap) {
            @SuppressWarnings("unchecked")
            final Map<String,Object> map = (Map<String,Object>)o;
            System.out.println("======================= " + envelope + " =======================");
            for (String key : map.keySet()) {
                System.out.println(key + " : " + map.get(key));
            }
            System.out.println("============================================================");
        }
    }
    
    public static void print(HttpResponse response, String envelope) throws IOException {
        print(response, envelope, 200);
    }
}
