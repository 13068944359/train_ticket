package cart.domain;

import java.math.BigDecimal;

import ticket.domain.Ticket;

public class CartItem {

	private Ticket ticket ; //��Ʒ
	private int count;   //����
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
	
	public double getSubTatal(){   //�������С�ơ�  ����û�ж�Ӧ�ĳ�Ա����
		
		BigDecimal d1 = new BigDecimal(ticket.getPrice() + "");    //��������������������
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}
	
}
