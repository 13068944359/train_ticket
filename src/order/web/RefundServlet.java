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
	 * 退款操作（客户已经付款但未领取车票，state = 2）
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
		
		request.getSession().setAttribute("msg", "退款成功");
		response.sendRedirect(request.getContextPath() + "/message.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
