package com.spzh.controller;

import com.spzh.form.LoginForm;
import com.spzh.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private GpsService gpsService;

    @RequestMapping({"/table",""})
    public String login(Model model, HttpServletRequest request){
        LoginForm loginForm = gpsService.login(request);
        model.addAttribute("loginForm",loginForm);
        return "index";
    }

}
