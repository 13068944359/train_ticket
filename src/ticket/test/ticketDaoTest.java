package ticket.test;

import java.util.ArrayList;
import org.junit.Test;

import ticket.dao.QueryResult;
import ticket.dao.ticketDao;
import ticket.domain.Ticket;

public class ticketDaoTest {

	ticketDao dao = new ticketDao();
	@Test
	public void testAdd(){
		
		
		Ticket ti = new Ticket();
		ti.setFromwhere("湛江");
		ti.setTowhere("汕头");
		ti.setGotime("2016-05-21 08:00:00");
		ti.setNumber(120);
		ti.setPrice(220.0);
		
		dao.add(ti);
	}
	
	
	@Test
	public void testFindAll(){
		ArrayList<Ticket> list =  (ArrayList<Ticket>) dao.findAll();
		while(!list.isEmpty()){
			Ticket ti = list.get(0);
			System.out.println(ti.toString());
			list.remove(0);
		}
	}
	
	
	@Test
	public void testFindAll2(){
		QueryResult qr = dao.findAll(2, 6);
		ArrayList<Ticket> list = qr.getList();
		while(!list.isEmpty()){
			Ticket ti = list.get(0);
			System.out.println(ti.toString());
			list.remove(0);
		}
		System.out.println("-----------");
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testFindbyfrom(){
		QueryResult qr = dao.findByFromWhere(0, 6 ,"广州");
		ArrayList<Ticket> list = qr.getList();
		while(!list.isEmpty()){
			Ticket ti = list.get(0);
			System.out.println(ti.toString());
			list.remove(0);
		}
		System.out.println("-----------");
		System.out.println(qr.getTotalRecord());
	}
	
	
	@Test
	public void testFindbyTid(){
		String tid = "3";
		Ticket ticket = dao.findByTid(tid);
		System.out.println(ticket.toString());
	}
}
