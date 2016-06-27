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

		String select = request.getParameter("select");   //获取对方查看票的筛选标记
		int page = Integer.parseInt(request.getParameter("page")); //获取对方请求该筛选方式下想查看的页数
		
		if(select.equals("all")){ //对方没有进行筛选
			QueryInfo info = new QueryInfo();
			info.setCurrentPage(page);//对相关数据进行封装完毕
			
			PageBean bean = service.findAll(info);
			request.setAttribute("ticketList", bean);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			QueryInfo info = new QueryInfo();
			info.setCurrentPage(page);//对相关数据进行封装完毕
			
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
