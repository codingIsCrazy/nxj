package com.nongxiangjie.util;

import java.util.HashMap;
import java.util.Map;

public class ApiJsonUtil {

	public final static String API_STATUS = "status";
	public final static String API_CODE = "code";
	public final static String API_MESSAGE = "message";
	
	public final static String API_DATA = "data";
	
	public static Map<String, Object> getApiData(int code,Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		
		//状态信息
		String  errorMessage = ApiMessage.getErrorMessage(code);
		Map<String, Object> statusMap = new HashMap<String,Object>();
		statusMap.put(API_CODE, code);
		statusMap.put(API_MESSAGE, errorMessage);
		
		result.put(API_STATUS, statusMap);
		if(data != null){
			result.put(API_DATA, data);
		}
		
		return result;
		
	}
	
	
	
	public static Map<String, Object> getServerError(){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> error = new HashMap<String,Object>();
		error.put(API_CODE, ApiMessage.API_SERVER_ERROR_CODE);
		error.put(API_MESSAGE, ApiMessage.getErrorMessage(ApiMessage.API_SERVER_ERROR_CODE));
		
		result.put(API_STATUS, error);
		return error;
	}
	
	
	
	/**
	 * 判断token是否为空
	 */
	public static boolean tokenIsEmpty(String token){
		if(token != null && !"".equals(token)){
			return true;
		}
		return false;
	}
	

	
	
}
