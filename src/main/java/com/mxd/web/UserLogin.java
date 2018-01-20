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
	 *ע�⣺�˴�������� @ResponseBody
	 *1.������ֵ��User���󣬻���map��list�ȴ��ڼ�ֵ����ʽ������ʱ��
	 *	���Զ�����һ��json��ʽ�����ݣ�ǰ��ajax�������ݵ����;�Ҫ��json
	 *2.������ֵ������Stringʱ����Ϊ@ResponseBody�����ã�
	 *	ʲô�������ͷ��ص�ʲô���󣬷��صľͲ����߼���ͼ�������ַ�����ajax�������ݵ����;�Ҫ��text
	 *3.����ֵ����Ҳ������void����ô�������ݾ�Ҫͨ�����ķ�ʽ��ʹ��getwriter()�ķ�ʽ
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
