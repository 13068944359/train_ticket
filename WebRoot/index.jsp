<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
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
  	<br/><br/><br/><br/><br/>
  	
  	<font color="red"><h1>欢迎光临车站自助购票系统</h1></font><br/>
  	
  	<c:if test="${session_user==null}">
	    <a href="${pageContext.request.contextPath}/servlet/RegistUIServlet">注册</a> &nbsp;&nbsp;
	    <a href="${pageContext.request.contextPath}/servlet/LoginUIServlet">登录</a>
    </c:if>
    <c:if test="${session_user!=null}">
	   		 您好：${sessionScope.session_user.username }&nbsp;&nbsp;|&nbsp;&nbsp;
	   		<a href="${pageContext.request.contextPath}/servlet/ShowTicketServlet">我要买票</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/servlet/showCartServlet">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/servlet/ShowOrderServlet">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/servlet/LogoutServlet">注销登录</a>
    </c:if>
    <br/><br/><br/>
    <c:if test="${ticketList!=null}">
    	<table border="1" width="40%" cellspacing="0" background="black" align="center">
    		<tr height="30px">
    			<td><b>出发地-目的地</b></td>
    			<td><b>发车时间</b></td>
    			<td><b>余票</b></td>
    			<td><b>票价</b></td>
    			<td><b>买票</b></td>
    		</tr>
    		<c:forEach items="${ticketList.list}" var="ticket">
    			<tr height="30px">
	    			<td>${ticket.fromwhere}--${ticket.towhere}</td>
	    			<td>${ticket.gotime}</td>
	    			<td>${ticket.number}</td>
	    			<td>${ticket.price}元</td>
	    			<td><a href="${pageContext.request.contextPath}/servlet/BuyTicketServlet?tid=${ticket.tid}">买票</a></td>
	    		</tr>
    		</c:forEach>
    		<tr height="30px">
    			<td colspan="5">
    				共-${ticketList.totalRecord}-条记录，共-${ticketList.totalPage}-页&nbsp;&nbsp;当前第-${ticketList.currentPage}-页&nbsp;&nbsp;
    				<c:if test="${ticketList.previousPage!=-1}">
    					<a href="${pageContext.request.contextPath}/servlet/ChangePageServlet?select=${ticketList.select}&page=${ticketList.previousPage}">上一页</a>&nbsp;&nbsp;
    				</c:if>
    				<c:if test="${ticketList.nextPage!=-1}">
    					<a href="${pageContext.request.contextPath}/servlet/ChangePageServlet?select=${ticketList.select}&page=${ticketList.nextPage}">下一页</a>
    				</c:if>
    			</td>
    		</tr>
    	</table>
    	
    	<br/><br/>按<font color="blue"><b>出发地</b></font>查询火车票<br/><br/>
    	<form action="${pageContext.request.contextPath}/servlet/SelectTicketServlet" method="post">
    		<input type="text" name="select" />
    		<input type="submit" value="查询" />
    	</form>
    	
    </c:if>
  </body>
</html>
