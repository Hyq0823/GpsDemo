package com.spzh.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyq on 2017/8/23.
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    @RequestMapping("/tree")
    @ResponseBody
    public String tree(Model model,String id) throws Exception {
        Map<String,JSONObject> map = new HashMap<String,JSONObject>();
        JSONObject d1 = new JSONObject();
             JSONObject  d11 = new JSONObject();
              d11.put("name","北京");
            d11.put("id","1");
              JSONObject d12 = new JSONObject();
                 d12.put("name","上海");
            d1.put("1",d11);
             d1.put("2",d12);

        JSONObject d2 = new JSONObject();
            JSONObject d111 = new JSONObject();
                 d111.put("name","东城区");
                 d111.put("id","1_1");
            JSONObject d122 = new JSONObject();
                 d2.put("1_1",d111);

        JSONObject d3 = new JSONObject();
            JSONObject d113 = new JSONObject();
             d113.put("name","3级郊区");
             d113.put("id","1_1_1");
             d3.put("1_1_1",d113);

        JSONObject d4 = new JSONObject();
        JSONObject d1134 = new JSONObject();
        d113.put("name","4级郊区");
        d113.put("id","1_1_1_1");
        d4.put("1_1_1_1",d1134);

        map.put("0",d1);
        map.put("1",d2);
        map.put("1_1",d3);
        map.put("1_1_1",d4);
        String s = map.get(id).toString();
        System.out.println(s);
        return s;
    }





}
