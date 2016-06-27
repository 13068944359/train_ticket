<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
		<br/><br/>
		
		<h2><font color="red">售票窗口发放车票系统</font></h2>
		
		
		<br/><br/><br/>
		↓请输入订单号↓<br/><br/>
		<form action="${pageContext.request.contextPath}/admin/giveTicketServlet" method="post">
			<input type="text" name="oid"><br/><br/>
			<input type="submit" value="发放车票">
		</form>
		
		<br/><br/><br/>
		<c:if test="${oid != null }">
			<font color="red"><b>${oid}</b></font>
		</c:if>
		
		<br/><br/><br/><br/><br/>
	  		<a href="${pageContext.request.contextPath}/admin/IndexServlet">返回管理主页</a>
	  	<br/><br/>
	</body>
</html>
