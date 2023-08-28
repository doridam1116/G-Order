package com.gorder.responses;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {
    public Map<String, String> responseHttp(String code, String msg){
        Map<String, String> result = new HashMap<>();
        result.put(code,msg);
        return result;
    }
}
