package admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.OrderService;
import user.domain.User;
import user.service.UserService;

public class OrderSystem extends HttpServlet {

	private OrderService orderservice = new OrderService();
	private UserService userservice = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/orderSystem.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
