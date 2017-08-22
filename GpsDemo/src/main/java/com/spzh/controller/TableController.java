package com.spzh.controller;

import com.spzh.entity.Area;
import com.spzh.form.LoginForm;
import com.spzh.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private GpsService gpsService;

    private static Map<String,List<Area>> map = new HashMap<String, List<Area>>();
    private static  List<Area> areas1 = new ArrayList<Area>();;
    private static  List<Area> areas2 = new ArrayList<Area>();;
    private static  List<Area> areas3 = new ArrayList<Area>();;
    private static  List<Area> areas4= new ArrayList<Area>();;
    private static  List<Area> areas5= new ArrayList<Area>();;
    private static  List<Area> areas6= new ArrayList<Area>();;
    private static  List<Area> areas7= new ArrayList<Area>();;
    private static  List<Area> areas8= new ArrayList<Area>();;

    static {
            areas2.add(new Area("86","中国","1","","86"));
            areas2.add(new Area("1","四川省","1","86","111"));
            areas2.add(new Area("2","北京市","0","86","2222"));
            areas2.add(new Area("3","天津市","0","86","33333"));
                areas3.add(new Area("4","成都市","1","1","4444"));
                    areas4.add(new Area("5","邛崃市","1","4","5555"));
                        areas5.add(new Area("6","邛崃市２","1","5","5555"));
                            areas6.add(new Area("7","羊安镇","1","6","66666"));
                                areas7.add(new Area("8","樊哙村","1","7","777"));
                                    areas8.add(new Area("9","一组","0","8","777"));
        map.put("0",areas2);
        map.put("1",areas3);
        map.put("4",areas4);
        map.put("5",areas5);
        map.put("6",areas6);
        map.put("7",areas7);
        map.put("8",areas8);
    }


    @RequestMapping({"/table",""})
    public String login(Model model, HttpServletRequest request){
        LoginForm loginForm = gpsService.login(request);
        model.addAttribute("loginForm",loginForm);
        return "index";
    }
    @RequestMapping("/treeTable")
    public String treeTable(Model model, HttpServletRequest request){
        return "treeTable";
    }

    @RequestMapping({"/findByParentId"})
    @ResponseBody
    public List<Area> findByParentId(String parentId, Model model){
        List<Area> areas = null;
        if(parentId == null || "".equals(parentId)){//第一级节点
            areas = areas2;
        }else{
            areas = map.get(parentId);
        }
        return areas;
    }

}
