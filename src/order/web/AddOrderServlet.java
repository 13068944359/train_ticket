package order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import order.domain.Order;
import order.domain.OrderItem;
import order.service.OrderService;
import order.service.ServiceException;
import cart.domain.Cart;
import cart.domain.CartItem;
import user.domain.User;

public class AddOrderServlet extends HttpServlet {

	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//从session得到cart
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		//使用cart生成order对象  并设置相关属性
		
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrderTime(new Date());
		order.setState(1);
		User user = (User) request.getSession().getAttribute("session_user");
		order.setOwner(user);
		order.setTotal(cart.getTotal());
		
		if(cart.getCartItenList()==null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		
		/**
		 * 生成订单条目集合
		 * CartItemList --> OrderItemList
		 */
		List<OrderItem> orderitemlist = new ArrayList<OrderItem>();
		for(CartItem cartItem : cart.getCartItenList()){
			OrderItem oi = new OrderItem();//创建订单条条目
			
			oi.setIid(CommonUtils.uuid());
			oi.setCount(cartItem.getCount());
			oi.setTicket(cartItem.getTicket());
			oi.setSubtotal(cartItem.getSubTatal());
			oi.setOrder(order);
			
			orderitemlist.add(oi);
		}
		
		order.setOrderItemList(orderitemlist);
		
		//清空购物车
		
		cart.clear();
		
		//调用service方法完成添加订单
		
		try {
			service.add(order);
		} catch (ServiceException e) {
			String message = e.getMessage();
			request.getSession().setAttribute("msg", message);
			response.sendRedirect(request.getContextPath()+"/message.jsp"); //告诉用户生成订单失败
		}
		
		//保存order到request中，转发到······
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/WEB-INF/jsp/order/desc.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
