package com.spzh.controller;

import com.spzh.form.LoginForm;
import com.spzh.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private GpsService gpsService;

    @RequestMapping("/login")
    public String login(Model model){
        LoginForm loginForm = gpsService.login();
        model.addAttribute("loginForm",loginForm);
        return "index";
    }
}
