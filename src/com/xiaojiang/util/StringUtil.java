package com.xiaojiang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String formatStr = "^(?:(-1|[\\d])+,)*(-1|\\d)+$";
	
	/**
	 * 去掉字符串的最后一个分隔符
	 * @param s 待处理的字符串
	 * @param seperator 分隔符
	 * @return
	 */
	public static final String removeLastSeperator(String s, String seperator){
		if(s.endsWith( seperator )){
        	s = s.substring( 0, s.length()-seperator.length() );
        }
		return s;
	}
	
	/**
	 * 为空的字符串设置默认值
	 * @param s 待处理的字符串
	 * @param nullValue 默认值
	 * @return 处理后的字符串
	 */
	public static final String setNullValue(String s, String nullValue){
		if(s == null){
			s = nullValue;
		}
		return s;
	}
	
	/**
	 * 判断一个字符串是否为NULL或者是空字符串
	 * @param s 待检查的字符串
	 * @return true为空， false表示非空
	 */
	public static final boolean isEmpty(String s){
		if(s == null)
			return true;
		
		if("".equals(s.trim()))
			return true;
		
		if("\"\"".equals(s.trim()))
			return true;
		
		return false;
	}
	
	public static final boolean isNotEmpty(String s){
		return !isEmpty(s);
	}
	
	/** 
     * 判断是否是逗号分隔的数字字符串. 
     */  
    public static boolean isNumStr(String value) {  
    	Pattern pattern=Pattern.compile(formatStr);  
    	Matcher isNum=pattern.matcher(value.trim());
    	
    	return isNum.find(); 
    }
    
    public final static boolean isValidPhone(String phone){
    	if(phone == null)
    		return false;
    	
    	if( "".equalsIgnoreCase(phone.trim()) )
    		return false;
    	
    	//手机号码为11位，并且全部是数字
    	if( phone.length() != 11 || !phone.matches("[0-9]+") )
    		return false;
    	
    	return true;
    }
    
    public static Integer toInteger(String intStr, Integer defaultNullValue){
    	if(intStr == null)
    		return defaultNullValue;
    	return Integer.valueOf(intStr);
    }
    
    public static void main(String[] args) {
    	Pattern pattern=Pattern.compile("^(?:(-1|[\\d])+,)*(-1|\\d)+$");
    	Matcher isNum=pattern.matcher("21,-1,-1,54,60,61,68");
    	
    	boolean n = isNum.find();
    	
    	if(n){
    		System.out.println("success");
    	}else
    		System.out.println("fail");
	}
}
