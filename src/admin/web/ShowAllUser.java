package admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.domain.User;
import user.service.UserService;

public class ShowAllUser extends HttpServlet {

	private UserService userservice = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		List<User> userList = userservice.getUserList();
		String count = userList.size() + "";
		
		request.setAttribute("userList", userList); //�����˻���Ϣ�浽request
		request.setAttribute("count", count);     //�˻��ĸ����浽request
		request.getRequestDispatcher("/WEB-INF/jsp/admin/showAllUser.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
