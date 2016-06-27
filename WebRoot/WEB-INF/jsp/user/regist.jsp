<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户注册</title>
		
		<script type="text/javascript">
			function changeImage(img){
				img.src = img.src + "?" + new Date().getTime();
			}
		</script>
	</head>

<body>
	<br/><br/><br/><br/>
    <form action="${pageContext.request.contextPath}/servlet/RegistServlet" method="post">
    <table width="500px"  align="center" > 
    	<tr>
    		<td colspan="2"> <font color="red"> ${message }</font></td>
    	</tr>
    	<tr height="40px">
    		<td>登录帐号</td>
    		<td>
    			<input type="text" name="username" value="${form.username }">
    			<span>${errors.username }</span>
    		</td>
    	</tr>
    	
    	<tr height="40px">
    		<td>密码</td>
    		<td>
    			<input type="password" name="password"  value="${form.password }">
    			<span>${errors.password }</span>
    		</td>
    	</tr>
    	
    	<tr height="40px">
    		<td>确认密码</td>
    		<td>
    			<input type="password" name="password2" value="${form.password2 }">
    			<span>${errors.password2 }</span>
    		</td>
    	</tr>
    	
    	<tr height="40px">
    		<td>邮箱</td>
    		<td>
    			<input type="text" name="email" value="${form.email }">
    			<span>${errors.email }</span>
    		</td>
    	</tr>
    	
    	<tr height="40px">
    		<td>图片认证</td>
    		<td>
    			<input type="text" name="checkcode">
				<img src="${pageContext.request.contextPath}/servlet/CheckPictureServlet" onclick="changeImage(this)" alt="换一张" style="cursor:hand">
				<span>${errors.check }</span>
    		</td>
    	</tr>
    	
    	<tr height="40px">
    		<td>
    			
    		</td>
    		<td>
    			<input type="submit" value="注册">
    		</td>
    	</tr>
    	
    	
    </table>
    </form>
  </body>
</html>
