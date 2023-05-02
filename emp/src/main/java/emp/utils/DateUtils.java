package emp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfFullTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfCnDateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:--");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	
	public static String parseYYYYMMDD(Date date) {
		return sdfYMD.format(date);
	}
	
	public static String getTodayStr() {
		return parseYYYYMMDD(new Date());
	}
	
	public static String parseFullTime(Date date) {
		return sdfFullTime.format(date);
	}
	
	public static String parseTime(Date date) {
		return sdfTime.format(date);
	}
	
	public static String getCnDateTimeStr() {
		return sdfCnDateTime.format(new Date());
	}
	
	/**
	 * 判断是否迟到
	 * @param checkinTime
	 * @return 返回true则为迟到
	 */
	public static boolean checkLate(Date checkinTime) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 9);//9:00上班
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return checkinTime.after(cal.getTime());
	}
	
	/**
	 * 判断是否早退
	 * @param checkinTimed
	 * @return 返回true则为早退
	 */
	public static boolean checkLeave(Date checkinTime) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 18);//18:00下班
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return checkinTime.before(cal.getTime());
	}
	
	public static String getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return sdfYMD.format(cal.getTime());
	}
	
	public static String getMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
		return sdfYMD.format(cal.getTime());
	}
}
