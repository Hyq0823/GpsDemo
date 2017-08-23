package com.spzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by hyq on 2017/8/23.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {


    @RequestMapping("/export")
    public void testExport(Model model, HttpServletResponse response){
        /*
        try {
            new ExportExcel("测试标题", AreaForm.class).write(response,"test1.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
