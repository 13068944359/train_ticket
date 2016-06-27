package order.service;

import java.util.List;
import java.util.Map;

import order.dao.OrderDao;
import order.domain.Order;
import utils.JdbcUtil;

public class OrderService {

	private OrderDao dao = new OrderDao();
	
	public void add(Order order) throws ServiceException{
		
		try {
			
			//开启事务
			JdbcUtil.startTransaction();
			
			dao.addOrder(order);
			dao.addOrderItemList(order.getOrderItemList());
			
			//提交事务
			JdbcUtil.commitTransaction();
			
		} catch (Exception e) {
			// 回滚事务
			JdbcUtil.rollback();
			throw new ServiceException("很抱歉，由于系统出现了故障，生成订单失败，请稍候重试");
		}finally{
			JdbcUtil.release();
		}
	}
	
	//取出我的订单
	public List<Order> myOrders(String uid){
		return dao.findByUid(uid);
	}
	
	public Order findByOid(String oid){
		return dao.findOrderByOid(oid);
	}

	//将制定条目的订单的state修改为2（已经付款）
	public void pay(String oid) {
		dao.pay(oid);
	}

	
	//将制定条目的订单的state修改为3（交易完成）
	public void finishTransaction(String oid){
		dao.finishTransaction(oid);
	}
	
	//退款
	public void reFund(String oid) throws ServiceException {

		try {

			// 开启事务
			JdbcUtil.startTransaction();
			
			//先查询oid对应订单的状态，如果state不是2则要给用户提示操作错误信息
			Order order = dao.findOrderByOid(oid);
			if(order.getState()==5){
				throw new ServiceException("请不要重复退款");     //用户已经退款过了
			}else if(order.getState()!=2){
				throw new ServiceException("您的操作有误，请稍后再试");    //用户尚未付款
			}
			
			//由oid获得 orderItemList
			//根据orderItemList获得个条目的tid和count
			//根据tid和count对ticket表做相应的修改
			//把orders中对应oid的条目的state设置为5(已付款，在领取车票之前退款）
						
			List<Map<String, Object>> mapList = dao.findOrderItemListByOid(oid);
			
			for(Map<String, Object> map : mapList){
				String tid = map.get("tid").toString();	
				String count = map.get("count").toString();	
				dao.reFundToTicket(tid, count);
			}
			
			dao.changeStateByOid(oid , 5);
			

			// 提交事务
			JdbcUtil.commitTransaction();

		} catch (Exception e) {
			// 回滚事务
			JdbcUtil.rollback();
			throw new ServiceException("很抱歉，由于系统出现了故障，退款失败，请稍候重试");
		} finally {
			JdbcUtil.release();
		}

	}

	
	//取消尚未付款的订单
	public void CancelOrder(String oid) throws ServiceException {
		try {

			System.out.println(1);
			// 开启事务
			JdbcUtil.startTransaction();
			
			//先查询oid对应订单的状态，如果state不是1则要给用户提示操作错误信息
			Order order = dao.findOrderByOid(oid);
			if(order.getState()==4){
				throw new ServiceException("该订单已经处于取消状态");     //用户已经取消过该订单
			}else if(order.getState()!=1){
				throw new ServiceException("您的操作有误，请稍后再试");    //用户不是处于尚未付款的状态
			}
			
			//由oid获得 orderItemList
			//根据orderItemList获得个条目的tid和count
			//根据tid和count对ticket表做相应的修改
			//把orders中对应oid的条目的state设置为4
						
			List<Map<String, Object>> mapList = dao.findOrderItemListByOid(oid);
			
			for(Map<String, Object> map : mapList){
				String tid = map.get("tid").toString();	
				String count = map.get("count").toString();	
				dao.reFundToTicket(tid, count);
				System.out.println(2);
			}
			
			dao.changeStateByOid(oid , 4);
			

			// 提交事务
			JdbcUtil.commitTransaction();

		} catch (Exception e) {
			// 回滚事务
			JdbcUtil.rollback();
			throw new ServiceException("很抱歉，由于系统出现了故障，操作失败，请稍候重试");
		} finally {
			JdbcUtil.release();
		}
	}
}
