package admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.web.bean.UserAndOrderList;
import order.service.OrderService;
import user.domain.User;
import user.service.UserService;

public class FindAllOrder extends HttpServlet {

	private OrderService orderservice = new OrderService();
	private UserService userservice = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//先创建出用于封装所有订单信息的list（包涵用户信息）
		List<UserAndOrderList> listOfAllMessage = new ArrayList<UserAndOrderList>();
		
		
		//先获得所有用户的信息
		List<User> userlist = userservice.getUserList();
		
		//按用户遍历所有的订单       每个用户所有的信息都封装到UserAndOrderList的实例对象中
		for(User user : userlist){
			UserAndOrderList userandorder = new UserAndOrderList();
			userandorder.setUser(user);
			userandorder.setOrderList(orderservice.myOrders(user.getUid()));
			listOfAllMessage.add(userandorder);
		}
		
		request.setAttribute("listOfAllMessage", listOfAllMessage);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/showAllOrder.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
