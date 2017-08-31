package com.spzh.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * gps状态
 * @author hyq
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GpsForm {
	private String id; //设备号
	private String vid;//车牌号
	private int lng; //经度 如果设备定位无效，值为0。 
	private int lat; //纬度 如果设备定位无效，值为0。
	private int ft; //厂家类型
	private int sp;//速度
	private int ol;//在线状态
	private String gt;//gps上传时间
	private int pt;//通信协议类型
	private int dt;//硬盘类型 1表示SD卡，2表示硬盘，3表示SSD卡。
	private int ac;//音频类型
	private int fdt;//厂家子类型
	private int net;//网络类型 1表示3G，2表示WIFI。
	private String gw;//网关服务器编号
	private String s1;//四个状态
	private String s2;
	private String s3;
	private String s4; //s4:25位	超时停车(平台产生)  s4:14位 超时停车
    private int t1;//温度传感器 
	private int t2;
	private int t3;
	private int t4;
	private int hx; //方向 正北方向为0度，顺时针方向增大，最大值360度。
	
	private String mlng;//经过转换后的经度
	private String mlat;//经过转换后的纬度
	
	private int pk; //停车时长 单位: 秒。
	private int lc; //里程 单位米
	private int yl; //油量 单位: 升，使用中需先除以100。
	
	private String imei;
	private String imsi;
	private String hv;
	private String sv;
	private String po;
	private int lid;
	private int drid;
	private int dct;
	private int sfg;
	private int snm;
	private int sst;
	private int or;
	private int os;
	private int ov;
	private int ojt;
	private int ost;
	private int ojm;
	
	
	 /**
     * 将int型整数转成32位的2进制形式
     * @param num
     * @return String
     */
    public static String toFullBinaryString(int num)
    {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++){
            chs[Integer.SIZE - 1 - i] = (char) ((num >> i & 1) + '0');
        }
        return new String(chs);
    }
    public static void main(String[] args) {
		String str = toFullBinaryString(8);
		System.out.println(str);
	}
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getHv() {
		return hv;
	}
	public void setHv(String hv) {
		this.hv = hv;
	}
	public String getSv() {
		return sv;
	}
	public void setSv(String sv) {
		this.sv = sv;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getDrid() {
		return drid;
	}
	public void setDrid(int drid) {
		this.drid = drid;
	}
	public int getDct() {
		return dct;
	}
	public void setDct(int dct) {
		this.dct = dct;
	}
	public int getSfg() {
		return sfg;
	}
	public void setSfg(int sfg) {
		this.sfg = sfg;
	}
	public int getSnm() {
		return snm;
	}
	public void setSnm(int snm) {
		this.snm = snm;
	}
	public int getSst() {
		return sst;
	}
	public void setSst(int sst) {
		this.sst = sst;
	}
	public int getOr() {
		return or;
	}
	public void setOr(int or) {
		this.or = or;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public int getOv() {
		return ov;
	}
	public void setOv(int ov) {
		this.ov = ov;
	}
	public int getOjt() {
		return ojt;
	}
	public void setOjt(int ojt) {
		this.ojt = ojt;
	}
	public int getOst() {
		return ost;
	}
	public void setOst(int ost) {
		this.ost = ost;
	}
	public int getOjm() {
		return ojm;
	}
	public void setOjm(int ojm) {
		this.ojm = ojm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getFt() {
		return ft;
	}
	public void setFt(int ft) {
		this.ft = ft;
	}
	public int getSp() {
		return sp;
	}
	public void setSp(int sp) {
		this.sp = sp;
	}
	public int getOl() {
		return ol;
	}
	public void setOl(int ol) {
		this.ol = ol;
	}
	public String getGt() {
		return gt;
	}
	public void setGt(String gt) {
		this.gt = gt;
	}
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
	}
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public int getAc() {
		return ac;
	}
	public void setAc(int ac) {
		this.ac = ac;
	}
	public int getFdt() {
		return fdt;
	}
	public void setFdt(int fdt) {
		this.fdt = fdt;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	
	public String getS1() {
		try {
			int ss1 = Integer.parseInt(s1);
			if(ss1>0){
				return toFullBinaryString(ss1);
			}
		}catch (Exception e){
			}
		return s1+"";
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		try {
			int ss2 = Integer.parseInt(s2);
			if(ss2>0){
				return toFullBinaryString(ss2);
			}
		}catch (Exception e){
			}
		return s2+"";
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		try {
			int ss3 = Integer.parseInt(s3);
			if(ss3>0){
				return toFullBinaryString(ss3);
			}
		}catch (Exception e){
			}
		return s3+"";
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public String getS4() {
		try {
			int ss4 = Integer.parseInt(s4);
			if(ss4>0){
				return toFullBinaryString(ss4);
			}
		}catch (Exception e){
			}
		return s4+"";
	}

	public void setS4(String s4) {
		this.s4 = s4;
	}

	public int getT1() {
		return t1;
	}
	public void setT1(int t1) {
		this.t1 = t1;
	}
	public int getT2() {
		return t2;
	}
	public void setT2(int t2) {
		this.t2 = t2;
	}
	public int getT3() {
		return t3;
	}
	public void setT3(int t3) {
		this.t3 = t3;
	}
	public int getT4() {
		return t4;
	}
	public void setT4(int t4) {
		this.t4 = t4;
	}
	public int getHx() {
		return hx;
	}
	public void setHx(int hx) {
		this.hx = hx;
	}
	public String getMlng() {
		return mlng;
	}
	public void setMlng(String mlng) {
		this.mlng = mlng;
	}
	public String getMlat() {
		return mlat;
	}
	public void setMlat(String mlat) {
		this.mlat = mlat;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getLc() {
		return lc;
	}
	public void setLc(int lc) {
		this.lc = lc;
	}
	public int getYl() {
		return yl;
	}
	public void setYl(int yl) {
		this.yl = yl;
	}
	
}
