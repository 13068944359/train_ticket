package order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.OrderService;
import order.service.ServiceException;

public class RefundServlet extends HttpServlet {

	/**
	 * �˿�������ͻ��Ѿ����δ��ȡ��Ʊ��state = 2��
	 */
	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String oid =  request.getParameter("oid");
		try {
			service.reFund(oid);
		} catch (ServiceException e) {
			request.getSession().setAttribute("msg", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/message.jsp");
			return;
		}
		
		request.getSession().setAttribute("msg", "�˿�ɹ�");
		response.sendRedirect(request.getContextPath() + "/message.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
