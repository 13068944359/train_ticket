package cart.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.domain.Ticket;
import ticket.service.ticketService;
import cart.domain.Cart;

public class BuyTicketServlet extends HttpServlet {

	private ticketService service = new ticketService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String tid = request.getParameter("tid"); //获得想购买票的tid
		Ticket ticket = service.findByTid(tid);
		
		if(ticket != null){
			request.setAttribute("ticket", ticket);
			request.getRequestDispatcher("/WEB-INF/jsp/cart/ticket.jsp").forward(request, response);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
