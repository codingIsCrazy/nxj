package com.nongxiangjie.util;

import sun.launcher.resources.launcher;

public class ApiMessage {

	public static final  String API_SUCCESS = "成功";
	public static final int API_SUCCESS_CODE = 0;
	
	public static final String API_SERVER_ERROR = "服务器错误";
	public static final int API_SERVER_ERROR_CODE = 999;
	
	public static final String API_USER_EXIST = "用户名已存在";
	public static final int API_USER_EXIST_CODE = 101;
	
	public static final String API_USER_NAME_NOT_EMPTY = "用户名不能为空";
	public static final int API_USER_NAME_NOT_EMTPY_CODE = 102;
	
	public static final String API_USER_PASSWORD_NOT_EMPTY = "密码不能为空";
	public static final int API_USER_PASSWORD_NOT_EMPTY_CODE = 103;
	
	public static final String API_USER_PASSWORD_ERROR = "密码错误";
	public static final int API_USER_PASSWORD_ERROR_CODE = 104;
	
	public static final String API_USER_TOKEN_ERROR = "token已失效";
	public static final int API_USER_TOKEN_ERROR_CODE = 105;
	
	public static final String API_USER_NOT_EXIST = "用户不存在";
	public static final int API_USER_NOT_EXIST_CODE = 106;
	
	public static String getErrorMessage(int code){
		
		String errorMessage = null;
		
		switch (code) {
		//成功
		case API_SUCCESS_CODE: 
			errorMessage = API_SUCCESS;
			break;
		//用户已存在
		case API_USER_EXIST_CODE:
			errorMessage = API_USER_EXIST;
			break;
		//用户名不能为空
		case API_USER_NAME_NOT_EMTPY_CODE:
			errorMessage = API_USER_NAME_NOT_EMPTY;
			break;
		//密码不能为空
		case API_USER_PASSWORD_NOT_EMPTY_CODE:
			errorMessage = API_USER_PASSWORD_NOT_EMPTY;
			break;
		//密码错误
		case API_USER_PASSWORD_ERROR_CODE:
			errorMessage = API_USER_PASSWORD_ERROR;
			break;
		//token已经失效
		case API_USER_TOKEN_ERROR_CODE:
			errorMessage = API_USER_TOKEN_ERROR;
			break;
		//token已经失效
		case API_USER_NOT_EXIST_CODE:
			errorMessage = API_USER_NOT_EXIST;
			break;
		//服务器内部错误
		case API_SERVER_ERROR_CODE: 
			errorMessage = API_SERVER_ERROR;
			break;
		default:
			break;
		}
		
		return errorMessage;
	}
	
	
}
