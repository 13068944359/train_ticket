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

		
		//��session�õ�cart
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		//ʹ��cart����order����  �������������
		
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
		 * ���ɶ�����Ŀ����
		 * CartItemList --> OrderItemList
		 */
		List<OrderItem> orderitemlist = new ArrayList<OrderItem>();
		for(CartItem cartItem : cart.getCartItenList()){
			OrderItem oi = new OrderItem();//������������Ŀ
			
			oi.setIid(CommonUtils.uuid());
			oi.setCount(cartItem.getCount());
			oi.setTicket(cartItem.getTicket());
			oi.setSubtotal(cartItem.getSubTatal());
			oi.setOrder(order);
			
			orderitemlist.add(oi);
		}
		
		order.setOrderItemList(orderitemlist);
		
		//��չ��ﳵ
		
		cart.clear();
		
		//����service���������Ӷ���
		
		try {
			service.add(order);
		} catch (ServiceException e) {
			String message = e.getMessage();
			request.getSession().setAttribute("msg", message);
			response.sendRedirect(request.getContextPath()+"/message.jsp"); //�����û����ɶ���ʧ��
		}
		
		//����order��request�У�ת����������������
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/WEB-INF/jsp/order/desc.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
