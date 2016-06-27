package user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.UserService;

public class LogoutServlet extends HttpServlet {

	private UserService service = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.getSession().invalidate();//销毁session
		request.getSession().setAttribute("msg", "退出登录成功");
		response.sendRedirect(request.getContextPath()+"/message.jsp"); //退出登录成功，重定向全局消息显示页面
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
