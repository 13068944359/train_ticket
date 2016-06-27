package order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.OrderService;
import order.service.ServiceException;

public class CancelOrderServlet extends HttpServlet {

	
	/**
	 * �ڸ���֮ǰȡ�����������׹رգ� state: 1-->4
	 */
	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String oid =  request.getParameter("oid");
		try {
			System.out.println(23);
			service.CancelOrder(oid);
		} catch (ServiceException e) {
			request.getSession().setAttribute("msg", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/message.jsp");
			return;
		}
		
		request.getSession().setAttribute("msg", "ȡ�������ɹ�");
		response.sendRedirect(request.getContextPath() + "/message.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
