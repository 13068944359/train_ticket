package admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.domain.Order;
import order.service.OrderService;


/**
 * 处理发放车票的请求，将指定订单的state修改为3（完成交易）
 * @author Administrator
 *
 */
public class giveTicketServlet extends HttpServlet {

	private OrderService orderservice = new OrderService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//交易系统提交的
		String oid = request.getParameter("oid");
		orderservice.finishTransaction(oid);
		
		response.sendRedirect(request.getContextPath()+"/admin/FindAllOrder");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查看订单提交的

		String oid = request.getParameter("oid");
		
		//先确认订单是否存在
		//确认订单是否处于已付款未领取的状态，否则不能发放车票
		Order order = orderservice.findByOid(oid);
		if(order == null){
			oid = "订单号：" + oid + " 不存在，请核查是否输入错误";
		}else if(order.getState() == 3){
			oid = "订单号：" + oid + " 已领取过车票，请勿重复领取";
		}else if(order.getState() == 1 || order.getState() == 4){
			oid = "订单号：" + oid + " 尚未付款，不能领取车票";
		}else if(order.getState() == 5){
			oid = "订单号：" + oid + " 已完成退款操作，不能领取车票";
		}else{
			orderservice.finishTransaction(oid);
			oid = "订单号：" + oid + " 已完成登记";
		}
		request.setAttribute("oid", oid);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/orderSystem.jsp").forward(request, response);

	}

}
