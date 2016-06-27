package ticket.service;

import ticket.dao.QueryResult;
import ticket.dao.ticketDao;
import ticket.domain.Ticket;
import ticket.web.bean.PageBean;
import ticket.web.bean.QueryInfo;

public class ticketService {

	private ticketDao dao = new ticketDao();
	
	public PageBean findAll(QueryInfo info){
		QueryResult qr = dao.findAll(info.getStartIndex(),info.getPageSize());
		PageBean bean = new PageBean();
		
		bean.setList(qr.getList());
		bean.setTotalRecord(qr.getTotalRecord());
		bean.setCurrentPage(info.getCurrentPage());
		bean.setSelect("all");  //用于标记用户的筛选方式，此处为用户显示所有票
		
		if(qr.getTotalRecord()%info.getPageSize()==0){ //确认总页数是否整除页面条数，以确定页面数
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize());
		}else{
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize() + 1);
		}
		
		if(info.getCurrentPage()==1){ //请求页数大于1，则存在上一页，否则赋值-1（jsp页面用于控制超链接显示与否）
			bean.setPreviousPage(-1);
		}else{
			bean.setPreviousPage(info.getCurrentPage()-1);
		}
		if(info.getCurrentPage()==bean.getTotalPage() || bean.getTotalPage()==0){ //请求页数小于最大页数，则存在下一页，否则赋值-1（jsp页面用于控制超链接显示与否）
			bean.setNextPage(-1);
		}else{
			bean.setNextPage(info.getCurrentPage()+1);
		}
		return bean;
	}
	
	public PageBean findByFromwhere(QueryInfo info, String select){
		QueryResult qr = dao.findByFromWhere(info.getStartIndex(),info.getPageSize(),select);
		PageBean bean = new PageBean();
		
		bean.setList(qr.getList());
		bean.setTotalRecord(qr.getTotalRecord());
		bean.setCurrentPage(info.getCurrentPage());
		bean.setSelect(select);  //用于标记用户的筛选方式，此处为用户显示所有票
		
		if(qr.getTotalRecord()%info.getPageSize()==0){ //确认总页数是否整除页面条数，以确定页面数
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize());
		}else{
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize() + 1);
		}
		
		if(info.getCurrentPage()==1){ //请求页数大于1，则存在上一页，否则赋值-1（jsp页面用于控制超链接显示与否）
			bean.setPreviousPage(-1);
		}else{
			bean.setPreviousPage(info.getCurrentPage()-1);
		}
		if(info.getCurrentPage()==bean.getTotalPage() || bean.getTotalPage()==0){ //请求页数小于最大页数，则存在下一页，否则赋值-1（jsp页面用于控制超链接显示与否）
			bean.setNextPage(-1);
		}else{
			bean.setNextPage(info.getCurrentPage()+1);
		}
		return bean;
		
	}
	
	//通过tid获得票的数据
	public Ticket findByTid(String tid){
		dao.findByTid(tid);
		
		return dao.findByTid(tid);
	}
	
	
	//修改tid对应的ticket的number
	public void ChangeNumberByTid(String tid, String number){
		dao.ChangeNumberByTid(tid, number);
	}
}
