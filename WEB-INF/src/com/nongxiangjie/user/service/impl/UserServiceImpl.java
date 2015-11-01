package com.nongxiangjie.user.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nongxiangjie.user.dao.UserDao;
import com.nongxiangjie.user.dto.User;
import com.nongxiangjie.user.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;

	@Override
	public User getUser(Map<String, Object> map) throws Exception {
		User user = userDao.getUser(map);
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {

		userDao.addUser(user);
	}

	@Override
	public void updateUserPassword(Map<String, Object> map) throws Exception {
		userDao.updateUserPassword(map);
	}

	@Override
	public void updateHeadImage(Map<String, Object> map) throws Exception {
		userDao.updateHeadImage(map);
	}

}
