package com.spzh.service;

import com.spzh.form.LoginForm;
import com.spzh.form.RecordForm;
import com.spzh.form.VideoQuyery;
import com.spzh.utils.CachUtil;
import com.spzh.utils.GpsUtil;
import com.spzh.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class GpsService {

    public LoginForm login(HttpServletRequest request){
        LoginForm getForm = (LoginForm)request.getSession().getAttribute("loginForm");
        if(getForm!=null){
            return getForm;
        }
        String result = GpsUtil.login();
        LoginForm loginForm = JsonUtils.json2Obj(result,LoginForm.class);
        if(loginForm!=null && "0".equals(loginForm.getResult())){
            request.getSession().setAttribute("loginForm",loginForm);
            CachUtil.putCache(GpsUtil.JSESSION,loginForm.getJsession());
        }else{
            CachUtil.removeByKey(GpsUtil.JSESSION);
        }
        return loginForm;

    }

    public RecordForm searchHistory(VideoQuyery videoQuyery) {
        String result = GpsUtil.searchHistory(videoQuyery);
        RecordForm recordForm = JsonUtils.json2Obj(result, RecordForm.class);
        if(recordForm==null || !"0".equals(recordForm.getResult())){
            CachUtil.removeByKey(GpsUtil.JSESSION);
        }
        return recordForm;
    }

}
