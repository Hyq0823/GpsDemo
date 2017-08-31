package com.spzh.service;

import com.spzh.form.*;
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

    public RecordForm searchHistoryVideo(VideoQuyeryVo videoQuyery) {
        String result = GpsUtil.searchHistoryVideo(videoQuyery);
        RecordForm recordForm = JsonUtils.json2Obj(result, RecordForm.class);
        if(recordForm==null || !"0".equals(recordForm.getResult())){
            CachUtil.removeByKey(GpsUtil.JSESSION);
        }
        return recordForm;
    }

    public HistoryTrailForm SearchHistoryTrail(HistoryTrailVo historyTrailVo){
        String result = GpsUtil.searchHistoryTrail(historyTrailVo);
        System.out.println(result);
        HistoryTrailForm historyTrailForm = JsonUtils.json2Obj(result, HistoryTrailForm.class);
        if(historyTrailForm==null || !"0".equals(historyTrailForm.getResult())){
            CachUtil.removeByKey(GpsUtil.JSESSION);//拉取轨迹信息失败
        }
        return historyTrailForm;
    }

}
