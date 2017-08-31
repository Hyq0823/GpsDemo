package com.spzh.utils;

import com.spzh.form.HistoryTrailVo;
import com.spzh.form.LoginForm;
import com.spzh.form.VideoQuyeryVo;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GpsUtil {
    private static  String BASE = "http://120.77.253.46";
    public static  String JSESSION = "jsession";
    private static String LOGIN_URL = BASE + ":8080/StandardApiAction_login.action?account=admin&password=kn2008";//登录

    private static String VIDEO_HISTORY = BASE + ":6604/3/5";
    public static String VIDEO_HISTORY_GET = BASE + ":6604/3/5?DownType=2&jsession={0}&DevIDNO={1}&LOC={2}&CHN=-1&YEAR={3}&MON={4}&DAY={5}&RECTYPE={6}&FILEATTR=2&BEG=0&END=86399";
    public static String TRAIL_HISTORY_GET = BASE + ":8080/StandardApiAction_queryTrackDetail.action?jsession={0}&devIdno={1}&begintime={2}&endtime={3}&toMap=2";


    public static String login() {
        String result = null;
        try {
             result = HttpUtils.sendGet(LOGIN_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static  String getJession()  {
        String jsession = null;
            jsession = CachUtil.getCacheByKey(JSESSION);
            if(jsession==null || "".equals(jsession)){
                String loginResult = login();
                LoginForm loginForm = JsonUtils.json2Obj(loginResult, LoginForm.class);
                if(loginForm!=null && "0".equals(loginForm.getResult())){
                    jsession = loginForm.getJsession();
                }
            }
        if(jsession==null || "".equals(jsession)){
            CachUtil.removeByKey(JSESSION);
            System.out.println("登录失败!...");
        }
        return jsession;
    }

    public void putJession2Chache(String jsession){
        CachUtil.putCache("jession",jsession);
    }

    public static String searchHistoryVideo(VideoQuyeryVo videoQuyery) {
        String result = null;
        try{
            Calendar time= Calendar.getInstance();
            time.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(videoQuyery.getStartTime()));
//            Map<String,String> parmas = new HashMap<String,String>();
//            parmas.put("DownType","2");
//            parmas.put("jsession",getJession());
//            parmas.put("DevIDNO",videoQuyery.getDeviceNo());
//            parmas.put("LOC",videoQuyery.getLoc());
//            parmas.put("CHN",-1+"");
//            parmas.put("YEAR",time.get(Calendar.YEAR)+"");
//            parmas.put("MON",time.get(Calendar.MONTH)+1 + "");
//            parmas.put("DAY",time.get(Calendar.DAY_OF_MONTH)+"");
//            parmas.put("RECTYPE",videoQuyery.getRectype());
//            parmas.put("FILEATTR","2");
//            parmas.put("BEG","0");
//            parmas.put("END","86399");
//            result = HttpUtils.sendPost(VIDEO_HISTORY, parmas);
            //:6604/3/5?DownType=2&jsession=${0}&DevIDNO={1}&LOC={2}&CHN=-1&YEAR={3}&MON={4}&DAY={5}&RECTYPE={7}&FILEATTR=2&BEG=0&END=86399";
            String url = MessageFormat.format(VIDEO_HISTORY_GET,getJession(),videoQuyery.getDeviceNo(),videoQuyery.getLoc()
                    ,time.get(Calendar.YEAR)+"",time.get(Calendar.MONTH)+1,time.get(Calendar.DAY_OF_MONTH),videoQuyery.getRectype());
            result = HttpUtils.sendGet(url);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }


    /***
     * 查询历史轨迹
     * @param historyTrailVo 查询实体
     * @return
     */
    public static String  searchHistoryTrail(HistoryTrailVo historyTrailVo){
        String url =MessageFormat.format(TRAIL_HISTORY_GET,getJession(),historyTrailVo.getDeviceNo(),historyTrailVo.getStartTime(),historyTrailVo.getEndTime());
        String result = HttpUtils.sendGet(url);
        return result;
    }

}
