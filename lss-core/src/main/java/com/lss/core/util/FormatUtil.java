package com.lss.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {

	/**
	 * 验证手机号码正确性
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean matchMobile(String mobile) {
		if (ObjectUtil.isEmpty(mobile))
			return false;
		//String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
		String regex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		if (cardId == null) {
			return false;
		}
		char bit = getBankCardCheckCode(cardId
				.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	/**
	 * 验证字符串长度是否大于等于指定长度
	 */
	public static boolean validLength(String str, int length) {

		if (str.length() >= length) {
			return true;
		}
		return false;
	}

	/**
	 * 校验姓名
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkName(String name) {

		boolean flag = false;
		try {
			String check = "^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(name);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 姓名格式化
	 * 
	 * @param name
	 * @return
	 */
	public static String formatName(String name) {

		if (StringUtils.isEmpty(name)) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < name.length(); i++) {
			builder.append("*");
		}
		return name.substring(0, 1) + builder.toString();
	}

	/**
	 * 格式化账号
	 * 
	 * @param account
	 * @return
	 */
	public static String formatAccount(String account) {

		if (StringUtils.isEmpty(account) || account.length() < 3) {
			return null;
		}
		return account.substring(0, 1) + "***"
				+ account.substring(account.length() - 2);
	}

	/**
	 * a为一个带有未知位小数的实数 对其取b位小数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDouble(double a, int b) {
		int x = 0;
		int y = 1;
		for (int i = 0; i < b; i++) {
			y = y * 10;
		}
		x = (int) (a * y);
		System.out.println("x=" + x);
		return (double) x / y;
	}

	/**
	 * 格式化银行卡
	 * 
	 * @param card
	 * @return
	 */
	public static String formatBankCard(String card) {

		if (StringUtils.isEmpty(card)) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		if (card.length() > 6) {
			for (int i = 4; i < card.length() - 4; i++) {
				builder.append("*");
			}
			return card.substring(0, 4) + builder.toString()
					+ card.substring(card.length() - 4);
		}
		return card;
	}

	/**
	 * 格式化身份证
	 * 
	 * @param card
	 * @return
	 */
	public static String formatIDCard(String card) {

		if (StringUtils.isEmpty(card)) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		if (card.length() > 8) {
			for (int i = 4; i < card.length() - 4; i++) {
				builder.append("*");
			}
			return card.substring(0, 4) + builder.toString()
					+ card.substring(card.length() - 4);
		}
		return card;
	}

	/**
	 * 格式化手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static String formatMobile(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		return phone.substring(0, 3) + "****"
				+ phone.substring(7, phone.length());
	}

	public static void main(String[] args) {
		System.out.println(matchMobile("18128841359"));
	}
}
