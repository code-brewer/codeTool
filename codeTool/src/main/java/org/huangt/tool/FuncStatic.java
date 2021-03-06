package org.huangt.tool;

import java.util.List;

import org.apache.log4j.Logger;
 
public class FuncStatic {
	private static Logger logger = Logger.getLogger(FuncStatic.class.getName());
 

	public static boolean checkIsEmpty(Object var) {
		if (var == null)
			return true;
		if (var.toString().equals(""))
			return true;
		return false;
	}
	/**
	 * 大,将大写字符串转换为驼峰形式，下划线后面和第一个字母是大写 USER_NAME --> UserName
	 * 
	 * @param s
	 * @return
	 */
	public static String convertUpperCaseToHump(String s) {
		if (FuncStatic.checkIsEmpty(s)){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(s.charAt(0)));
		boolean up = false;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '_') {
				up = true;
			} else {
				if (up) {
					sb.append(Character.toUpperCase(s.charAt(i)));
					up = false;
				} else
					sb.append(Character.toLowerCase(s.charAt(i)));
			}
		}
		return sb.toString();
	}
	/**
	 * 小,将大写字符串转换为驼峰形式，下划线后面和第一个字母是大写 USER_NAME --> userName
	 * 
	 * @param s
	 * @return
	 */
	public static String convertLowerCaseToHump(String s) {
		if (FuncStatic.checkIsEmpty(s)){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toLowerCase(s.charAt(0)));
		boolean up = false;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '_') {
				up = true;
			} else {
				if (up) {
					sb.append(Character.toUpperCase(s.charAt(i)));
					up = false;
				} else
					sb.append(Character.toLowerCase(s.charAt(i)));
			}
		}
		return sb.toString();
	}
	/**
	 * 首字母小写
	 * 
	 * @param s
	 * @return
	 */
	public static String convertLowerCaseFirst(String s) {
		if (FuncStatic.checkIsEmpty(s)){
			return s;
		}
		s = s.substring(0, 1).toLowerCase() + s.substring(1);
	    return  s;
	}
	
	/**
	 * 添加不重复的元素
	 * @param key
	 * @param list
	 * @return
	 */
	public static List<String> add(String key,List<String> list){
		if(!FuncStatic.checkIsEmpty(key) && !list.contains(key)){
			list.add(key);
		}
		return list;
	}
}
