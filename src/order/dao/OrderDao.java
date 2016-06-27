package order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import order.domain.Order;
import order.domain.OrderItem;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.commons.CommonUtils;
import ticket.domain.Ticket;
import utils.JdbcUtil;

public class OrderDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDs());
	
	//��Ӷ���       �˴���Ҫ����util��dateת����sql��timestamp
	public void addOrder(Order order){
		try {
			String sql = "insert into orders values(?,?,?,?,?)";
			sql = "insert into orders values(?,?,?,?,?)";
			
			Timestamp timestamp = new Timestamp(order.getOrderTime().getTime());
			
			Object[] param = {order.getOid(), timestamp, order.getTotal(),
					order.getState(),order.getOwner().getUid()};
			qr.update(sql , param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//���붩����Ŀ        ������      
	//���ɶ�����Ŀ��ͬʱ�����ݿ���ָ����Ʊ����Ʊ����ҲҪ����Ӧ�ļ���
	public void addOrderItemList(List<OrderItem> orderItemList){
		try {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			String sql2 = "update ticket set number=number-? where tid=?";
			
			Object[][] params = new Object[ orderItemList.size() ][];
			Object[][] params2 = new Object[ orderItemList.size() ][];
			//ѭ������list
			for(int i = 0; i < orderItemList.size(); i++){
				OrderItem item = orderItemList.get(i);
				params[i] = new Object[]{  item.getIid(), item.getCount(),
						item.getSubtotal(), item.getOrder().getOid(),
						item.getTicket().getTid()    };
				params2[i] = new Object[]{
						item.getCount(),item.getTicket().getTid()
				};
			}
			
			qr.batch(sql, params);
			qr.batch(sql2, params2);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	

	//��ɽ���
	public void finishTransaction(String oid) {
		try {
			String sql = "update orders set state='3' where oid='" + oid +"'";
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//����
	public void pay(String oid) {
		try {
			String sql = "update orders set state='2' where oid='" + oid +"'";
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//ͨ�������Ż�ȡ�ö�����Ϣ�Լ���Ӧ�ĳ�Ʊ��Ϣ
	public List<Map<String, Object>> findOrderItemListByOid(String oid) {
		try {
			String sql = "select * from orderitem o,ticket t where o.tid=t.tid and oid='" + oid + "'";
			List<Map<String, Object>> mapList = (List<Map<String, Object>>) qr.query(sql, new MapListHandler());
			
			return mapList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//�ͻ��˿����Ӧ�ĳ�Ʊ��������������
	public void reFundToTicket(String tid, String count) {
		try {
			String sql = "update ticket set number=number+? where tid=?";
			Object[] param = { count ,tid};
			qr.update(sql , param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//�޸�oid��Ӧ�Ķ�����state����
	public void changeStateByOid(String oid , int state) {
		try {
			String sql = "update orders set state=? where oid=?";
			Object[] param = { state ,oid};
			qr.update(sql , param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//����Oid��ö�Ӧ�Ķ�����Ϣ
	public Order findOrderByOid(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Object[] params = {oid};
			return (Order) qr.query(sql, new BeanHandler(Order.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	//��ѯָ���û��Ķ���
	public List<Order> findByUid(String uid) {
		try {
			//�õ���ǰ�û������ж���      //����ʱ������
			String sql = "select * from orders where uid='" + uid + "' order by ordertime desc";
			List<Order> orderList = (List<Order>) qr.query(sql, new BeanListHandler(Order.class));
			//ѭ������ÿ��order��Ϊ��������Լ����еĶ�����Ŀ
			for(Order order : orderList){
				loadOrderItems(order);//Ϊorder��������������ж�����Ŀ
			}
			
			//���ض����б�
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	
	
	
	
	public void loadOrderItems(Order order) throws SQLException{
		//��ѯ���ű�
		String sql = "select * from orderitem i, ticket t where i.tid=t.tid and oid='" + order.getOid() + "'";
		
		//�������Ӧ�Ĳ�����javabean���Բ�����BeanListHandler��
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) qr.query(sql, new MapListHandler());
		
		//ÿ��map��Ӧһ�н����         
		//ÿ��map������������
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}
	
	//��ÿ��mapת�����������󲢽�����ϵ
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	//��һ��mapת����һ��oderItem����
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Ticket ticket = CommonUtils.toBean(map, Ticket.class);
		orderItem.setTicket(ticket);
		return orderItem;
	}
	
}
