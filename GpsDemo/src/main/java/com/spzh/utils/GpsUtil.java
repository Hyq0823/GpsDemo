package com.spzh.utils;

public class GpsUtil {
    private static String LOGIN_URL = "http://120.77.253.46:8080/StandardApiAction_login.action?account=admin&password=kn2008";//登录

    public static String login() {
        String result = "";
        try {
             result = HttpUtils.sendGet(LOGIN_URL,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
