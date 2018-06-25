package com.springbootdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class FormUtil {
	public static final char UNDERLINE = '_';

	private static final Logger logger = LoggerFactory.getLogger(FormUtil.class);

	public static String[] arrayUnique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将一个Bean属性赋值给另一个Bean
	 *
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> copyProperties(List srcBeans, Class targetClass) {
		List<T> targetBeans = new ArrayList<T>();
		try {
			for (int i = 0; i < srcBeans.size(); i++) {
				T target = (T) targetClass.newInstance();
				BeanUtils.copyProperties(srcBeans.get(i), target);
				targetBeans.add(target);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return targetBeans;
	}

	/**
	 * 将一个Bean转换为Map
	 *
	 * @param obj
	 * @return
	 */
	public static <T> Map<String, Object> populate(T bean) {
		if (bean == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}

	public static <T> Map<String, Object> populate(T bean, Map<String, Object> map) {
		if (bean == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 *
	 * @param clazz
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	public static <T> T populate(Class<T> clazz, Map<String, Object> map, boolean delUnderLine)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException,
			ParseException {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
		T obj = clazz.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			descriptor.getPropertyType();
			String propertyName = delUnderLine ? camelToUnderline(descriptor.getName()) : descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args = setValue(descriptor, args, value);
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	private static Object[] setValue(PropertyDescriptor descriptor, Object[] args, Object mapValue)
			throws ParseException {
		if (descriptor.getPropertyType() == Integer.class || descriptor.getPropertyType() == int.class) {
			if (mapValue instanceof Integer) {
				args[0] = mapValue;
			} else {
				args[0] = Integer.parseInt((String) mapValue);
			}
			// 当set方法中的参数为Date
		} else if (descriptor.getPropertyType() == Date.class) {
			if (mapValue instanceof Date) {
				args[0] = mapValue;
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				args[0] = sdf.parse((String) mapValue);
			}
			// 当set方法中的参数为Float
		} else if (descriptor.getPropertyType() == double.class || descriptor.getPropertyType() == Double.class) {
			if (mapValue instanceof Double) {
				args[0] = mapValue;
			} else {
				args[0] = Double.parseDouble((String) mapValue);
			}
			// 当set方法中的参数为其他
		} else if (descriptor.getPropertyType() == String.class) {

			if (mapValue instanceof String[]) {

				String[] tempArray = (String[]) mapValue;
				String result = "";
				for (int m = 0; m < tempArray.length; m++) {
					result = result + tempArray[m] + ",";
				}
				result = result.substring(0, result.length() - 1);
				args[0] = result;

			} else {
				args[0] = (String) mapValue;
			}
		}
		return args;
	}

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 *
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}
