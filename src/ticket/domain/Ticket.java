package ticket.domain;

public class Ticket {

	private int tid;
	private String fromwhere;
	private String towhere;
	private String gotime;
	private int number;
	private double price;
	public String getFromwhere() {
		return fromwhere;
	}
	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
	}
	public String getTowhere() {
		return towhere;
	}
	public void setTowhere(String towhere) {
		this.towhere = towhere;
	}
	public String getGotime() {
		return gotime;
	}
	public void setGotime(String gotime) {
		this.gotime = gotime;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Ticket [tid=" + tid + ", fromwhere=" + fromwhere + ", towhere="
				+ towhere + ", gotime=" + gotime + ", number=" + number
				+ ", price=" + price + "]";
	}
	
	
	
	
	
}
