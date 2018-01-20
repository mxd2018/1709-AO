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
	
	//发送邮件
	@RequestMapping(value="/mailsend.action")
	@ResponseBody
	public String mailSend(Mail mail,HttpSession session){
		mailservice.saveMail(mail);
		return "0";
	}
	
	//查看所有接受邮件
	@RequestMapping(value="/receive.action")
	public String mailReceive(HttpSession session){
		User user = (User) session.getAttribute("sessionUser");
		List<Mail> mList = mailservice.findMailById(user);
		
		session.setAttribute("mList",mList);	
		return "mailReceive";
	}
	
	/**
	 * 将邮件删除到垃圾箱
	 * 问题思考：
	 * 1.前端a标签能不能直接传个对象过来
	 * 2.ajax传数据的方式？
	 */
	@RequestMapping(value="/removemail.action")
	public String mailRemove(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.removeToGarage(parseid);
		
		return "redirect:receive.action";
	}
	
	//显示垃圾箱邮件
	@RequestMapping(value="/garage.action")
	public String mailGarage(HttpSession session){
		User user = (User) session.getAttribute("sessionUser");
		List<Mail> gmList = mailservice.findGarageMailById(user);
		
		session.setAttribute("gmList",gmList);	
		return "mailGarage";
	}
	
	//还原删除邮件
	@RequestMapping(value="/returnmail.action")
	public String mailReturn(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.returnToMail(parseid);
	
		return "redirect:garage.action";
	}
	
	//彻底删除邮件，从垃圾箱中
	@RequestMapping(value="/deletemail.action")
	public String mailDelete(HttpServletRequest request){
		String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.deleteMail(parseid);
		
		return "redirect:garage.action";
	}
	
	
	//查看邮件详细信息
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
