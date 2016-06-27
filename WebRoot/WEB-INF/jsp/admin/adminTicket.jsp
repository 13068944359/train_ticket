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
	
		<br/>
		<br/>
		<h2><font color="red">车票管理系统</font></h2><br/><br/><br/>
	
		<table  border="1" width="40%" cellspacing="0" background="black" align="center">
    		<tr height="30px">
    			<td width="140px"><b>出发地-目的地</b></td>
    			<td width="200px"><b>发车时间</b></td>
    			<td width="50px"><b>余票</b></td>
    			<td width="80px"><b>票价</b></td>
    			<td width="50px"><b>操作</b></td>
    		</tr>
    		<c:forEach items="${ticketList.list}" var="ticket">
    			<tr height="30px">
	    			<td>${ticket.fromwhere}--${ticket.towhere}</td>
	    			<td>${ticket.gotime}</td>
	    			<td>${ticket.number}</td>
	    			<td>${ticket.price}元</td>
	    			<td><a href="${pageContext.request.contextPath}/admin/EditorTicketServlet?tid=${ticket.tid}">编辑</a></td>
	    		</tr>
    		</c:forEach>
    		<tr height="30px">
    			<td colspan="5">
    				共-${ticketList.totalRecord}-条记录，共-${ticketList.totalPage}-页&nbsp;&nbsp;当前第-${ticketList.currentPage}-页&nbsp;&nbsp;
    				<c:if test="${ticketList.previousPage!=-1}">
    					<a href="${pageContext.request.contextPath}/admin/AdminTicket?select=${ticketList.select}&page=${ticketList.previousPage}">上一页</a>&nbsp;&nbsp;
    				</c:if>
    				<c:if test="${ticketList.nextPage!=-1}">
    					<a href="${pageContext.request.contextPath}/admin/AdminTicket?select=${ticketList.select}&page=${ticketList.nextPage}">下一页</a>
    				</c:if>
    			</td>
    		</tr>
    	</table>
    	
    	<br/><br/>按<font color="blue"><b>出发地</b></font>查询火车票<br/><br/>
    	<form action="${pageContext.request.contextPath}/admin/AdminTicket" method="post">
    		<input type="hidden" name="doselect" value="select">
    		<input type="text" name="select" />
    		<input type="submit" value="查询" />
    	</form>
    	
    	
    	
    	
    	<br/><br/>
	  		<a href="${pageContext.request.contextPath}/admin/IndexServlet">返回管理主页</a>
	  	<br/><br/>
	
	</body>
</html>
