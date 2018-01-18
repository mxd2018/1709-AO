package com.mxd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/mail")
public class Mail {
	
	@RequestMapping(value="/mailWrite.action")
	public String mailWrite(){
		
		return "mailWrite";
	}
}
