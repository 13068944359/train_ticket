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

		
		Cart cart = (Cart) request.getSession().getAttribute("cart");  //��session�еõ��û��Ĺ��ﳵ
		
		//��request�л���û������tid������     ���ӱ��л�ã�
		String tid = request.getParameter("tid");
		Ticket ticket = service.findByTid(tid);
		int count = Integer.parseInt(request.getParameter("count"));
		if(count>ticket.getNumber()){
			request.getSession().setAttribute("msg", "�벻Ҫ���зǷ�������лл����");  //�û��ƹ�javascrip�ύ��Ʊ����Ŀ
			response.sendRedirect(request.getContextPath()+"/message.jsp");
			return;
		}
		CartItem item = new CartItem(); //�õ���Ŀ
		item.setTicket(ticket);
		item.setCount(count);
		
		cart.add(item);//����Ŀ��ӵ���
		
		response.sendRedirect(request.getContextPath()+"/servlet/showCartServlet");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
