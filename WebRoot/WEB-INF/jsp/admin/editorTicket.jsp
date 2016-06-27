<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑车票信息</title>
     <style>
		td {text-align: center}
	</style>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
	<script type="text/javascript">
		function changeprice(num){
			var number = document.getElementById("number").value + 0.0;
			if(number>=0 && number%1==0){
			}else{
				alert("请输入正整数，若输入0，则下架该商品。");
				document.getElementById("number").value=num;
				
			}
		}
	</script>
	
  </head>

	<body style="text-align:center;">
	
		<br/><br/><br/><br/><br/><br/>
		
		<form action="${pageContext.request.contextPath}/admin/ChangeTicketNumServlet" method="post">
			<input type="hidden" name="tid" value="${ticket.tid}">
			<table  border="1" width="40%" cellspacing="0" background="black" align="center">
				<tr  height="50px">
	    			<td><b>出发地-目的地</b></td>
	    			<td><b>发车时间</b></td>
	    			<td><b>余票</b></td>
	    			<td><b>余票修改为</b></td>
	    			<td><b>操作</b></td>
	    		</tr>
	    		
	    		<tr  height="50px">
	    			<td>${ticket.fromwhere}--${ticket.towhere}</td>
	    			<td>${ticket.gotime}</td>
	    			<td>${ticket.number}</td>
	    			<td><input id="number" type="text" name="number" size="5" value="${ticket.number}" onchange="return changeprice(${ticket.number})"></td>
	    			<td><input type="submit" value="提交"></td>
	    		</tr>
			
			
			</table>
		</form>
		
		<br/><br/>
	  	<a href="${pageContext.request.contextPath}/admin/IndexServlet">返回管理主页</a>
	  	<br/><br/>
	</body>
</html>
