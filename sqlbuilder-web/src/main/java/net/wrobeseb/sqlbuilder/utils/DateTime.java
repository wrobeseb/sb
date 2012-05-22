package net.wrobeseb.sqlbuilder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	public static Date getDate() {
		return new Date();
	}
	
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
