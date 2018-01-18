package com.mxd.service;

import com.mxd.pojo.po.User;

public interface IUserService {
	User findUserByName(User user);
	
	void updateUser(User user);
}
