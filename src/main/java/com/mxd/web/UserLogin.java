package com.mxd.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxd.pojo.po.User;
import com.mxd.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserLogin {
	@Autowired
	private IUserService uService;
	
	@RequestMapping(value="/tologin.do")
	public String toLogin(){
		return "login";
	}
	
	
	/**
	 *注意：此处如果加了 @ResponseBody
	 *1.当返回值是User对象，或者map，list等存在键值对形式的数据时，
	 *	会自动返回一个json格式的数据，前端ajax接收数据的类型就要是json
	 *2.当返回值类型是String时，因为@ResponseBody的作用，
	 *	什么请求来就返回到什么请求，返回的就不是逻辑视图，而是字符串，ajax接收数据的类型就要是text
	 *3.返回值类型也可以是void，那么返回数据就要通过流的方式，使用getwriter()的方式
	 */
	@RequestMapping(value="/login.do")
	@ResponseBody
	public String findUserByName(User user,HttpSession session){
		User findUser = uService.findUserByName(user);
		if(findUser!=null){
			if(findUser.getPassword().equals(user.getPassword())){
				session.setAttribute("sessionUser", findUser);
				return "1";
			}else{
				return "0";
			}
		}	
		return "0";		
	}
	
	@RequestMapping(value="/toindex.do")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping(value="/loginOut.action")
	public String loginOut(){
		return "login";
	}
	
	
}
