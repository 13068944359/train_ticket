package admin.web.bean;

import java.util.List;

import order.domain.Order;
import user.domain.User;


/**
 * 用于封装所有的订单信息（包涵用户）
 * @author Administrator
 *
 */
public class UserAndOrderList {

	private User user ;
	private List<Order> orderList ;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderlist) {
		this.orderList = orderlist;
	}
	@Override
	public String toString() {
		return "UserAndOrderList [user=" + user + ", orderlist=" + orderList
				+ "]";
	}
	
	
}
