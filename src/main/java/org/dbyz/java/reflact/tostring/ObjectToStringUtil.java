package org.dbyz.java.reflact.tostring;

import java.lang.reflect.Field;

/**
 * toString工具类
 *
 * @ClassName: ObjectToStringUtil
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ObjectToStringUtil {
	public static String toString(Object obj) throws IllegalArgumentException,
			IllegalAccessException {
		Class<? extends Object> clazz = obj.getClass();
		StringBuffer result = new StringBuffer();

		result.append(clazz.getSimpleName()).append(" [");
		for (Field field : clazz.getDeclaredFields()) {
			result.append(field.getName()).append("=");
			field.setAccessible(true);
			result.append(field.get(obj)).append(", ");
		}
		result.append("]");

		int end = result.lastIndexOf(",");
		if (end > -1) {
			return result.substring(0, end) + "]";
		}
		return result.toString();
	}
}
