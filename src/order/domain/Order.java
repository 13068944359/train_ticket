package order.domain;

import java.util.Date;
import java.util.List;

import user.domain.User;

public class Order {

	private String oid;//������
	private Date orderTime;//�µ�ʱ��
	private double total;//�ϼ�
	private int state;  //������״̬  1δ����  2�Ѹ���µ�����δ����վ��ȡ  3�µ�������ȡ�������г�Ʊ    4�ڸ���֮ǰȡ������    5�˿�Ѹ������ȡ��Ʊ֮ǰ�˿
	private User owner; //�µ���
	
	private List<OrderItem> orderItemList;  //��ǰ������������Ŀ
	
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
