package com.alibaba.maxgraph.credentials;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    private static final String ACCESS_KEY_NAME = "accesskey";
    private static final String SIGNATURE_TYPE_NAME = "signature_type";
    private static final String AK_SIGNATURE_TYPE = "HMAC-MD5";
    private static final String JAVA_SIGNATURE_TYPE = "HmacMD5";
    private static final String TIMESTAMP_NAME = "timestamp";
    private static final String SIGNATURE_NAME = "signature";
    private static final String VERSION_NAME = "version";
    private static final String VERSION = "1.0";

    public static String generateAuthURLParameter(Credentials credentials) throws NoSuchAlgorithmException, InvalidKeyException {
        Map<String, String> paraMap = new TreeMap<>();
        paraMap.put(ACCESS_KEY_NAME, credentials.getAccessKeyId());
        paraMap.put(SIGNATURE_TYPE_NAME,  AK_SIGNATURE_TYPE);
        paraMap.put(VERSION_NAME, VERSION);

        String timestamp = String.valueOf(System.currentTimeMillis());

        paraMap.put(TIMESTAMP_NAME, timestamp);

        String baseStr = mapToString(paraMap);

        SecretKey secretKey = new SecretKeySpec(credentials.getAccessKeySecret().getBytes(), JAVA_SIGNATURE_TYPE);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(baseStr.getBytes());
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode(digest)));

        paraMap.put(SIGNATURE_NAME, sign);

        return String.format("?accessKeyId=%s&signatureMethod=%s&version=1.0&timestamp=%s&signature=%s",
                paraMap.get(ACCESS_KEY_NAME),
                paraMap.get(SIGNATURE_TYPE_NAME),
                paraMap.get(TIMESTAMP_NAME),
                paraMap.get(SIGNATURE_NAME));
    }

    private static String mapToString(Map<String, String> map) {
        String baseStr = "";
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();

            baseStr += entry.getKey();
            baseStr += "=";
            baseStr += entry.getValue();
            baseStr += "&";
        }

        baseStr = baseStr.substring(0, baseStr.length()-1);

        return baseStr;
    }
}
