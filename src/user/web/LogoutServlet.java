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

		
		request.getSession().invalidate();//����session
		request.getSession().setAttribute("msg", "�˳���¼�ɹ�");
		response.sendRedirect(request.getContextPath()+"/message.jsp"); //�˳���¼�ɹ����ض���ȫ����Ϣ��ʾҳ��
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
