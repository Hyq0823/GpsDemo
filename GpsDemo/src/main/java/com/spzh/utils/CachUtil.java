package com.spzh.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyq on 2017/8/17.
 */
public class CachUtil {
    private static Map<String,String> map  = new HashMap<String, String>();


    public static void putCache(String key,String value){
        map.put(key,value);
    }
    public static String getCacheByKey(String key){
        return  map.get(key);
    }

    public static void removeByKey(String key){
        map.remove(key);
    }
}
