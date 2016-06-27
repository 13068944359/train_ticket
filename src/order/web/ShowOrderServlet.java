package order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.domain.Order;
import order.service.OrderService;
import user.domain.User;

public class ShowOrderServlet extends HttpServlet {

	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//从session得到当前用户
		User user = (User) request.getSession().getAttribute("session_user");
				
		//使用uid得到用户所有订单
		List<Order> orderList = service.myOrders(user.getUid());
		//把订单保存到request中，转发到jsp页面
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
