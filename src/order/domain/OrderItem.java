package order.domain;

import ticket.domain.Ticket;

public class OrderItem {

	private String iid;
	private int count;//数量
	private double subtotal;//小计
	private Order order;//所属订单
	private Ticket ticket;//所要购买的票
	
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subTotal) {
		this.subtotal = subTotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal="
				+ subtotal + ", order=" + order + ", ticket=" + ticket + "]";
	}
	
	
}
