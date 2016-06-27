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

public class ChangePageServlet extends HttpServlet {

	private ticketService service = new ticketService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String select = request.getParameter("select");   //��ȡ�Է��鿴Ʊ��ɸѡ���
		int page = Integer.parseInt(request.getParameter("page")); //��ȡ�Է������ɸѡ��ʽ����鿴��ҳ��
		
		if(select.equals("all")){ //�Է�û�н���ɸѡ
			QueryInfo info = new QueryInfo();
			info.setCurrentPage(page);//��������ݽ��з�װ���
			
			PageBean bean = service.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			QueryInfo info = new QueryInfo();
			info.setCurrentPage(page);//��������ݽ��з�װ���
			
			PageBean bean = service.findByFromwhere(info, select);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
