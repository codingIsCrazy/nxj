package com.nongxiangjie.user.service;

import java.util.Map;

import com.nongxiangjie.user.dto.User;

public interface UserService {

	/**
	 * 查询用户
	 */
	public User getUser(Map<String, Object> map) throws Exception;
	
	/**
	 * 添加用户
	 */
	public void addUser(User user) throws Exception;
	
	/**
	 * 更新用户密码
	 */
	public void updateUserPassword(Map<String, Object> map) throws Exception;
}
