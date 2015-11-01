package com.nongxiangjie.user.dao.mappers;

import java.util.Map;

import org.apache.ibatis.spring.support.AbstractDaoSupport;
import org.springframework.stereotype.Repository;

import com.nongxiangjie.user.dao.UserDao;
import com.nongxiangjie.user.dto.User;

@Repository
public class UserMapper extends AbstractDaoSupport implements UserDao{

	@Override
	public User getUser(Map<String, Object> map) throws Exception {
		User user = getSession().selectOne(map);
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {

		getSession().insert(user);
	}

	@Override
	public void updateUserPassword(Map<String, Object> map) throws Exception {
		getSession().update(map);
	}

	@Override
	public void updateHeadImage(Map<String, Object> map) throws Exception {
		getSession().update(map);
	}

}
