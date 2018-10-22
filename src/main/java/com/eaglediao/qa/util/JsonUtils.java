package com.eaglediao.qa.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {


    public static Map json2Map(String json) {

        Map<String, String> map;
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
            });
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}