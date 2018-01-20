package com.mxd.web;

import java.util.List;

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
	
	//跳转到个人信息编辑页面
	@RequestMapping(value="/editData.action")
	public String toEditData(){
		return "editData";
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping(value="/saveInfo.action")
	@ResponseBody
	public String updateUser(User user,HttpSession session){
		uService.updateUser(user);
		session.setAttribute("sessionUser", user);
		
		return "0";
	}
	
	//获得所有用户信息，并跳转到邮件编写页面
	@RequestMapping(value="/mailWrite.action")
	public String mailWrite(HttpSession session){
		List<User> uList = uService.findAllUser();
		session.setAttribute("uList",uList);
		return "mailWrite";
	}
	
	
	
	//跳转到个人账户信息页面
	@RequestMapping(value="/toSingleAccount.action")
	public String toUserAccount(){
		return "userAccount";
	}
	
	//跳转到个人信息编辑页面
		@RequestMapping(value="/editSingleAccount.action")
		public String toEditSingleAccount(){
			return "editSingleAccount";
		}
	
	//修改个人账户信息
	@RequestMapping(value="/saveSingleInfo.action")
	@ResponseBody
	public String updateSingleAccount(User user,HttpSession session){
		uService.updateSingle(user);
		//System.out.println(user.getUsername());
		session.setAttribute("sessionUser", user);
		
		return "0";
	}
	
	
	//跳转用户管理页面
	@RequestMapping(value="/toAllAcount.action")
	public String allAccount(HttpSession session){
		List<User> allUser = uService.findAllUser();
		session.setAttribute("uList", allUser);
		return "allUserAccount";
	}
	
	//跳转到添加账户页面
	@RequestMapping(value="/toAddUser.action")
	public String toAddUser(){
		return "addUserAccount";
	}
		
		
	//添加账户
	@RequestMapping(value="/addAccount.action")
	@ResponseBody
	public String addAccount(User user){
		uService.addUserAccount(user);
		
		return "0";
	}
}
