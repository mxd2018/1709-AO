package com.mxd.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mxd.pojo.po.Mail;
import com.mxd.pojo.po.User;
import com.mxd.service.IMailService;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping(value="/mail")
public class UserMail {
	@Autowired
	private IMailService mailservice;
	
	//发送邮件
	@RequestMapping(value="/mailsend.action")
	
	public String mailSend(Mail mail,@RequestParam("file")MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		
		String realPath = request.getServletContext().getRealPath("/");
		//System.out.println(realPath);
		String originalFilename = file.getOriginalFilename();
		
		mail.setMailfile(file.getOriginalFilename());
		
		if (!file.isEmpty())
	     {
			file.transferTo(new File(realPath+"sendFile/"+file.getOriginalFilename())); 
	     }
		mailservice.saveMail(mail);
		
		return "mailWrite";
	}
	
	//下载文件
	@RequestMapping(value="downloadmail.action")
	public void downLoad(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String filename = request.getParameter("filename");
		//filename = new String(filename.getBytes("iso8859-1"),"utf-8");
		
		String realPath = request.getServletContext().getRealPath("/sendFile/"+filename);
		
		request.getServletContext().getMimeType(filename);
		
		String agent = request.getHeader("User-Agent");
		String filenameencode=filename;
		response.setHeader("Content-Disposition","attachment;filename="+filenameencode);
		if (agent.contains("MSIE")) {
			// IE浏览器
			filenameencode = URLEncoder.encode(filenameencode, "utf-8");
			filenameencode = filenameencode.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameencode = "=?utf-8?B?"+ base64Encoder.encode(filenameencode.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filenameencode = URLEncoder.encode(filenameencode, "utf-8");				
		} 
			response.setHeader("Content-Disposition","attachment;filename="+filenameencode);
		
			FileInputStream in = new FileInputStream(new File(realPath));
			ServletOutputStream out = response.getOutputStream();
			int b=0;
			while((b=in.read())!=-1){
				out.write(b);
			}
				in.close();
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
	@ResponseBody
	public String mailRemove(HttpServletRequest request,HttpSession session){
		/*String mid = request.getParameter("mid");
		int parseid = Integer.parseInt(mid);
		mailservice.removeToGarage(parseid);
		
		return "redirect:receive.action";*/
		String[] mids = request.getParameterValues("checkSingleBox");
		if(mids!=null){
			for(String mid :mids){
				int parseid = Integer.parseInt(mid);
				mailservice.removeToGarage(parseid);
			}
			return "0";
		}else{
			return "2";
		}
		
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
