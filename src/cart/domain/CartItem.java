package cart.domain;

import java.math.BigDecimal;

import ticket.domain.Ticket;

public class CartItem {

	private Ticket ticket ; //商品
	private int count;   //数量
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItem [ticket=" + ticket + ", count=" + count + "]";
	}
	
	public double getSubTatal(){   //计算出“小计”  ，但没有对应的成员属性
		
		BigDecimal d1 = new BigDecimal(ticket.getPrice() + "");    //处理二进制运算误差问题
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}
	
}
