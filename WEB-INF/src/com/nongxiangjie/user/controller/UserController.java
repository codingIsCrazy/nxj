package com.nongxiangjie.user.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import util.DateUtil;
import util.MD5Util;
import util.UuidUtil;

import com.nongxiangjie.user.dto.User;
import com.nongxiangjie.user.service.UserService;
import com.nongxiangjie.util.ApiJsonUtil;
import com.nongxiangjie.util.ApiMessage;
import com.nongxiangjie.util.ImageUtil;



/**
 * 用户
 * @author liujianjun
 *
 */
@Controller
@RequestMapping(value="/api/v1/userController")
public class UserController {

	final static Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value="/userIsExist",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userIsExist(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		int apiCode = 0;
		
		String name = request.getParameter("name");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		try{
			if(name == null || "".equals(name.trim())){
				apiCode = ApiMessage.API_USER_NAME_NOT_EMTPY_CODE;
			}else{
				User user = userService.getUser(map);
				if(user != null){
					apiCode = ApiMessage.API_USER_EXIST_CODE;
				}else{
					apiCode = ApiMessage.API_SUCCESS_CODE;
				}
			}
			
			result = ApiJsonUtil.getApiData(apiCode, null);
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
	
	}
	
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="/signIn",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> signIn(HttpServletRequest request,HttpServletResponse response,User user){
		
		
		Map<String, Object> result = null;
		
		try{
			
			//Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); 
		    //Matcher nameMatcher = p.matcher(user.getName()); 
		    //Matcher passwordMatcher = p.matcher(user.getPassword());
			
			if(user.getName() == null || user.getName().equals("")){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_NAME_NOT_EMTPY_CODE, null);
				return result;
			}
			
			if(user.getPassword() == null || user.getPassword().equals("")){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_PASSWORD_NOT_EMPTY_CODE, null);
				return result;
			}
			
			
			Map<String, Object> isExist = new HashMap<String,Object>();
			isExist.put("name", user.getName());
			User user_ = userService.getUser(isExist);
			if(user_ != null){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_EXIST_CODE, null);
				return result;
			}
				
			
			String id = UuidUtil.getUniqueUUid();
			String token = UuidUtil.getUniqueUUid();
			user.setId(id);
			user.setCreateDate(DateUtil.getNowTimestamp());
			user.setToken(token);
			user.setStatus(User.STATUS_NORMAL);
			user.setPassword(MD5Util.string2MD5(user.getPassword()));
			
			userService.addUser(user);
			
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("id", id);
			data.put("token", token);
			
			
			result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
	
	}
	
	
	
	/**
	 * 登录
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> result = null;
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try{
			
			if(name == null || name.equals("")){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_NAME_NOT_EMTPY_CODE, null);
				return result;
			}
			
			if(password == null || password.equals("")){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_PASSWORD_NOT_EMPTY_CODE, null);
				return result;
			}
			
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("name", name);
			User user = userService.getUser(param);
			
			if(user != null){
				if(!user.getPassword().equals(MD5Util.string2MD5(password))){
					result = ApiJsonUtil.getApiData(ApiMessage.API_USER_PASSWORD_ERROR_CODE, null);
					return result;
				}else{
					Map<String, Object> data = new HashMap<String,Object>();
					data.put("id", user.getId());
					data.put("token", user.getToken());
					
					result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
				}
			}else{
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_NOT_EXIST_CODE, null);
				return result;
			}
			
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
		
	}
	
	
	/**
	 * 通过id获取用户信息
	 */
	@RequestMapping(value="/getUserByToken",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByToken(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String token = request.getParameter("token");;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("token", token);
		try{
			
			if(ApiJsonUtil.tokenIsEmpty(token)){
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_TOKEN_ERROR_CODE, null);
				return result;
			}else{
				User user = userService.getUser(map);
				if(user != null){
					user.setPassword(null);
					result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, user);
				}else{
					result = ApiJsonUtil.getApiData(ApiMessage.API_USER_TOKEN_ERROR_CODE, null);
				}
				
			}
		
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
	
	}
	
	
	/**
	 * 更新用户密码
	 */
	@RequestMapping(value="/updateUserPassowrd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserPassowrd(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String token = request.getParameter("token");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("token", token);
		
		try{
			
			User user = userService.getUser(map);
			
			if(user != null && token != null && !token.equals("")){
				if(oldPassword != null && !"".equals(oldPassword) &&
							user.getPassword().equals(MD5Util.string2MD5(oldPassword))){
					if(newPassword != null && !newPassword.equals("")){
						map.put("newPassword", MD5Util.string2MD5(newPassword));
						String newToken = UuidUtil.getUniqueUUid();
						map.put("newToken", newToken);
						userService.updateUserPassword(map);
						
						Map<String, Object> data = new HashMap<String,Object>();
						data.put("token", token);
						result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
					}else{
						result = ApiJsonUtil.getApiData(ApiMessage.API_USER_PASSWORD_NOT_EMPTY_CODE, null);
					}
				}else{
					result = ApiJsonUtil.getApiData(ApiMessage.API_USER_PASSWORD_ERROR_CODE, null);
				}
			}else{
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_NOT_EXIST_CODE, null);
			}
			
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
	
	}
	
	
	
	/**
	 * 更新头像
	 */
	@RequestMapping(value="/updateHeadImage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateHeadImage(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String token = request.getParameter("token");
		String headImage = request.getParameter("headImage");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("headImage", headImage);
		map.put("token", token);
		
		try{
			
			User user = userService.getUser(map);
			
			if(user != null && token != null && !token.equals("")){
				if(headImage != null && !"".equals(headImage) ){
					//更新头像地址
					userService.updateHeadImage(map);
				}
				result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, null);
			}else{
				result = ApiJsonUtil.getApiData(ApiMessage.API_USER_NOT_EXIST_CODE, null);
			}
			
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace(); 
		}
		
		return result;
	
	}
	
	
	
	/**
	 * 上传头像
	 */
	@RequestMapping(value="/uploadHeadImage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadHeadImage(HttpServletRequest request,HttpServletResponse response){
		
		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
		Map<String, Object> result = new HashMap<String,Object>();
		try{
			
	        MultipartFile multipartFile = multipartHttpservletRequest.getFile("uploadFile");
	        InputStream inputStream = multipartFile.getInputStream();
	        	
	        String fileName =  multipartFile.getOriginalFilename();
	        fileName = fileName.substring(fileName.lastIndexOf("."));
	        String img = ImageUtil.getAliImgurl(inputStream,fileName,"company_logo/" + DateUtil.getNowDateStr().replace("-", ""));
	        Map<String, Object> data = new HashMap<String,Object>();
	     
	        data.put("headImage", img);
	        result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("application/json; charset=utf-8");  
		}catch (Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace();
		}
		
		return result;
	
	}
	
	
	
}
