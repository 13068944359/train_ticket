package ticket.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.service.ticketService;
import ticket.web.bean.PageBean;
import ticket.web.bean.QueryInfo;

public class SelectTicketServlet extends HttpServlet {

	private ticketService service = new ticketService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		QueryInfo info = new QueryInfo();
		String select = request.getParameter("select");
		PageBean bean = service.findByFromwhere(info, select);
		request.setAttribute("ticketList", bean);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
