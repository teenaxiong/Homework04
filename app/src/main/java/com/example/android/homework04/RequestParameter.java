package com.example.android.homework04;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

public class RequestParameter {
    private HashMap<String, String> params;
    private StringBuilder stringBuilder;

    public RequestParameter() {
        params = new HashMap<>();
        stringBuilder = new StringBuilder();
    }

    public RequestParameter addParamters(String key, String value){
        try {
            params.put(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getEncodedParameters() {
        for (String key : params.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(key + "=" + URLDecoder.decode(params.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public String getEncordedURL(String url){
        return url + "?" + getEncodedParameters();
    }

}
