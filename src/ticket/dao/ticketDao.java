package ticket.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ticket.domain.Ticket;
import utils.JdbcUtil;

public class ticketDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDs());
	
	//�����ݿ����Ʊ
	public void add(Ticket ticket){
		try{
			String sql = "insert into ticket(fromwhere,towhere,gotime,number,price) values(?,?,?,?,?)";
			Object[] param = {ticket.getFromwhere(),ticket.getTowhere(),ticket.getGotime(),ticket.getNumber(),ticket.getPrice()};
			qr.update(sql, param);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	//�������Ʊ������
	public List<Ticket> findAll(){
		try{
			String sql = "select * from ticket";
			return (List<Ticket>) qr.query(sql, new BeanListHandler(Ticket.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	//�������Ʊ�����ݵ�һ���֣���ҳ��ʾ��
	public QueryResult findAll(int startIndex, int pageSize){
		try{
			QueryResult result = new QueryResult();
			
			String sql = "select * from ticket where number>0 limit " + startIndex + "," + pageSize;
			result.setList((ArrayList<Ticket>) qr.query(sql,  new BeanListHandler(Ticket.class) ));
			sql = "select count(*) from ticket";
			Object[] total =   (Object[]) qr.query(sql, new ArrayHandler());
			result.setTotalRecord(Integer.parseInt(total[0].toString()));
			return result;
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	//����ó�����ɸѡ����Ʊ������
	public QueryResult findByFromWhere(int startIndex, int pageSize, String select){
		try{
			QueryResult result = new QueryResult();
			
			String sql = "select * from ticket where fromwhere='" + select +"' limit " + startIndex + "," + pageSize;
			result.setList((ArrayList<Ticket>) qr.query(sql,  new BeanListHandler(Ticket.class) ));
			sql = "select count(*) from ticket where fromwhere='" + select + "'";
			Object[] total =   (Object[]) qr.query(sql, new ArrayHandler());
			result.setTotalRecord(Integer.parseInt(total[0].toString()));
			return result;
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
		
	}
	
	//��tid��ö�Ӧ��Ʊ������
	public Ticket findByTid(String tid){
		try{
			String sql = "select * from ticket where tid="+ tid;
			return (Ticket) qr.query(sql, new BeanHandler(Ticket.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}

	public void ChangeNumberByTid(String tid, String number) {
		try{
			String sql = "update ticket set number=? where tid=?";
			Object[] param = {number, tid};
			qr.update(sql, param);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
		
	}
	
	
}
