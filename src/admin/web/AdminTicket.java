package admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.service.ticketService;
import ticket.web.bean.PageBean;
import ticket.web.bean.QueryInfo;

public class AdminTicket extends HttpServlet {

	ticketService ticketservice = new ticketService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QueryInfo info = new QueryInfo();
		
		String select = request.getParameter("select");   //��ȡ�Է��鿴Ʊ��ɸѡ���
		if(request.getParameter("doselect")!=null){   //��ҪɸѡƱ
			
			PageBean bean = ticketservice.findByFromwhere(info, select);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
			return;
		}
		
		
		if(select == null){       //�û��ս��복Ʊ����ϵͳ ����δ���κα��
			
			PageBean bean = ticketservice.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
			return;
		}
		
		
		
		int page = Integer.parseInt(request.getParameter("page")); //��ȡ�Է������ɸѡ��ʽ����鿴��ҳ��
		
		if(select.equals("all")){ //�Է�û�н���ɸѡ
			
			info.setCurrentPage(page);//��������ݽ��з�װ���
			
			PageBean bean = ticketservice.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
		}else{
			
			info.setCurrentPage(page);//��������ݽ��з�װ���
			
			PageBean bean = ticketservice.findByFromwhere(info, select);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
