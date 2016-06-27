package admin.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.UserService;

public class DefriendServlet extends HttpServlet {

	private UserService userservice = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String uid = request.getParameter("uid");
		
		userservice.deFriend(uid);
		
		response.sendRedirect(request.getContextPath()+"/admin/ShowAllUser");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
