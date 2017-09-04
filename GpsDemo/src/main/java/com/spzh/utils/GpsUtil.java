package com.spzh.utils;

import com.spzh.form.HistoryTrailVo;
import com.spzh.form.LoginForm;
import com.spzh.form.VideoQuyeryVo;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GpsUtil {
    private static  String BASE = "http://120.77.253.46";
    public static  String JSESSION = "jsession";
    private static String LOGIN_URL = BASE + ":8080/StandardApiAction_login.action?account=admin&password=kn2008";//登录

    private static String VIDEO_HISTORY = BASE + ":6604/3/5";
    public static String VIDEO_HISTORY_GET = BASE + ":6604/3/5?DownType=2&jsession={0}&DevIDNO={1}&LOC={2}&CHN=-1&YEAR={3}&MON={4}&DAY={5}&RECTYPE={6}&FILEATTR=2&BEG={7}&END={8}";
    public static String TRAIL_HISTORY_GET = BASE + ":8080/StandardApiAction_queryTrackDetail.action?jsession={0}&devIdno={1}&begintime={2}&endtime={3}&toMap=2";


    /**
     * 计算时间秒值
     * @param date 目标时间
     * @param gapMinutes 时间偏移量
     * @return 秒值
     * @throws Exception
     */
    private static long time2long(String date,int gapMinutes) throws Exception {
        Calendar time = parseCalendar(date);
        time.add(Calendar.MINUTE,gapMinutes);
        return caculateSeconds(time);
    }

    private static Calendar parseCalendar(String date) throws ParseException {
        Date parseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parseDate);
        return instance;
    }

    private static long getTimeLong(String date) throws ParseException {
        Calendar time = parseCalendar(date);
        return caculateSeconds(time);
    }

    public static long caculateSeconds(Calendar time){
        int hour_seconds = time.get(Calendar.HOUR_OF_DAY) * 60 * 60;
        int minute_seconds = time.get(Calendar.MINUTE) * 60;
        return time.get(Calendar.SECOND) + hour_seconds + minute_seconds;
    }


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
                    ,time.get(Calendar.YEAR)+"",time.get(Calendar.MONTH)+1,time.get(Calendar.DAY_OF_MONTH),videoQuyery.getRectype()
                    ,caculateSeconds(time),getTimeLong(videoQuyery.getEndTime()));
            System.out.println("seach_his_video_url:"+url);
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
