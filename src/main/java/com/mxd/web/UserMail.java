package com.mxd.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxd.pojo.po.Mail;
import com.mxd.pojo.po.User;
import com.mxd.service.IMailService;

@Controller
@RequestMapping(value="/mail")
public class UserMail {
	@Autowired
	private IMailService mailservice;
	
	//�����ʼ�
	@RequestMapping(value="/mailsend.action")
	@ResponseBody
	public String mailSend(Mail mail,HttpSession session){
		mailservice.saveMail(mail);
		return "0";
	}
	
	//�鿴���н����ʼ�
	@RequestMapping(value="/receive.action")
	public String mailReceive(HttpSession session){
		User user = (User) session.getAttribute("sessionUser");
		List<Mail> mList = mailservice.findMailById(user);
		
		session.setAttribute("mList",mList);	
		return "mailReceive";
	}
	
	/**
	 * ���ʼ�ɾ����������
	 * ����˼����
	 * 1.ǰ��a��ǩ�ܲ���ֱ�Ӵ����������
	 * 2.ajax�����ݵķ�ʽ��
	 */
	@RequestMapping(value="/removemail.action")
	public String mailRemove(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.removeToGarage(parseid);
		
		return "redirect:receive.action";
	}
	
	//��ʾ�������ʼ�
	@RequestMapping(value="/garage.action")
	public String mailGarage(HttpSession session){
		User user = (User) session.getAttribute("sessionUser");
		List<Mail> gmList = mailservice.findGarageMailById(user);
		
		session.setAttribute("gmList",gmList);	
		return "mailGarage";
	}
	
	//��ԭɾ���ʼ�
	@RequestMapping(value="/returnmail.action")
	public String mailReturn(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.returnToMail(parseid);
	
		return "redirect:garage.action";
	}
	
	//����ɾ���ʼ�������������
	@RequestMapping(value="/deletemail.action")
	public String mailDelete(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.deleteMail(parseid);
		
		return "redirect:garage.action";
	}
	
	
	//�鿴�ʼ���ϸ��Ϣ
	@RequestMapping(value="/mailInfo.action")
	public String findMailInfo(HttpServletRequest request,HttpSession session){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.updateRead(parseid);
		Mail mail = mailservice.findMailInfo(parseid);
		//System.out.println(mail.getUser().getUsername());
		session.setAttribute("mailInfo", mail);
		
		return "mailInfo";
	}
	
	
}
