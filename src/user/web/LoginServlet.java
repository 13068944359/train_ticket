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
		
		//�ȶ��ύ����Ϣ����У��
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()){
			request.setAttribute("msge", "�û�������Ϊ��");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			request.setAttribute("msge", "���벻��Ϊ��");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}
		
		//У���ύ���˻������Ƿ�Ϊ����Ա�˻�����
		if(form.getUsername().equals(this.getInitParameter("adminAccount")) && form.getPassword().equals(this.getInitParameter("adminPassword"))){
			//ȷ���ǹ���Ա��¼������״̬��session�򣬲���ת������Աҳ��
			request.getSession().invalidate();
			request.getSession().setAttribute("admin", "admin");
			response.sendRedirect(request.getContextPath()+"/admin/IndexServlet");
			return;
		}
		
		
		
		//����service���login����
		try {
			form.setPassword(UserUtils.md5(form.getPassword()));
			User user = service.login(form);
			request.getSession().setAttribute("session_user", user); 
			request.getSession().setAttribute("cart", new Cart());             //����¼�ɹ����û����һ�����ﳵ
			response.sendRedirect(request.getContextPath()+"/index.jsp");     //��¼�ɹ������û����ݴ浽session���ض���homePage
		} catch (UserException e) {  //��¼�쳣�������쳣��Ϣ�ͱ����ݷ��ص�¼ҳ��
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
