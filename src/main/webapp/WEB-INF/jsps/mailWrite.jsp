<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
		<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
		<title>办公自动化管理系统</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<script>
			function setit()
			{
				document.forms[0].submit();
			}
		</script>
		
		<script type="text/javascript">
			window.onload=function(){
				var gender="${sessionUser.gender}";
				var sc=document.getElementById("sc");
				var options=sc.getElementsByTagName("option");
				for(var i=0;i<options.length;i++){
					if(options[i].value==gender){
						options[i].selected=true;
					}
				}
				
			}
		</script>
		
		
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
	</head>
	
	<body>
		<div class="top">
			<div class="global-width">
				<img src="${pageContext.request.contextPath}/Images/logo.gif" class="logo" />
			</div>
		</div>
		<div class="status">
			<div class="global-width">
				${sessionUser.username} 你好！欢迎访问办公管理系统！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="location.href='${pageContext.request.contextPath}/user/loginOut.action'";>注销</a>
			</div>
		</div>
		<form id="myForm" name="myForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/mail/mailsend.action">
		<!-- RESTful风格 -->
		<!-- <input type="hidden" name="_method" value="put" /> --> 
		
		<!-- 隐藏的表单数据 -->
		<input type="hidden" name="isread" value="未读" id=""/>
		<input type="hidden" name="isdelete" value="0" id=""/>
		<input type="hidden" name="writeuser" value="${sessionUser.id}" id=""/>
		<div class="main">
			<div class="global-width">
				
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>办公自动化管理系统</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	</head>
  
  <body>
    <div class="nav" id="nav">
					<div class="t"></div>
					<dl>
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">信息管理 
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/user/toindex.do" target="_self">个人信息</a>
						</dd>
					</dl>
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							邮件管理
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/userInfo/mailWrite.action" target="_self">写邮件</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/mail/receive.action" target="_self">收邮件</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/mail/garage.action" target="_self">垃圾邮件</a>
						</dd>
					</dl>
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							考勤管理
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/vacation/leave.action?leave_id=${sessionUser.id}" target="_self">休假</a>
						</dd>
					</dl>
					
					<dl >
					
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							权限管理
						</dt>
						
						<dd>
							<a href="${pageContext.request.contextPath}/userInfo/toSingleAccount.action" target="_self">个人账户</a>
						</dd>
						
						<c:if test="${sessionUser.isadmin==1}">
							<dd>
								<a href="${pageContext.request.contextPath}/userInfo/toAllAcount.action" target="_self">管理账户</a>
							</dd>
						</c:if>
					</dl>
				</div>
  </body>
</html>
 
					<div class="action">
						<div class="t">
							写邮件
						</div>
						<div class="pages">
							<table width="90%" border="0" cellspacing="0" cellpadding="0">
								<tr >
									<td align="right" width="30%">收件人：</td>
									<td  align="left">
										<select name="receiveuser" id="receiveuser">
											<c:forEach items="${uList}" var="ruser">
												
												<c:if test="${ruser.id != sessionUser.id}">
													<option value="${ruser.id}">${ruser.username}</option>
												</c:if>
												
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr >
									<td align="right" width="30%">邮件标题：</td>
									<td  align="left">
										<input type="text" name="mailtitle"  id="mailtitle"/>
									</td>
								</tr>
								<tr >
									<td align="right" width="30%">邮件内容：</td>
									<td  align="left">
										<textarea name="mailcontent"  rows="10" cols="30"></textarea>
									</td>
								</tr>
								<tr >
									<td align="right" width="30%">上传附件：</td>
									<td  align="left">
										<input type="file" name="file" id="file"/>
										
										<b><font color="red" id="mess"></font></b>
									</td>
									
								</tr>
									
								<tr >
									<td align="center" colspan="2"><br/>
									<input type="submit"  id="save" value="发送邮件" />
									<input type="button"  id="" value="返回" onclick="history.go(-1)" />
									</td>
								</tr>
								
								</table>
								
						</div>
					</div>
			</div>
		</div>
		</form>
		<div class="copyright">
			Copyright &nbsp; &copy; &nbsp; 
		</div>
 
	</body>
	
	<script type="text/javascript">
			$(function(){
				$("#myForm").validate({
					rules:{
						mailtitle:"required"
						
						
					},
					messages:{
						mailtitle:"请输入邮件标题"
						
						
					}
					/* submitHandler:function(){
						//Ajax提交表单
						var formData = new FormData($( "#myForm" )[0]); 
						$.ajax({
							data:formData,
							dataType:"text",
							type:"post",
							url:"${pageContext.request.contextPath}/mail/mailsend.action",
							success:function(rec){
								if(rec=="0"){
									alert("发送成功！")
									location.href="${pageContext.request.contextPath}/userInfo/mailWrite.action"
								}else{
									alert("抱歉，发送失败！")
								}	
							}
						});
					}  */
				});
				
				
			})
	
	</script>
	<script>
		$(function(){
			$("#save").click(function(){
				var dom = document.getElementById("file");
				var fileSize=dom.files[0].size;
				if(fileSize>50000){
					$("#mess").html("上传文件不得超过9M");
					return false;
				}
			});
			
		});
	</script>
</html>