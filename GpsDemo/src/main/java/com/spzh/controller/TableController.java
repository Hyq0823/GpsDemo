package com.spzh.controller;

import com.spzh.entity.Table;
import com.spzh.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private GpsService gpsService;

    @RequestMapping({"/table",""})
    public String login(Model model, Table table){
        System.out.println(table);
        System.out.println(table);
        System.out.println(table);
        return "table";
    }

}
