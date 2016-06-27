package user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.UserException;
import user.service.UserService;

public class ActiveServlet extends HttpServlet {

	UserService service = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//获取参数激活码
		String code = request.getParameter("code");
		//调用service的激活方法
		try{
			service.active(code);
			request.getSession().setAttribute("msg", "恭喜激活成功，您现在可以登录买票网站了！");
			response.sendRedirect(request.getContextPath()+"/message.jsp");
		}catch(UserException e){
			request.getSession().setAttribute("msg", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/message.jsp");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
