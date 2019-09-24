package com.lss.core.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.lss.core.emus.WorkTagType;





/**
 * 
 * @ClassName: DateUtils
 * @Description: 描述：日期处理常用类
 * @author xiehz
 * @date 2015年5月22日 上午9:55:21
 *
 */
public class DateUtils {

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");// 数据库使用的日期格式
	public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat yyyyMMddHHmmssDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat dateMonthFormatDB = new SimpleDateFormat("yyyyMM");// 数据库使用的日期格式
	public static SimpleDateFormat dateMonthFormatYYYY_MM = new SimpleDateFormat("yyyy-MM");// 数据库使用的日期格式
	
	public static String formatDateTime(Date date) {
		return dataTimeFormat.format(date);
	}

	public static String formatdateFormat(Date date){
		return dateFormat.format(date);
	}
	
	public static String formatdateFormatDB(Date date){
		return dateFormatDB.format(date);
	}
	
	public static String formatdateMonthFormatDB(Date date){
		return dateMonthFormatDB.format(date);
	}
	
	public static String formatdateMonthFormatYYYY_MM(Date date){
		return dateMonthFormatYYYY_MM.format(date);
	}
	
	
	/**
	 * 
	 * @param dateStr yyyyMMdd 格式的日期
	 * @return
	 */
	public static Date parseDateFormatDB(String dateStr){
		Date date = null;
		try {
			date = dateFormatDB.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * 生日计算年龄
	 * 
	 * @param date
	 * @return
	 */
	public static int birthdateToAge(Date date) {
		int reVal = 0;
		int numOne = 0;
		int numTwo = 0;
		Calendar calOne = Calendar.getInstance();
		Calendar calTwo = Calendar.getInstance();
		calOne.setTime(date);
		calTwo.setTime(new Date());
		numTwo = calTwo.get(1);
		numOne = calOne.get(1);
		reVal = numTwo - numOne;
		return reVal;
	}

	/**
	 * 
	 * 功能：生成日期yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDbDate() {
		return dateFormatDB.format(new Date());
	}
	
	/**
	 * 功能：生成日期yyyy-MM-dd
	 * @return
	 */
	public static String getdateFormatDbDate() {
		return dateFormat.format(new Date());
	}

	/**
	 * 
	 * 功能：把日期yyyy-MM-dd格式转换成yyyyMMDD日期格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String convertClientDateToDbDate(String dateStr) {
		String dbDateStr = null;
		try {
			dbDateStr = dateFormatDB.format(dateFormat.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbDateStr;
	}

	/**
	 * 
	 * 功能：解析数据库中的日期字符串 格式:yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 功能：格式化日期字符串 例如：2008-8-8 转换为2008-08-08
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrFormat(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能：解析数据库中的时间字符串 格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateTime(String dateTimeStr) {
		Date date = null;
		try {
			date = dataTimeFormat.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static int calcDays(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormat.parse(startDate).getTime();
			long end = dateFormat.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 *增加天数
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);
		date = c.getTime(); 
		return date;
	}
	
	/**
	 *增加天数
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addMonths(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		date = c.getTime(); 
		return date;
	} 
	
	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *            日期
	 * @param minute
	 *            分钟
	 * @return 返回相加后的日期
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minute) * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：添加指定秒杀的时间
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		long s = date.getTime();
		s = s + second * 1000;
		return new Date(s);
	}

	/**
	 * 功能：指定日期减去指定天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：分钟相差 minute的时间值
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateByMinuteAdd(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 功能:两个日期相隔天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / (24 * 3600 * 1000);
		return (int) s;
	}

	/***
	 * 
	 * @Title: diffMinute
	 * @author: echao.wang Create at: 2014年9月15日 下午2:40:51
	 * @Description: 计算两个时间间隔的分钟数
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return
	 * @return int
	 */
	public static int diffMinute(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / (60 * 1000);
		return (int) s;
	}

	/**
	 * 功能：传入时间按所需格式返回时间字符串
	 * 
	 * @param date
	 *            java.util.Date格式
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date == null) {
				date = new Date();// 如果时间为空,则默认为当前时间
			}
			if (format == null || format.trim().equals("")) {// 默认格式化形式
				format = "yyyy-MM-dd";
			}
			DateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			result = df.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 功能：传入时间按所需格式返回时间字符串
	 * 
	 * @param date
	 *            java.util.Date格式
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		String result = "";
		try {
			if (date == null) {
				return result; // 时间为空,返回空
			}
			if (format == null || format.trim().equals("")) {// 默认格式化形式
				format = "yyyy-MM-dd";
			}
			DateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			result = df.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 功能：传入时间字符串按所需格式返回时间
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param format
	 *            跟传入dateStr时间的格式必须一样 yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日
	 *            HH时mm分ss秒
	 * @return
	 */
	public static Date format(String dateStr, String format) {
		if (dateStr == null || dateStr.trim().equals("")) {
			return new Date();
		}
		if (format == null || format.trim().equals("")) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		try {
			DateFormat f = new SimpleDateFormat(format);
			date = f.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 功能：时间字符串格式转换
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param format
	 *            时间字符串的格式
	 * @param toFormat
	 *            格式为的格式
	 * @return
	 */
	public static String format(String dateStr, String format, String toFormat) {
		return format(format(dateStr, format), toFormat);
	}

	/**
	 * 功能：格式化rss的时间 输入:
	 * 
	 * @param date
	 * @return
	 */
	public static String formatRssDate(Date date) {
		if (date == null) {
			date = new Date();// 如果时间为空,则默认为当前时间
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		SimpleTimeZone zone = new SimpleTimeZone(8, "GMT");
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}

	/**
	 * 功能：返回年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);

	}

	/**
	 * 功能：返回月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能：返回日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能：返回小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能：返回分
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 功能：返回星期 1：星期一，2:星期二 ... 6:星期六 7:星期日
	 * 
	 * @param date
	 * @return
	 */
	public static int getChinaWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			return 7;
		} else {
			return week;
		}
	}

	/**
	 * 功能：返回秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond2(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 功能：返回毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 功能：获取当前月的第一天日期
	 * 
	 * @return
	 */
	public static Date getMonFirstDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	/**
	 * 功能：获取当前月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonLastDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date), 1);

		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}

	/**
	 * 指定某个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeMonFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	/**
	 * 指定某个月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeMonLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(getYear(date), getMonth(date), 1);
		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}

	/**
	 * 功能：获取当前日期 格式:2008-02-02 23:11:10
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		return dataTimeFormat.format(date);
	}

	/**
	 * 功能：获取当前日期 格式:20101010
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 功能：根据生日获取年龄
	 * 
	 * @return
	 * @throws ParseException
	 */
	public int[] BirthdayGetAge(String birthday) throws ParseException {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birday = sdf.parse(birthday);// 当前对当前的情况
		return this.BirthdayGetAge(birday);
	}

	/**
	 * 功能：根据生日获取年龄
	 * 
	 * @return
	 */
	public int[] BirthdayGetAge(Date birday) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(cal.getTime());// 当前时间
		java.util.Date today = formatter.parse(mDateTime);
		int age = today.getYear() - birday.getYear();
		int MM = today.getMonth() - birday.getMonth();
		int day = today.getDate() - birday.getDate();
		if (MM < 0) {
			MM = 12 + MM;
		}
		if (day < 0) {
			MM = MM - 1;
			day = 31 + day;
		}
		if (today.getMonth() == birday.getMonth()

		&& today.getDate() == birday.getDate()

		&& birday.getYear() % 4 != 0 && today.getYear() != 0

		&& birday.getMonth() != 1 && today.getMonth() != 1) {// 月份和日期都与当前时间相同(除去生日和当前年是闰年，并且是二月的情况)

		} else if (today.getMonth() < birday.getMonth()) {// 生日的月份大于当前时间的月份

			age = age - 1;

		} else if (birday.getMonth() == 1 && birday.getDate() == 29// 生日是闰年，当前年不一定是闰年

				&& today.getMonth() == 1) {// 生日是闰年的情况,并且本月是二月

			if (today.getYear() % 4 == 0) {// 当前年是闰年 2月有二十九
				if (today.getDate() < birday.getDate()) {//
					age = age - 1;
				}
			} else {// 当前是年二月是二十八天
				if (today.getDate() < birday.getDate() - 1) {
					age = age - 1;
				}
			}
		} else if (today.getMonth() == 1 && today.getDate() == 29

		&& birday.getMonth() == 1) { // 当前年是闰年，生日年不一定是闰年

			if (birday.getYear() % 4 == 0) {// 生日年是闰年 二月有二十九天

				if (today.getDate() < birday.getDate()) {//

					age = age - 1;
				}
			} else {// 生日年二月是二十八天

				if (today.getDate() + 1 < birday.getDate()) {

					age = age - 1;

				}
			}
		} else if (today.getMonth() > birday.getMonth()) {// 不是同一年，生日的月份不大于当前的月份的情况

		} else if (today.getDate() < birday.getDate()) {// 正常的日期(非闰年) 当前 小于 出生
			age = age - 1;
		} else if (today.getDate() == birday.getDate()) {// 当前 等于 出生

		} else {
		}
		System.out.println(age + "岁，" + MM + "月，" + day + "天");
		return new int[] { age, MM, day };
	}

	/**
	 * 获取两个时间的间隔，只精确到分钟，如果end早于start，则返回"小于一分钟"
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getInterval(Date start, Date end) {
		long l = end.getTime() - start.getTime();
		// long day=l/(24*60*60*1000);
		long hour = (l / (60 * 60 * 1000));
		long min = ((l / (60 * 1000)) - hour * 60);
		long s = (l / 1000 - hour * 60 * 60 - min * 60);
		if (hour < 0 || min < 0) {
			return "0:0:0";
		}
		/*
		 * String hourStr = hour+""; if(hour<10){ hourStr = "0"+hourStr; }
		 */
		String minStr = min + "";
		if (min < 10) {
			minStr = "0" + min;
		}
		String sStr = s + "";
		if (s < 10) {
			sStr = "0" + s;
		}

		return hour + ":" + minStr + ":" + sStr;
	}

	/**
	 * date需要已yyyy-MM-dd方式传值 根据传来的时间date，得到之后的amount天 2014-05-31，amount
	 * =1则返回2014-06-01
	 * 
	 * @param date
	 * @return
	 */
	public static String getAfterDate(String date, int amount) {
		if (ObjectUtil.isEmpty(date)) {
			return null;
		}
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ca.setTime(sdf.parse(date));
			ca.add(Calendar.DATE, amount);
			String tomorrow = DateFormatUtils.format(ca, "yyyy-MM-dd");
			return tomorrow;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算两个时间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 时间转换成yyyyMMddHHmmss格式
	 * @param date
	 * @return
	 */
	public static String dateToyyyyMMddHHmmss(Date date) {
		String result = "";
		try {
			if (date == null) {
				return result; // 时间为空,返回空
			}
			DateFormat df = yyyyMMddHHmmssDateTimeFormat;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			result = df.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 时间戳转换成yyyy-MM-dd hh:mm:ss时间格式
	 * @param timestamp
	 * @return
	 */
	public static String timestampToTimeStr(long timestamp){
		
		String date = dataTimeFormat.format(new Date(timestamp));
		return date;
	}
	
	/**
	 * 时间戳转换成yyyy-MM-dd hh:mm:ss时间格式
	 * @param timestamp
	 * @return
	 */
	public static Date timestampToTime(long timestamp){
		
		String date = dataTimeFormat.format(new Date(timestamp));
		return parseDateTime(date);
	}
	
	/**
	 * 时间戳转换成yyyy-MM-dd hh:mm:ss时间格式
	 * @param timestamp
	 * @return
	 */
	public static Date timestampToTime(String timestamp){
		
		String date = dataTimeFormat.format(new Date(Long.parseLong(timestamp)));
		return parseDateTime(date);
	}

	/**
	 * 对比两个时间相差几分钟
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public  static long betweenTimes(Date starttime, Date endtime){
				 
		long seconds = endtime.getTime() - starttime.getTime();
		 
		return seconds/60/1000;
	}
	
	/**
	 * 两个日期相差天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int betweenDays(Date date1, Date date2){
		
		if (date1 == null || date2 == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 校验是否合适时间
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isLegalTime(){
		
		
		Date date = new Date();
		Date date_morning_begin = (Date) date.clone();
		Date date_morning_end = (Date) date.clone();
		Date date_afternoon_begin = (Date) date.clone();
		Date date_afternoon_end = (Date) date.clone();
		
		date_morning_begin.setHours(9);
		date_morning_begin.setMinutes(0);
		date_morning_begin.setSeconds(0);
		
		date_morning_end.setHours(11);
		date_morning_end.setMinutes(30);
		date_morning_end.setSeconds(0);
		
		date_afternoon_begin.setHours(13);
		date_afternoon_begin.setMinutes(0);
		date_afternoon_begin.setSeconds(0);
		
		date_afternoon_end.setHours(15);
		date_afternoon_end.setMinutes(0);
		date_afternoon_end.setSeconds(0);
		
		if((date.after(date_morning_begin) && date.before(date_morning_end))
				||(date.after(date_afternoon_begin) && date.before(date_afternoon_end))){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取时间日期部分
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getDateDay(Date dt) {
		return parseDate(formatdateFormat(dt));
	}
	
	
	public static void main(String[] args) {
		System.out.println(WorkTagType.hlm_url.getType());
		Date now=getDateDay(new Date());
		Date netDay=addDate(now, 1);
		System.out.println(now);
		System.out.println(netDay);
		
		Date yesterday = DateUtils.addDays(new Date(), -1);
		System.out.println(DateUtils.format(yesterday, YYYYMMDDHHMMSS));
		
		Date preMonth=addMonths(new Date(), -1);
		System.out.println(DateUtils.format(preMonth, YYYYMMDDHHMMSS));
		System.out.println(DateUtils.formatdateMonthFormatDB(preMonth));
		
		String dbStr="20190501";
		Date dateMonth=DateUtils.parseDateFormatDB(dbStr);
		System.out.println(DateUtils.format(dateMonth, YYYYMMDDHHMMSS));
		
	}
	
}
