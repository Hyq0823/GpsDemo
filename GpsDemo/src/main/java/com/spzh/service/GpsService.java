package com.spzh.service;

import com.spzh.form.LoginForm;
import com.spzh.utils.GpsUtil;
import com.spzh.utils.JsonUtils;
import org.springframework.stereotype.Service;

@Service
public class GpsService {

    public LoginForm login(){
        String result = GpsUtil.login();
        return JsonUtils.json2Obj(result,LoginForm.class);
    }
}
