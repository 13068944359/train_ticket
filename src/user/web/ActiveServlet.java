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

		
		//��ȡ����������
		String code = request.getParameter("code");
		//����service�ļ����
		try{
			service.active(code);
			request.getSession().setAttribute("msg", "��ϲ����ɹ��������ڿ��Ե�¼��Ʊ��վ�ˣ�");
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
