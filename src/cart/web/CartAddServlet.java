package cart.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.domain.Cart;
import cart.domain.CartItem;
import ticket.domain.Ticket;
import ticket.service.ticketService;

public class CartAddServlet extends HttpServlet {

	private ticketService service = new ticketService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Cart cart = (Cart) request.getSession().getAttribute("cart");  //从session中得到用户的购物车
		
		//从request中获得用户请求的tid和数量     （从表单中获得）
		String tid = request.getParameter("tid");
		Ticket ticket = service.findByTid(tid);
		int count = Integer.parseInt(request.getParameter("count"));
		if(count>ticket.getNumber()){
			request.getSession().setAttribute("msg", "请不要进行非法操作，谢谢合作");  //用户绕过javascrip提交车票的数目
			response.sendRedirect(request.getContextPath()+"/message.jsp");
			return;
		}
		CartItem item = new CartItem(); //得到条目
		item.setTicket(ticket);
		item.setCount(count);
		
		cart.add(item);//把条目添加到车
		
		response.sendRedirect(request.getContextPath()+"/servlet/showCartServlet");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
