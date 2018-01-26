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
	
	//�����ʼ�
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
	
	//�����ļ�
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
			// IE�����
			filenameencode = URLEncoder.encode(filenameencode, "utf-8");
			filenameencode = filenameencode.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// ��������
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameencode = "=?utf-8?B?"+ base64Encoder.encode(filenameencode.getBytes("utf-8")) + "?=";
		} else {
			// ���������
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
