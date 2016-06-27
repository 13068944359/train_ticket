package user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.weld.util.collections.EnumerationList;

import cart.domain.Cart;
import user.domain.User;
import user.service.UserException;
import user.service.UserService;
import user.utils.RegistBeanUtil;
import user.utils.UserUtils;

public class LoginServlet extends HttpServlet {

	private UserService service = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		User form = RegistBeanUtil.request2Bean(request, User.class);
		
		//先对提交的信息进行校验
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()){
			request.setAttribute("msge", "用户名不能为空");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			request.setAttribute("msge", "密码不能为空");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}
		
		//校验提交的账户密码是否为管理员账户密码
		if(form.getUsername().equals(this.getInitParameter("adminAccount")) && form.getPassword().equals(this.getInitParameter("adminPassword"))){
			//确认是管理员登录，保存状态到session域，并跳转到管理员页面
			request.getSession().invalidate();
			request.getSession().setAttribute("admin", "admin");
			response.sendRedirect(request.getContextPath()+"/admin/IndexServlet");
			return;
		}
		
		
		
		//访问service层的login方法
		try {
			form.setPassword(UserUtils.md5(form.getPassword()));
			User user = service.login(form);
			request.getSession().setAttribute("session_user", user); 
			request.getSession().setAttribute("cart", new Cart());             //给登录成功的用户添加一个购物车
			response.sendRedirect(request.getContextPath()+"/index.jsp");     //登录成功，把用户数据存到session域并重定向到homePage
		} catch (UserException e) {  //登录异常，带着异常信息和表单数据返回登录页面
			request.setAttribute("msge", e.getMessage());
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
