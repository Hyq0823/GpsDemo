package com.spzh.controller;

import com.spzh.form.*;
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
    public String back(Model model, VideoQuyeryVo videoQuyery){
        System.out.println(videoQuyery);
        RecordForm recordforms = gpsService.searchHistoryVideo(videoQuyery);
        model.addAttribute("record",recordforms);
        model.addAttribute("videoQuyery",videoQuyery);
        model.addAttribute("jsession", GpsUtil.getJession());
        return "index";
    }

    @RequestMapping("/trail")
    public String trailMain(Model model, HistoryTrailVo historyTrailVo){
        model.addAttribute("jsession",GpsUtil.getJession());
        model.addAttribute("trail_history_url",GpsUtil.TRAIL_HISTORY_GET);
        return "trail";
    }


    /**
     * 方法2： 后端拉取Gps信息
     * @param model
     * @param historyTrailVo
     * @return
     */
    @RequestMapping("/trail/query")
    public String trail(Model model, HistoryTrailVo historyTrailVo){
        HistoryTrailForm historyTrailForm = gpsService.SearchHistoryTrail(historyTrailVo);
        model.addAttribute("historyTrailVo",historyTrailVo);
        model.addAttribute("historyTrailForm",historyTrailForm);
        return "trail";
    }

}
