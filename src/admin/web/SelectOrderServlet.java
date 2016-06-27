package admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.OrderService;
import admin.web.bean.UserAndOrderList;
import user.domain.User;
import user.service.UserService;

public class SelectOrderServlet extends HttpServlet {

	/**
	 * 获得指定用户的订单信息
	 */
	private OrderService orderservice = new OrderService();
	private UserService userservice = new UserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// 先创建出用于封装所有订单信息的list（包涵用户信息）
		List<UserAndOrderList> listOfAllMessage = new ArrayList<UserAndOrderList>();

		//获得想查询的用户的名称
		String name = request.getParameter("select");
		
		// 先获得指定用户的信息
		User user = userservice.getUserByName(name);
		
		if(user==null){       //用户名不存在
			String errorName = "用户名不存在";
			request.setAttribute("errorName", errorName);
			request.getRequestDispatcher("/admin/FindAllOrder").forward(request, response);
			return;
		}

		//把指定用户的相关信息全部封装
		UserAndOrderList userandorder = new UserAndOrderList();
		userandorder.setUser(user);
		userandorder.setOrderList(orderservice.myOrders(user.getUid()));
		listOfAllMessage.add(userandorder);
		

		request.setAttribute("listOfAllMessage", listOfAllMessage);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/showAllOrder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
