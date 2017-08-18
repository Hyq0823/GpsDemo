package com.spzh.controller;

import com.spzh.form.LoginForm;
import com.spzh.form.RecordForm;
import com.spzh.form.VideoQuyery;
import com.spzh.service.GpsService;
import com.spzh.utils.GpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private GpsService gpsService;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
        LoginForm loginForm = gpsService.login(request);
        model.addAttribute("loginForm",loginForm);
        return "index";
    }

    @RequestMapping("/video/back")
    /**
     * rectype 0表示常规，1表示报警，-1表示所有。
     loc 1表示设备，2表示存储服务器，4表示下载服务器。
     */
    public String back(Model model, VideoQuyery videoQuyery){
        System.out.println(videoQuyery);
        RecordForm recordforms = gpsService.searchHistory(videoQuyery);
        model.addAttribute("record",recordforms);
        model.addAttribute("videoQuyery",videoQuyery);
        model.addAttribute("jsession", GpsUtil.getJession());
        return "index";
    }
}
