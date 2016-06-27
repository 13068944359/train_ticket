package order.domain;

import ticket.domain.Ticket;

public class OrderItem {

	private String iid;
	private int count;//����
	private double subtotal;//С��
	private Order order;//��������
	private Ticket ticket;//��Ҫ�����Ʊ
	
	
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
