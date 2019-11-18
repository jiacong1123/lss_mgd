package com.lss.core.util;

import java.util.Random;

public class RandomString {
	public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	public static final String SOURCESNUM = "1234567890";

	/**
	 * 随机生成秘钥
	 *
	 * @param random
	 *            the random number generator.
	 * @param characters
	 *            the characters for generating string.
	 * @param length
	 *            the length of the generated string.
	 * @return
	 */
	public static String generateString(int length) {
		Random random = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
		}
		return new String(text);
	}

	/**
	 * 随机生成帐号
	 *
	 * @param random
	 *            the random number generator.
	 * @param characters
	 *            the characters for generating string.
	 * @param length
	 *            the length of the generated string.
	 * @return
	 */
	public static String generateStringNum(int length) {
		Random random = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = SOURCESNUM.charAt(random.nextInt(SOURCESNUM.length()));
		}
		return new String(text);
	}
}