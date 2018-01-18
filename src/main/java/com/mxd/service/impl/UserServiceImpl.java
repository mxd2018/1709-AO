package com.mxd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxd.dao.IUserDao;
import com.mxd.pojo.po.User;
import com.mxd.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User findUserByName(User user){
		User u = userDao.findUserByName(user);
		return u;
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
}
