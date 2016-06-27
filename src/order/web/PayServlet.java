package order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.OrderService;

public class PayServlet extends HttpServlet {

	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oid =  request.getParameter("oid");
		service.pay(oid);
		
		request.getSession().setAttribute("msg", "¸¶¿î³É¹¦");
		response.sendRedirect(request.getContextPath() + "/message.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
