package com.spzh.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spzh.utils.DateUtils;

public class RecordFileForm{
	private int arm;//报警信息 当为报警录像时有效。
	private int beg;//文件开始时间 单位秒，如3600=1点。
	private int chn;//文件结束时间 单位秒，如7200=2点，结束时间可能会大于86400，这个表示出现了跨天的情况
	private int chnMask;//	通道掩码 1个文件同时存储多个通道的视频时有效，按位表示，第0位表示录像文件中有通道1的录像，以此类推。
	//先判断chnMask，若chnMask>0，再解析chn。
	private int day;//日
	private String devIdno;//设备号 当查询服务器上的录像时，则表示车牌号。
	private int end;//结束秒数
	private String file;//文件名称
	private long len;//文件大小
	private int loc;//文件位置 1表示设备，2表示存储服务器，4表示下载服务器。
	private int mon;//月份
	private int recing;//是否正在录像 0表示没有录像，1表示正在录像。
	private int stream;//视频码流1表示子码流，0表示主码流
	private int svr;//服务器ID 当查找存储服务器和下载服务器上录像时有效。
	private int type;//录像类型 0表示常规，1表示报警。
	private int year;//年 使用时加上2000。
	
	
	@JsonIgnore
	private String startTime;
	@JsonIgnore
	private String endTime;
	
	
	
	
	public String getStartTime() {
		StringBuffer sb = new StringBuffer();
		sb.append(year+2000).append("-")
		.append(mon).append("-")
		.append(day).append(" ").append(DateUtils.formatTimeStr(beg));
		return  sb.toString();
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		StringBuffer sb = new StringBuffer();
		sb.append(year+2000).append("-")
		.append(mon).append("-")
		.append(day).append(" ").append(DateUtils.formatTimeStr(end));
		return  sb.toString();
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public int getArm() {
		return arm;
	}
	
	
	public RecordFileForm() {
		super();
	}


	public void setArm(int arm) {
		this.arm = arm;
	}
	public int getBeg() {
		return beg;
	}
	public void setBeg(int beg) {
		this.beg = beg;
	}
	public int getChn() {
		return chn;
	}
	public void setChn(int chn) {
		this.chn = chn;
	}
	public int getChnMask() {
		return chnMask;
	}
	public void setChnMask(int chnMask) {
		this.chnMask = chnMask;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getDevIdno() {
		return devIdno;
	}
	public void setDevIdno(String devIdno) {
		this.devIdno = devIdno;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public int getLoc() {
		return loc;
	}
	public void setLoc(int loc) {
		this.loc = loc;
	}
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public int getRecing() {
		return recing;
	}
	public void setRecing(int recing) {
		this.recing = recing;
	}
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	public int getSvr() {
		return svr;
	}
	public void setSvr(int svr) {
		this.svr = svr;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}