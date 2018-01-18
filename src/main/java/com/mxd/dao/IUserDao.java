package com.mxd.dao;

import com.mxd.pojo.po.User;

public interface IUserDao {
	User findUserByName(User user);
	
	void updateUser(User user);
}
