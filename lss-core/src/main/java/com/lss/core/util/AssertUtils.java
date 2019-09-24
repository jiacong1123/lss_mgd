package com.lss.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * </p>.
 *
 * @author 彭阳
 * 
 * CreateDate: 2012-2-16
 */
@SuppressWarnings("rawtypes")
public class AssertUtils {

	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String args[]) {
		System.out.println("start");
		// notNullAndEmpty(new String[]{});
		// notNullAndEmpty(new HashMap());
		// notNullAndEmpty(new ArrayList());
		System.out.println("end");
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param object the object
	 * @param message the message
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param object the object
	 * @param message the message
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(String object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		if ("".equals(object))
			throw new IllegalArgumentException(message);

	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param object the object
	 * @param message the message
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Collection object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		notEmpty(object);
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param object the object
	 * @param message the message
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Map object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		notEmpty(object);
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param object the object
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Object object) {
		notNullAndEmpty(object, "数据不能为空！");
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param collection the collection
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Collection collection) {
		notNullAndEmpty(collection, "数据不能为空！");
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param map the map
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(Map map) {
		notNullAndEmpty(map, "数据不能为空！");
	}

	/**
	 * 方法说明：Assert that an object is not <code>null</code> and Empty.
	 *
	 * @param str the str
	 * @author 彭阳
	 */
	public static void notNullAndEmpty(String str) {
		notNullAndEmpty(str, "数据不能为空！");
	}

	/**
	 * 校验返回数据的条数不能大于1条.
	 *
	 * @param count the count
	 * @param message the message
	 * @author 彭阳
	 * @version 1.0
	 */
	public static void notUpdateMoreThanOne(int count, String message) {
		if (count > 1) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 校验返回数据的条数不能大于1条.
	 *
	 * @param count the count
	 * @author 彭阳
	 * @version 1.0
	 */
	public static void notUpdateMoreThanOne(int count) {
		notUpdateMoreThanOne(count, "更新数据大于一条！");
	}

	/**
	 * Assert a boolean expression, throwing
	 * <code>IllegalArgumentException</code> if the test result is
	 * <code>false</code>.
	 * 
	 * <pre class="code">
	 * Assert.isTrue(i &gt; 0, &quot;The value must be greater than zero&quot;);
	 * </pre>
	 *
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert a boolean expression, throwing
	 * <code>IllegalArgumentException</code> if the test result is
	 * <code>false</code>.
	 * 
	 * <pre class="code">
	 * Assert.isTrue(i &gt; 0);
	 * </pre>
	 *
	 * @param expression a boolean expression
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.isNull(value, &quot;The value must be null&quot;);
	 * </pre>
	 *
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.isNull(value);
	 * </pre>
	 *
	 * @param object the object to check
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.notNull(clazz, &quot;The class must not be null&quot;);
	 * </pre>
	 *
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.notNull(clazz);
	 * </pre>
	 *
	 * @param object the object to check
	 */
	public static void notNull(Object object) {
		notNull(object,
				"[Assertion failed] - this argument is required; it must not be null");
	}
	
	/**
	 * 方法说明：不能全部为NULL。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @param message the message
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notAllNull(Object object,Object objectSec,String message) {
		if (object == null &&  objectSec == null) {
				throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 方法说明：不能全部为NULL。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notAllNull(Object object,Object objectSec) {
		if (object == null &&  objectSec == null) {
				throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not all be null");
		}
	}
	
	/**
	 * 方法说明：不能全部为NULL/空字符串。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @param message the message
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notAllNullAndEmpty(String object,String objectSec,String message) {
		if (ObjectUtil.isEmpty(object) && ObjectUtil.isEmpty(objectSec)) {
				throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 方法说明：不能全部为NULL/空字符串。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notAllNullAndEmpty(String object,String objectSec) {
		if (ObjectUtil.isEmpty(object) &&  ObjectUtil.isEmpty(objectSec)) {
				throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not all be null or empty");

		}
	}
	
	/**
	 * 方法说明：不能单独为NULL，可以全部为NULL，或全部不为NULL。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @param message the message
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notSingleNull(Object object,Object objectSec,String message) {
		if (object != null ||  objectSec != null) {
			if (object == null ||  objectSec == null) 
				throw new IllegalArgumentException(message);
		}
	}
	
	
	/**
	 * 方法说明：不能单独为NULL，可以全部为NULL，或全部不为NULL。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notSingleNull(Object object,Object objectSec) {
		if (object != null ||  objectSec != null) {
			if (object == null ||  objectSec == null) 
				throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not be null");
		}
	}
	
	/**
	 * 方法说明：不能单独为NULL/空字符串，可以全部为NULL/空字符串，或全部不为NULL/空字符串。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @param message the message
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notSingleNullAndEmpty(String object,String objectSec,String message) {
		if (!ObjectUtil.isEmpty(object) ||  !ObjectUtil.isEmpty(objectSec)) {
			if (ObjectUtil.isEmpty(object) || ObjectUtil.isEmpty(objectSec)) 
				throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 方法说明：不能单独为NULL/空字符串，可以全部为NULL/空字符串，或全部不为NULL/空字符串。.
	 *
	 * @param object the object
	 * @param objectSec the object sec
	 * @author 彭阳 CreateDate: 2013年11月25日
	 */
	public static void notSingleNullAndEmpty(String object,String objectSec) {
		if (!ObjectUtil.isEmpty(object) ||  !ObjectUtil.isEmpty(objectSec)) {
			if (ObjectUtil.isEmpty(object) || ObjectUtil.isEmpty(objectSec)) 
				throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not be null or empty");

		}
	}

	/**
	 * Assert that the given String is not empty; that is, it must not be
	 * <code>null</code> and not the empty String.
	 * 
	 * <pre class="code">
	 * Assert.hasLength(name, &quot;Name must not be empty&quot;);
	 * </pre>
	 * 
	 * @param text
	 *            the String to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @see StringUtils#hasLength
	 */
	public static void hasLength(String text, String message) {
		if (!hasLengthHelp(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the given String is not empty; that is, it must not be
	 * <code>null</code> and not the empty String.
	 * 
	 * <pre class="code">
	 * Assert.hasLength(name);
	 * </pre>
	 * 
	 * @param text
	 *            the String to check
	 * @see StringUtils#hasLength
	 */
	public static void hasLength(String text) {
		hasLength(
				text,
				"[Assertion failed] - this String argument must have length; it must not be null or empty");
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace
	 * character.
	 * 
	 * <pre class="code">
	 * Assert.hasText(name, &quot;'name' must not be empty&quot;);
	 * </pre>
	 * 
	 * @param text
	 *            the String to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text, String message) {
		if (!hasLengthHelp(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace
	 * character.
	 * 
	 * <pre class="code">
	 * Assert.hasText(name, &quot;'name' must not be empty&quot;);
	 * </pre>
	 * 
	 * @param text
	 *            the String to check
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text) {
		hasText(text,
				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * 
	 * <pre class="code">
	 * Assert.doesNotContain(name, &quot;rod&quot;, &quot;Name must not contain 'rod'&quot;);
	 * </pre>
	 * 
	 * @param textToSearch
	 *            the text to search
	 * @param substring
	 *            the substring to find within the text
	 * @param message
	 *            the exception message to use if the assertion fails
	 */
	public static void doesNotContain(String textToSearch, String substring,
			String message) {
		if (hasLengthHelp(textToSearch) && hasLengthHelp(substring)
				&& textToSearch.indexOf(substring) != -1) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * 
	 * <pre class="code">
	 * Assert.doesNotContain(name, &quot;rod&quot;);
	 * </pre>
	 * 
	 * @param textToSearch
	 *            the text to search
	 * @param substring
	 *            the substring to find within the text
	 */
	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				"[Assertion failed] - this String argument must not contain the substring ["
						+ substring + "]");
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(array, &quot;The array must have elements&quot;);
	 * </pre>
	 *
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void notEmpty(Object[] array, String message) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(array);
	 * </pre>
	 *
	 * @param array the array to check
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(
				array,
				"[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that an array has no null elements. Note: Does not complain if the
	 * array is empty!
	 * 
	 * <pre class="code">
	 * Assert.noNullElements(array, &quot;The array must have non-null elements&quot;);
	 * </pre>
	 *
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an array has no null elements. Note: Does not complain if the
	 * array is empty!
	 * 
	 * <pre class="code">
	 * Assert.noNullElements(array);
	 * </pre>
	 *
	 * @param array the array to check
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array,
				"[Assertion failed] - this array must not contain any null elements");
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(collection, &quot;Collection must have elements&quot;);
	 * </pre>
	 *
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 */

	public static void notEmpty(Collection collection, String message) {
		if (isEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(collection, &quot;Collection must have elements&quot;);
	 * </pre>
	 *
	 * @param collection the collection to check
	 */
	public static void notEmpty(Collection collection) {
		notEmpty(
				collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(map, &quot;Map must have entries&quot;);
	 * </pre>
	 *
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void notEmpty(Map map, String message) {
		if (isEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * 
	 * <pre class="code">
	 * Assert.notEmpty(map);
	 * </pre>
	 *
	 * @param map the map to check
	 */
	public static void notEmpty(Map map) {
		notEmpty(
				map,
				"[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 * 
	 * <pre class="code">
	 * Assert.instanceOf(Foo.class, foo);
	 * </pre>
	 *
	 * @param clazz the required class
	 * @param obj the object to check
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 * 
	 * <pre class="code">
	 * Assert.instanceOf(Foo.class, foo);
	 * </pre>
	 *
	 * @param type the type to check against
	 * @param obj the object to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context.
	 * It should normally end in a ": " or ". " so that the function
	 * generate message looks ok when prepended to it.
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message + "Object of class ["
					+ (obj != null ? obj.getClass().getName() : "null")
					+ "] must be an instance of " + type);
		}
	}

	/**
	 * Assert that <code>superType.isAssignableFrom(subType)</code> is
	 * <code>true</code>.
	 * 
	 * <pre class="code">
	 * Assert.isAssignable(Number.class, myClass);
	 * </pre>
	 *
	 * @param superType the super type to check
	 * @param subType the sub type to check
	 */
	public static void isAssignable(Class superType, Class subType) {
		isAssignable(superType, subType, "");
	}

	/**
	 * Assert that <code>superType.isAssignableFrom(subType)</code> is
	 * <code>true</code>.
	 * 
	 * <pre class="code">
	 * Assert.isAssignable(Number.class, myClass);
	 * </pre>
	 *
	 * @param superType the super type to check against
	 * @param subType the sub type to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context.
	 * It should normally end in a ": " or ". " so that the function
	 * generate message looks ok when prepended to it.
	 */
	@SuppressWarnings("unchecked")
	public static void isAssignable(Class superType, Class subType,
			String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType
					+ " is not assignable to " + superType);
		}
	}

	/**
	 * Assert a boolean expression, throwing <code>IllegalStateException</code>
	 * if the test result is <code>false</code>. Call isTrue if you wish to
	 * throw IllegalArgumentException on an assertion failure.
	 * 
	 * <pre class="code">
	 * Assert.state(id == null, &quot;The id property must not already be initialized&quot;);
	 * </pre>
	 *
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 */
	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * Assert a boolean expression, throwing {@link IllegalStateException} if
	 * the test result is <code>false</code>.
	 * <p>
	 * Call {@link #isTrue(boolean)} if you wish to throw
	 *
	 * @param expression a boolean expression
	 * {@link IllegalArgumentException} on an assertion failure.
	 * 
	 * <pre class="code">
	 * Assert.state(id == null);
	 * </pre>
	 */
	public static void state(boolean expression) {
		state(expression,
				"[Assertion failed] - this state invariant must be true");
	}

	/**
	 * Return <code>true</code> if the supplied Collection is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * 
	 * @param collection
	 *            the Collection to check
	 * @return whether the given Collection is empty
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Determine whether the given array is empty: i.e. <code>null</code> or of
	 * zero length.
	 *
	 * @param array the array to check
	 * @return true, if checks if is empty
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of
	 * length 0. Note: Will return <code>true</code> for a CharSequence that
	 * purely consists of whitespace.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a String that purely consists of
	 * whitespace.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasLength(CharSequence)
	 */
	private static boolean hasLengthHelp(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * Return <code>true</code> if the supplied Map is <code>null</code> or
	 * empty. Otherwise, return <code>false</code>.
	 * 
	 * @param map
	 *            the Map to check
	 * @return whether the given Map is empty
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}
}
