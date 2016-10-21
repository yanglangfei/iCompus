package com.example.administrator.myapplication.icompus.utils;

import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static String getDate(long time){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
		String date = simpleDateFormat.format(time);
		return date;
	}
	
	public static String getTime(long time){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
		String strTime = simpleDateFormat.format(time);
		return strTime;
	}

}
