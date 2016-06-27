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
		
		String select = request.getParameter("select");   //获取对方查看票的筛选标记
		if(request.getParameter("doselect")!=null){   //需要筛选票
			
			PageBean bean = ticketservice.findByFromwhere(info, select);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
			return;
		}
		
		
		if(select == null){       //用户刚进入车票管理系统 ，尚未有任何标记
			
			PageBean bean = ticketservice.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
			return;
		}
		
		
		
		int page = Integer.parseInt(request.getParameter("page")); //获取对方请求该筛选方式下想查看的页数
		
		if(select.equals("all")){ //对方没有进行筛选
			
			info.setCurrentPage(page);//对相关数据进行封装完毕
			
			PageBean bean = ticketservice.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/adminTicket.jsp").forward(request, response);
		}else{
			
			info.setCurrentPage(page);//对相关数据进行封装完毕
			
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
