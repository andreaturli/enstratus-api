package com.enstratus.api;

import static com.google.common.io.BaseEncoding.base64;
import static com.google.common.io.ByteStreams.readBytes;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.base.Charsets;
import com.google.common.io.ByteProcessor;

public class RequestSignature {
    
    public static String sign(byte[] key, String toSign) throws Exception {
        
        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key, "HmacSHA256"));
        ByteProcessor<byte[]> processor = asByteProcessor(mac);
        InputStream input = new ByteArrayInputStream(toSign.getBytes(Charsets.UTF_8));;
        return base64().encode(readBytes(input , processor));
    }
    
    public static ByteProcessor<byte[]> asByteProcessor(final Mac mac) {
        checkNotNull(mac, "mac");
        return new ByteProcessor<byte[]>() {
           public boolean processBytes(byte[] buf, int off, int len) {
              mac.update(buf, off, len);
              return true;
           }
           public byte[] getResult() {
              return mac.doFinal();
           }
        };
     }
}
