package com.mxd.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxd.pojo.po.User;
import com.mxd.service.IUserService;

@Controller
@RequestMapping(value="/userInfo")
public class UserInfo {
	@Autowired
	private IUserService uService;
	
	//��ת��������Ϣ�༭ҳ��
	@RequestMapping(value="/editData.action")
	public String toEditData(){
		return "editData";
	}
	
	/**
	 * �޸ĸ�����Ϣ
	 */
	@RequestMapping(value="/saveInfo.action")
	@ResponseBody
	public String updateUser(User user,HttpSession session){
		uService.updateUser(user);
		session.setAttribute("sessionUser", user);
		
		return "0";
	}
	
}
