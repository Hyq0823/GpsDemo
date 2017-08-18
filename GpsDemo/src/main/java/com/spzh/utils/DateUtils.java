/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.spzh.utils;

import java.text.ParseException;



/**
 */
public class DateUtils  {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


	
	  public static String formatTimeStr(Integer second) {
		  if(second == null ) return "";
	        int hour = 0;
	        int minute = 0;
	        if (second > 60) {
	            minute = second / 60;
	            second = second % 60;
	        }
	        if (minute > 60) {
	            hour = minute / 60;
	            minute = minute % 60;
	        }
	        return (getTwoLength(hour) + ":" + getTwoLength(minute)  + ":"  + getTwoLength(second));
	    }
	  private static String getTwoLength(final int data) {
	        if(data < 10) {
	            return "0" + data;
	        } else {
	            return "" + data;
	        }
	    }

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		//String word = DateUtils.formatDateByWord(Long.parseLong("1491364469000"));
//		System.out.println(word);
	}
}
