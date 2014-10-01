package data_center;

import java.util.Calendar;

public class TimeTagGenerator
{
	/** Year/Month/Date */
	public static String generateYearMonthDateStr()
	{	
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		return year + "/" + month + "/" + date;
	}
	
	/** Hour:Min */
	public static String generateHourMinStr()
	{	
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		return hour + ":" + min;
	}
	
	/** Accurate to ms, no separators */
	public static String generateTimeTag()
	{	
		Calendar calendar = Calendar.getInstance();
		 
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int mSec = calendar.get(Calendar.MILLISECOND);
		
		return ("" + year + month + date + hour + min + sec + mSec);
	}
	
}
