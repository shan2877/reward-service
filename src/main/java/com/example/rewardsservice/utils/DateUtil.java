package com.example.rewardsservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Integer getDateValue(Date date, int expectedPart)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(expectedPart);
	}
	
	public static Date getDateFromString(String dateStr) throws ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("MM-dd-yyyy");
		return formatter1.parse(dateStr);
	}
}
