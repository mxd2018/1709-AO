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
	
	//��������û���Ϣ������ת���ʼ���дҳ��
	@RequestMapping(value="/mailWrite.action")
	public String mailWrite(HttpSession session){
		List<User> uList = uService.findAllUser();
		session.setAttribute("uList",uList);
		return "mailWrite";
	}
	
	
	
	//��ת�������˻���Ϣҳ��
	@RequestMapping(value="/toSingleAccount.action")
	public String toUserAccount(){
		return "userAccount";
	}
	
	//��ת��������Ϣ�༭ҳ��
		@RequestMapping(value="/editSingleAccount.action")
		public String toEditSingleAccount(){
			return "editSingleAccount";
		}
	
	//�޸ĸ����˻���Ϣ
	@RequestMapping(value="/saveSingleInfo.action")
	@ResponseBody
	public String updateSingleAccount(User user,HttpSession session){
		uService.updateSingle(user);
		//System.out.println(user.getUsername());
		session.setAttribute("sessionUser", user);
		
		return "0";
	}
	
	
	//��ת�û�����ҳ��
	@RequestMapping(value="/toAllAcount.action")
	public String allAccount(HttpSession session){
		List<User> allUser = uService.findAllUser();
		session.setAttribute("uList", allUser);
		return "allUserAccount";
	}
	
	//��ת������˻�ҳ��
	@RequestMapping(value="/toAddUser.action")
	public String toAddUser(){
		return "addUserAccount";
	}
		
		
	//����˻�
	@RequestMapping(value="/addAccount.action")
	@ResponseBody
	public String addAccount(User user){
		uService.addUserAccount(user);
		
		return "0";
	}
}
