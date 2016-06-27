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
	
	//添加订单       此处需要处理util的date转换成sql的timestamp
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
	
	//插入订单条目        批处理      
	//生成订单条目的同时，数据库中指定车票的余票数量也要做相应的减少
	public void addOrderItemList(List<OrderItem> orderItemList){
		try {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			String sql2 = "update ticket set number=number-? where tid=?";
			
			Object[][] params = new Object[ orderItemList.size() ][];
			Object[][] params2 = new Object[ orderItemList.size() ][];
			//循环遍历list
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
	

	//完成交易
	public void finishTransaction(String oid) {
		try {
			String sql = "update orders set state='3' where oid='" + oid +"'";
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//付款
	public void pay(String oid) {
		try {
			String sql = "update orders set state='2' where oid='" + oid +"'";
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//通过订单号获取该订单信息以及对应的车票信息
	public List<Map<String, Object>> findOrderItemListByOid(String oid) {
		try {
			String sql = "select * from orderitem o,ticket t where o.tid=t.tid and oid='" + oid + "'";
			List<Map<String, Object>> mapList = (List<Map<String, Object>>) qr.query(sql, new MapListHandler());
			
			return mapList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//客户退款，对响应的车票的数量做出调整
	public void reFundToTicket(String tid, String count) {
		try {
			String sql = "update ticket set number=number+? where tid=?";
			Object[] param = { count ,tid};
			qr.update(sql , param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//修改oid对应的订单的state属性
	public void changeStateByOid(String oid , int state) {
		try {
			String sql = "update orders set state=? where oid=?";
			Object[] param = { state ,oid};
			qr.update(sql , param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//根据Oid获得对应的订单信息
	public Order findOrderByOid(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Object[] params = {oid};
			return (Order) qr.query(sql, new BeanHandler(Order.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	//查询指定用户的订单
	public List<Order> findByUid(String uid) {
		try {
			//得到当前用户的所有订单      //按照时间排序
			String sql = "select * from orders where uid='" + uid + "' order by ordertime desc";
			List<Order> orderList = (List<Order>) qr.query(sql, new BeanListHandler(Order.class));
			//循环遍历每个order，为其加载他自己所有的订单条目
			for(Order order : orderList){
				loadOrderItems(order);//为order对象添加他的所有订单条目
			}
			
			//返回订单列表
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	
	
	
	
	public void loadOrderItems(Order order) throws SQLException{
		//查询两张表
		String sql = "select * from orderitem i, ticket t where i.tid=t.tid and oid='" + order.getOid() + "'";
		
		//结果集对应的不再是javabean所以不能用BeanListHandler了
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) qr.query(sql, new MapListHandler());
		
		//每个map对应一行结果集         
		//每个map生成两个对象
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}
	
	//把每个map转换成两个对象并建立关系
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	//把一个map转换成一个oderItem对象
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Ticket ticket = CommonUtils.toBean(map, Ticket.class);
		orderItem.setTicket(ticket);
		return orderItem;
	}
	
}
