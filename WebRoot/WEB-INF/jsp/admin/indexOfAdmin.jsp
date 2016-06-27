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
  </head>

	<body style="text-align:center;">
	
		<br/>
		<br/>
		<h2><font color="red">车票管理系统</font></h2><br/><br/><br/>
	
		<a href="${pageContext.request.contextPath}/admin/OrderSystem"><h4>订单交易系统</h4></a>	
	
		<a href="${pageContext.request.contextPath}/admin/FindAllOrder"><h4>查看订单</h4></a>	
	
		<a href="${pageContext.request.contextPath}/admin/AdminTicket"><h4>查看车票</h4></a>  
		
		<a href="${pageContext.request.contextPath}/admin/ShowAllUser"><h4>查看用户</h4></a>
		<br/><br/><br/>
		
		<a href="${pageContext.request.contextPath}/admin/Logout"><font color="red">退出系统</font></a>
	
	</body>
</html>
