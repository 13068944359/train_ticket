<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>买票</title>
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
		function changeprice(price, number){
			var count = document.getElementById("count").value;
			if(count>0 && count<=number && count%1==0){
				price = count * price+ 0.0;
				document.getElementById("price").value=price+ "元";
			}else{
				alert("请输入 1~" + number + " 之间的整数");
				document.getElementById("count").value=1;
				document.getElementById("price").value=price + "元";
			}
			
			
		}
	</script>

  </head>
  
  <body style="text-align:center;" >
  	<br/><br/><br/><br/>
  	<form action="${pageContext.request.contextPath}/servlet/CartAddServlet" method="post">
  		<input type="hidden" name="tid" value="${ticket.tid}" />
	  	<table border="1px" align="center" cellspacing="0">
	  		<tr  height="30px">
	  			<td width="140px"><b>出发地--目的地</b></td>
	  			<td width="200px"><b>发车时间</b></td>
	  			<td width="80px"><b>单价</b></td>
	  			<td width="50px"><b>余量</b></td>
	  			<td width="100px"><b>票数</b></td>
	  			<td width="80px"><b>小计</b></td>
	  			<td width="140px"><b>操作</b></td>
	  		</tr>
	  		<tr height="30px">
	  			<td>${ticket.fromwhere}--${ticket.towhere}</td>
	  			<td>${ticket.gotime}</td>
	  			<td>${ticket.price}</td>
	  			<td>${ticket.number}</td>
	  			<td><input id="count" type="text" name="count" value="1" size="5" onchange="return changeprice(${ticket.price},${ticket.number})" /></td>
	  			<td><input id="price" type="text" name="subprice" value="${ticket.price}元" size="4" disabled /></td>
	  			<td><input type="submit" value="添加到购物车" /></td>
	  		</tr>
	  	</table>
  	</form>
  	<br/>
  	<a href="${pageContext.request.contextPath}/servlet/ShowTicketServlet">返回主页</a>&nbsp;&nbsp;&nbsp;&nbsp;
  	<a href="${pageContext.request.contextPath}/servlet/showCartServlet">查看购物车</a>
  </body>
</html>