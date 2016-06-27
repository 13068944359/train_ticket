<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="Refresh" content="5;index.jsp"/>
  </head>
  
  <body style="text-align:center;">
  <br/><br/><br/><br/><br/><br/>
  
   <font color="red"><h1> ${msg }</h1></font><br/><br/>
	    页面将在5秒后跳转到网站主页，如果没有跳转，请
	  <a href="${pageContext.request.contextPath}/index.jsp">点击</a>  
    
  </body>
</html>
