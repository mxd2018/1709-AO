package com.mxd.service;

import java.util.List;

import com.mxd.pojo.po.User;

public interface IUserService {
	User findUserByName(User user);
	
	User findUserById(Integer leave_id);
	
	void updateUser(User user);
	
	List<User> findAllUser();

	void updateSingle(User user);

	void addUserAccount(User user);
	
	List<User> findAdmin();
}
