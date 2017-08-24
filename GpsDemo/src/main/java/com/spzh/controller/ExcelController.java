package com.spzh.controller;

import com.spzh.excel.ExportExcel;
import com.spzh.excel.ImportExcel;
import com.spzh.excel.form.AreaForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyq on 2017/8/23.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {


    @RequestMapping("/export")
    public void testExport(Model model, HttpServletResponse response){
        try {
            List<AreaForm> areas = new ArrayList<AreaForm>();
            areas.add(new AreaForm("86","中国","1","","86"));
            areas.add(new AreaForm("1","四川省","1","86","111"));
            areas.add(new AreaForm("2","北京市","0","86","2222"));
            areas.add(new AreaForm("3","天津市","0","86","33333"));
            areas.add(new AreaForm("4","成都市","1","1","4444"));
            areas.add(new AreaForm("5","邛崃市","1","4","5555"));
            areas.add(new AreaForm("6","邛崃市２","1","5","5555"));
            areas.add(new AreaForm("7","羊安镇","1","6","66666"));
            areas.add(new AreaForm("8","樊哙村","1","7","777"));
            areas.add(new AreaForm("9","一组","0","8","777"));
            new ExportExcel("测试标题", AreaForm.class).setData(areas).write(response,"test1.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/downloadTemplate")
    public void downLoadTemplate(HttpServletResponse response){
        List<AreaForm> areas = new ArrayList<AreaForm>();
        areas.add(new AreaForm("86","地区名称","1","","86"));
        try {
            new ExportExcel("地区数据导入模板",AreaForm.class)
                    .setData(areas).write(response,"地区导入模板.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importArea")
    public String importArea(MultipartFile file,Model model) throws Exception {
        try{
            System.out.println(file);
            List<AreaForm> areaList = new ImportExcel(file).getData(AreaForm.class);
            model.addAttribute("areaList",areaList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }



}
