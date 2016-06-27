<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看注册用户</title>
    <style>
		td {text-align: center}
	</style>
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
		
		<font color="blue"><h1>共有${count}个注册用户</h1></font>
		<br/>
		<br/>
		<table  border="1" width="50%" cellspacing="0" background="black" align="center">
			<tr height="30px">
				<td>用户名</td>
				<td>邮箱</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			
			<c:forEach items="${userList}" var="user">
				<tr height="30px">
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>
						<c:choose>
							<c:when test="${user.state}">
								 <font color="green"><b>活动账户</b></font>
							</c:when>
							<c:when test="${!user.state}">
								
								<c:if test="${user.code == 'no'}">
									<font color="red"><b>黑名单账户</b></font>
								</c:if>
								<c:if test="${user.code != 'no'}">
									未激活
								</c:if>
								
							</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${user.code == 'no'}">
							<a href="${pageContext.request.contextPath}/admin/DefriendServlet?uid=${user.uid}">取消拉黑</a>
						</c:if>
						<c:if test="${user.code != 'no'}">
							<a href="${pageContext.request.contextPath}/admin/DefriendServlet?uid=${user.uid}">拉黑账户</a>
						</c:if>
					</td>
				</tr>
			
			</c:forEach>
		
		
		</table>
		
		
		
		<br/><br/>
	  		<a href="${pageContext.request.contextPath}/admin/IndexServlet">返回管理主页</a>
	  	<br/><br/>
	
	</body>
</html>
