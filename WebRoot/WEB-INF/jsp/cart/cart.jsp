<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>显示购物车</title>
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
	
	<script type="text/javascript">
		function check(str){
			if(window.confirm("确定要清空购物车吗？")){
				window.location.href = str;
			}
		}
		
	</script>

  </head>
  
  <body style="text-align:center;">
  	<br/><br/>
  	
  	<c:if test="${sessionScope.cart.cartItenList!=null }">
  	
	  	<h2><font color="blue">我的购物车</font></h2>
	  	<table border="1px" align="center" cellspacing="0">
	  		<tr height="30px">
	  			<td width="140px"><b>出发地--目的地</b></td>
	  			<td width="200px"><b>发车时间</b></td>
	  			<td width="80px"><b>单价</b></td>
	  			<td width="100px"><b>数量</b></td>
	  			<td width="80px"><b>小计</b></td>
	  			<td width="140px"><input type="button" value="清空购物车" onclick="return check('${pageContext.request.contextPath}/servlet/CartClearServlet')"></td>
	  		</tr>
	  		<c:forEach items="${sessionScope.cart.cartItenList }" var="cartItem">
		  		<tr height="30px">
		  			<td>${cartItem.ticket.fromwhere}--${cartItem.ticket.towhere}</td>
		  			<td>${cartItem.ticket.gotime}</td>
		  			<td>${cartItem.ticket.price}元</td>
		  			<td>${cartItem.count}</td>
		  			<td>${cartItem.subTatal}元</td>
		  			<td><a href="${pageContext.request.contextPath}/servlet/CartDeleteServlet?tid=${cartItem.ticket.tid}">移除</a></td>
		  		</tr>
	  		</c:forEach>
	  		<tr height="30px">
	  			<td></td>
	  			<td></td>
	  			<td></td>
	  			<td></td>
	  			<td>${sessionScope.cart.total}元</td>
	  			<td><a href="${pageContext.request.contextPath}/servlet/AddOrderServlet">购买</a></td>
	  		</tr>
	  	</table>
  	
  	</c:if>
  	<c:if test="${sessionScope.cart.cartItenList==null }">
  		<h2><font color="blue">您的购物车空空如也，请返回主页选购车票</font></h2>
  	</c:if>
  	
  	<br/>
  	<a href="${pageContext.request.contextPath}/servlet/ShowTicketServlet">返回主页</a>
  </body>
</html>