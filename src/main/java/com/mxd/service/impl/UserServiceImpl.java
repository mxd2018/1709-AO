package com.mxd.service.impl;

import java.util.List;

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

	@Override
	public List<User> findAllUser() {
		List<User> uList = userDao.findAllUser();
		return uList;
	}

	@Override
	public void updateSingle(User user) {
		userDao.updateSingle(user);
		
	}

	@Override
	public void addUserAccount(User user) {
		userDao.addUserAccount(user);	
	}

	@Override
	public User findUserById(Integer leave_id) {
		User user = userDao.findUserById(leave_id);
		return user;
	}

	@Override
	public List<User> findAdmin() {
		List<User> admins = userDao.findAdmin();
		return admins;
	}

	@Override
	public void deleteUserById(int parseid) {
		userDao.deleteUserById(parseid);
		
	}
	
}
