package order.domain;

import java.util.Date;
import java.util.List;

import user.domain.User;

public class Order {

	private String oid;//订单号
	private Date orderTime;//下单时间
	private double total;//合计
	private int state;  //订单的状态  1未付款  2已付款但下单者尚未到车站领取  3下单者已领取订单所列车票    4在付款之前取消订单    5退款（已付款，在领取车票之前退款）
	private User owner; //下单人
	
	private List<OrderItem> orderItemList;  //当前订单下所有条目
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderTime=" + orderTime + ", total="
				+ total + ", state=" + state + ", owner=" + owner + "]";
	}
	
	
}
