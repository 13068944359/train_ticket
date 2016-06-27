package ticket.web.bean;

import java.util.ArrayList;

import ticket.domain.Ticket;

public class PageBean {

	private ArrayList<Ticket> list ;
	private int totalRecord;
	private int previousPage;
	private int nextPage;
	private int totalPage;
	private int currentPage;
	private String select;
	public ArrayList<Ticket> getList() {
		return list;
	}
	public void setList(ArrayList<Ticket> list) {
		this.list = list;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	@Override
	public String toString() {
		return "PageBean [list=" + list + ", totalRecord=" + totalRecord
				+ ", previousPage=" + previousPage + ", nextPage=" + nextPage
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ "]";
	}
	
	
	
}
