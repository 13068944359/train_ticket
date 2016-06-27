package order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.domain.Order;
import order.service.OrderService;
import user.domain.User;

public class ShowOrderServlet extends HttpServlet {

	private OrderService service = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��session�õ���ǰ�û�
		User user = (User) request.getSession().getAttribute("session_user");
				
		//ʹ��uid�õ��û����ж���
		List<Order> orderList = service.myOrders(user.getUid());
		//�Ѷ������浽request�У�ת����jspҳ��
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
