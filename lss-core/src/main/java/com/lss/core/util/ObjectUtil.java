package com.lss.core.util;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @ClassName:ObjectUtils
 * @Description:TODO
 * @date 2015年4月27日
 */
public class ObjectUtil {
	/**
	 * @Title:isEmpty
	 * @Description:集合是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static boolean isEmpty(Collection<?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * @Title:isEmpty
	 * @Description:map是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static boolean isEmpty(Map<?, ?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * @Title:isEmpty
	 * @Description:字符串是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static boolean isEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.toString().trim().length() <= 0;
	}

	/**
	 * @Title:isEmpty
	 * @Description:对象是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static <T> boolean isEmpty(T s) {
		if (null == s) {
			return true;
		}
		return false;

	}

	/**
	 * @Title:isEmpty
	 * @Description:对象是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static <T> boolean isEmpty(T[] s) {
		if (null == s) {
			return true;
		}
		return Array.getLength(s) < 1;
	}

	/**
	 * @Title:isNotEmpty
	 * @Description:集合不为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @date 2015年4月27日
	 */
	public static boolean isNotEmpty(Collection<?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * @Title:isNotEmpty
	 * @Description:map不为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015年4月27日
	 */
	public static boolean isNotEmpty(Map<?, ?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * 字符串不为空 ***********************************
	 * 
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static boolean isNotEmpty(String s) {
		if (null == s) {
			return false;
		}
		return s.toString().trim().length() > 0;
	}

	/**
	 * int 大于等于0 ***********************************
	 * 
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static boolean isNotEmpty(Integer s) {
		if (null == s) {
			return false;
		}
		return s >= 0;
	}

	/**
	 * 对象是否为空 ***********************************
	 * 
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static <T> boolean isNotEmpty(T s) {
		if (null == s) {
			return false;
		}
		return !isEmpty(s);
	}

	/**
	 * 转换list为 id列表 ***********************************
	 * 
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String listToString(Collection<T> t, String keyName) {
		String methodName = "";
		StringBuilder keys = new StringBuilder();
		try {
			for (T t2 : t) {
				methodName = "get" + keyName.substring(0, 1).toUpperCase()
						+ keyName.substring(1);
				Method method = t2.getClass().getDeclaredMethod(methodName);
				Object res = method.invoke(t2);
				if (null != res) {
					keys.append(res);
					keys.append(",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 转换list为 id列表 ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String arrayToString(T[] t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 转换list为 id列表 ***********************************
	 * 
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String listToString(Collection<T> t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}

	/**
	 * 整型转换为4位字节数组
	 * 
	 * @create_date 2015-1-27 下午5:11:58
	 * @param intValue
	 * @return
	 */
	public static byte[] int2Byte(int intValue) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
			// System.out.print(Integer.toBinaryString(b[i])+" ");
			// System.out.print((b[i] & 0xFF) + " ");
		}
		return b;
	}

	/**
	 * 4位字节数组转换为整型
	 * 
	 * @create_date 2015-1-27 下午5:11:47
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte[] b) {
		int intValue = 0;
		// int tempValue = 0xFF;
		for (int i = 0; i < b.length; i++) {
			intValue += (b[i] & 0xFF) << (8 * (3 - i));
			// System.out.print(Integer.toBinaryString(intValue)+" ");
		}
		return intValue;
	}

	/**
	 * @create_date 2014-8-7 上午10:16:59
	 * @param score
	 * @return
	 */
	public static Float parseFloat(String score) {
		if (isNotEmpty(score)) {
			if (isDouble(score)) {
				return Float.valueOf(score);
			}
		}
		return 0f;
	}

	/**
	 * @create_date 2014-8-7 上午10:16:59
	 * @param score
	 * @return
	 */
	public static Integer parseInt(String score) {
		if (isNotEmpty(score)) {
			if (isDouble(score)) {
				return Integer.valueOf(score);
			}
		}
		return 0;
	}

	public static final Pattern integerPattern = Pattern
			.compile("^[-\\+]?[\\d]*$");

	/**
	 * 
	 * @create_date 2014-8-7 上午10:23:15
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		return integerPattern.matcher(str).matches();
	}

	/*
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static final Pattern floatPattern = Pattern
			.compile("^[-\\+]?[.\\d]*$");

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @create_date 2014-8-7 上午10:22:54
	 * @param str传入的字符串
	 * @return是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		return floatPattern.matcher(str).matches();
	}

	/**
	 * @author xiehz
	 * @create_date 2014-8-8 上午11:26:33
	 * @param difficulty
	 * @return
	 */
	public static byte stringToByte(String difficulty) {

		if (ObjectUtil.isNotEmpty(difficulty)) {
			if (difficulty.length() == 1) {
				return Byte.valueOf(difficulty);
			}
		}
		return (byte) 0;
	}

	/**
	 * @author xiehz
	 * @create_date 2014-9-1 下午5:26:29
	 * @param paperIdSb
	 * @return
	 */
	public static String setToString(Set<Integer> set) {
		if (isEmpty(set)) {
			return "";
		}
		String ids = set.toString();
		return ids.substring(1, ids.length() - 1);
	}

	/**
	 * 判断 a 是否在【a,b,c】集合中
	 * 
	 * @create_date 2015-1-27 下午5:12:27
	 * @param org
	 * @param compArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isIn(T org, T... compArray) {
		for (T t : compArray) {
			if (t.equals(org)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数字和字母混合
	 */
	public static final Pattern numberAlphaPattern = Pattern
			.compile("^[A-Za-z0-9]+$");

	public static boolean isNumberAlphaFix(String str) {
		return numberAlphaPattern.matcher(str).matches();
	}

	/**
	 * 
	 * @Title: getPropertyValue
	 * @Description: 读取实体bean属性值
	 * @param @param bean
	 * @param @param propertyName
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@SuppressWarnings("finally")
	public static Object getPropertyValue(Object bean, String propertyName) {
		Object result = null;
		if (propertyName.equals("serialVersionUID")) {
			return result;
		}
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getReadMethod();
			result = m.invoke(bean);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * 
	 * @Title: setProperty
	 * @Description: 设置实体bean的属性值
	 * @param @param bean
	 * @param @param propertyName
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setProperty(Object bean, String propertyName,
			Object value) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getWriteMethod();
			// 设置属性值
			m.invoke(bean, value);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断多个对象是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isNULL(Object ...objs){
		for(Object obj : objs){
			if(obj == null)
				return true;
		}
		return false;		
	}
	
	/**
	 * 判断字符串是否是整数
	 * @param str
	 * @return
	 */
    public static boolean isNumeric(String str){  
        Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(str).matches();     
    }  
    
    
    /**
	 * 判断多个对象是否不为空
	 * @param strs
	 * @return
	 */
	public static boolean isNotNULL(Object ...objs){
		for(Object obj : objs){
			if(obj == null)
				return false;
		}
		return true;		
	}
	
	 /**
		 * 判断多个字符串是否不为空
		 * @param strs
		 * @return
		 */
	public static boolean isEmpty(String ...objs){
			for(String obj : objs){
				if(obj == null || "".equals(obj))
					return true;
			}
			return false;		
	}
	
	/**
	 * 判断多个对象是否为空值
	 * @param objs
	 * @return
	 */
	public static boolean isEmptys(Object ...objs) {
		
		for(Object obj : objs){
			if(obj == null || "".equals(obj))
				return true;
		}
		return false;
	}
}
