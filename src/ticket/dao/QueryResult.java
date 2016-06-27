package ticket.dao;

import java.util.ArrayList;

import ticket.domain.Ticket;

public class QueryResult {

	private ArrayList<Ticket> list ;
	private int totalRecord;
	
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
	
	
}
