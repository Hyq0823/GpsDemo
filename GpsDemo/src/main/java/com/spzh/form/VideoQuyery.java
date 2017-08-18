package com.spzh.form;

/**
 * Created by hyq on 2017/8/17.
 */
public class VideoQuyery {
    private String deviceNo;
    private String startTime;
    private String endTime;
    private String loc;
    private String rectype;

    public VideoQuyery(String deviceNo, String startTime, String endTime, String loc, String rectype) {
        this.deviceNo = deviceNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.loc = loc;
        this.rectype = rectype;
    }

    public VideoQuyery() {
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getRectype() {
        return rectype;
    }

    public void setRectype(String rectype) {
        this.rectype = rectype;
    }
}
