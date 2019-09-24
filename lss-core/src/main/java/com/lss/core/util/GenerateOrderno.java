package com.lss.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成工单号
 *
 */
public class GenerateOrderno {
	private static GenerateOrderno goInstance = null;

	private GenerateOrderno() {

	}

	public static GenerateOrderno getInstance() {
		if (goInstance == null) {
			goInstance = new GenerateOrderno();
		}
		return goInstance;
	}

	public static Integer ordersInt = 0;// 全局数

	/**
	 * 工单号
	 * 
	 * @return
	 */
	public String GenerateOrder() {
		ordersInt++;// 自增
		Integer countInteger = 4 - ordersInt.toString().length();// 算补位
		String bu = "";
		for (int i = 0; i < countInteger; i++) {// 补字符串
			bu += "0";
		}
		bu += ordersInt.toString();
		if (ordersInt > 9999) {
			ordersInt = 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");// 时间戳
		String str = sdf.format(new Date());
		return str + bu;
	}
	
	public static void main(String[] args) {
		System.out.println(GenerateOrderno.getInstance().GenerateOrder());
	}
}
