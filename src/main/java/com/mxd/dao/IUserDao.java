package com.mxd.dao;

import java.util.List;

import com.mxd.pojo.po.User;

public interface IUserDao {
	User findUserByName(User user);
	
	User findUserById(Integer leave_id);
	
	void updateUser(User user);
	
	List<User> findAllUser();
	
	User findUserByFk(Integer writeuser);

	void updateSingle(User user);

	void addUserAccount(User user);
	
	List<User> findAdmin();

	void deleteUserById(int parseid);
	
}
