package admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.domain.Order;
import order.service.OrderService;


/**
 * �����ų�Ʊ�����󣬽�ָ��������state�޸�Ϊ3����ɽ��ף�
 * @author Administrator
 *
 */
public class giveTicketServlet extends HttpServlet {

	private OrderService orderservice = new OrderService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//����ϵͳ�ύ��
		String oid = request.getParameter("oid");
		orderservice.finishTransaction(oid);
		
		response.sendRedirect(request.getContextPath()+"/admin/FindAllOrder");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�鿴�����ύ��

		String oid = request.getParameter("oid");
		
		//��ȷ�϶����Ƿ����
		//ȷ�϶����Ƿ����Ѹ���δ��ȡ��״̬�������ܷ��ų�Ʊ
		Order order = orderservice.findByOid(oid);
		if(order == null){
			oid = "�����ţ�" + oid + " �����ڣ���˲��Ƿ��������";
		}else if(order.getState() == 3){
			oid = "�����ţ�" + oid + " ����ȡ����Ʊ�������ظ���ȡ";
		}else if(order.getState() == 1 || order.getState() == 4){
			oid = "�����ţ�" + oid + " ��δ���������ȡ��Ʊ";
		}else if(order.getState() == 5){
			oid = "�����ţ�" + oid + " ������˿������������ȡ��Ʊ";
		}else{
			orderservice.finishTransaction(oid);
			oid = "�����ţ�" + oid + " ����ɵǼ�";
		}
		request.setAttribute("oid", oid);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/orderSystem.jsp").forward(request, response);

	}

}
