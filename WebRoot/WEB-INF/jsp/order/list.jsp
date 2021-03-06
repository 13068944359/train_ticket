<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的订单</title>
    <style>
		td {text-align: center}
	</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

  </head>
  
  <body style="text-align:center;">
  	
  	<br/><br/>
  	<h2><font color="blue">我的订单</font></h2>
  	
  	<c:forEach items="${orderList}" var="order">
  	
  		<table border="1" width="70%" cellspacing="0" background="black" align="center">
			<tr bgcolor="gray" bordercolor="gray">
				<td colspan="6">订单编号：${order.oid }&nbsp;&nbsp; 下单时间：<fmt:formatDate
						pattern="yyyy-MM-dd HH:mm:ss" value="${order.orderTime }" />&nbsp;&nbsp; 
					金额：<font color="yellow"><b>${order.total }元</b></font>&nbsp;&nbsp; 
					<c:choose>
						<c:when test="${order.state eq 1 }">
							<a href="${pageContext.request.contextPath}/servlet/PayServlet?oid=${order.oid }"><font color="red">付款</font></a>&nbsp;&nbsp; 
							<a href="${pageContext.request.contextPath}/servlet/CancelOrderServlet?oid=${order.oid }">取消订单</a> 
						</c:when>
						
						<c:when test="${order.state eq 2 }">
							<font color="yellow"><b>已付款</b></font> &nbsp;&nbsp; 
							<a href="${pageContext.request.contextPath}/servlet/RefundServlet?oid=${order.oid }">退款</a> 
						</c:when>
						
						<c:when test="${order.state eq 3 }">
							<font color="green"><b>交易完成</b></font>
						</c:when>
						
						<c:when test="${order.state eq 4 }">交易关闭</c:when>
						<c:when test="${order.state eq 5 }">已退款</c:when>
					</c:choose>
				</td>
			</tr>

			<c:forEach items="${order.orderItemList}" var="orderItem">
				<tr bordercolor="gray" align="center" height="30px">
					<td>${orderItem.ticket.fromwhere }--${orderItem.ticket.towhere }</td>
					<td>发车时间：${orderItem.ticket.gotime }</td>
					<td>单价：${orderItem.ticket.price }元</td>
					<td>数量：${orderItem.count }</td>
					<td>小计：${orderItem.subtotal }元</td>
				</tr>
			</c:forEach>

		</table>
  		
  		<br/><br/><br/>
  	</c:forEach>
  	

	<br/><br/>
  	<a href="${pageContext.request.contextPath}/servlet/ShowTicketServlet">返回主页</a>
  	
  	<br/><br/><br/><br/>
  </body>
</html>