/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chejet.cloud.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 字符串工具类
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/06
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	/*
	 * public static String toHtml(String txt){ if (txt == null){ return ""; }
	 * return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t",
	 * "&nbsp; &nbsp; "); }
	 */

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得i18n字符串
	 */
	/*
	 * public static String getMessage(String code, Object[] args) {
	 * LocaleResolver localLocaleResolver = (LocaleResolver)
	 * SpringContextHolder.getBean(LocaleResolver.class); HttpServletRequest
	 * request =
	 * ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).
	 * getRequest(); Locale localLocale =
	 * localLocaleResolver.resolveLocale(request); return
	 * SpringContextHolder.getApplicationContext().getMessage(code, args,
	 * localLocale); }
	 */

	/**
	 * 获得用户远程地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具 toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * 
	 * @param s
	 * @return
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具 toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * 
	 * @param s
	 * @return
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具 toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * 
	 * @param s
	 * @return
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 驼峰转下划线 (暂不建议使用toUnderScoreCase)
	 * 
	 * @param camelCaseName
	 * @return
	 */
	public static String toUnderscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * 
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果 例如：row.user.id
	 * 返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 * 
	 * @param objectString
	 * @return
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 转换为utf-8字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * String 是否为空
	 * 
	 * @param aStr
	 * @return
	 */
	public static boolean isEmpty(String aStr) {
		if (aStr == null || aStr.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 参数转换函数
	 * 
	 * @param args
	 * @param parameterMap
	 * @return
	 */
	public static String paramToString(Object[] args, Map<String, String[]> parameterMap) {
		StringBuilder sb = new StringBuilder();
		boolean isJoint = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof JSONObject) {
				JSONObject parse = (JSONObject) JSONObject.parse(args[i].toString());
				if (!isEmpty(parse.getString("password"))) {
					parse.put("password", "*******");
				}
				if (!isEmpty(parse.getString("rePassword"))) {
					parse.put("rePassword", "*******");
				}
				if (!isEmpty(parse.getString("oldPassword"))) {
					parse.put("oldPassword", "*******");
				}
				sb.append(parse.toString());
			} else if (args[i] instanceof String || args[i] instanceof Long || args[i] instanceof Integer
					|| args[i] instanceof Double || args[i] instanceof Float || args[i] instanceof Byte
					|| args[i] instanceof Short || args[i] instanceof Character) {
				isJoint = true;
			} else if (args[i] instanceof String[] || args[i] instanceof Long[] || args[i] instanceof Integer[]
					|| args[i] instanceof Double[] || args[i] instanceof Float[] || args[i] instanceof Byte[]
					|| args[i] instanceof Short[] || args[i] instanceof Character[]) {
				Object[] strs = (Object[]) args[i];
				sb.append("[");
				for (Object str : strs) {
					sb.append(str.toString() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]");

			} else if (args[i] instanceof Object) {
				sb.append(args[i].toString());
			} else {
				continue;
			}
		}
		if (isJoint) {

			for (String key : parameterMap.keySet()) {
				String[] strings = parameterMap.get(key);
				for (String str : strings) {
					sb.append(key + "=" + str + "&");
				}
			}
			if (sb.length() > 0){
				sb.deleteCharAt(sb.length() - 1);
			}
		}

		return sb.toString();
	}
}
