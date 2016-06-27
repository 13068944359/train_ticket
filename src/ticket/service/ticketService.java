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
		bean.setSelect("all");  //���ڱ���û���ɸѡ��ʽ���˴�Ϊ�û���ʾ����Ʊ
		
		if(qr.getTotalRecord()%info.getPageSize()==0){ //ȷ����ҳ���Ƿ�����ҳ����������ȷ��ҳ����
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize());
		}else{
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize() + 1);
		}
		
		if(info.getCurrentPage()==1){ //����ҳ������1���������һҳ������ֵ-1��jspҳ�����ڿ��Ƴ�������ʾ���
			bean.setPreviousPage(-1);
		}else{
			bean.setPreviousPage(info.getCurrentPage()-1);
		}
		if(info.getCurrentPage()==bean.getTotalPage() || bean.getTotalPage()==0){ //����ҳ��С�����ҳ�����������һҳ������ֵ-1��jspҳ�����ڿ��Ƴ�������ʾ���
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
		bean.setSelect(select);  //���ڱ���û���ɸѡ��ʽ���˴�Ϊ�û���ʾ����Ʊ
		
		if(qr.getTotalRecord()%info.getPageSize()==0){ //ȷ����ҳ���Ƿ�����ҳ����������ȷ��ҳ����
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize());
		}else{
			bean.setTotalPage(qr.getTotalRecord()/info.getPageSize() + 1);
		}
		
		if(info.getCurrentPage()==1){ //����ҳ������1���������һҳ������ֵ-1��jspҳ�����ڿ��Ƴ�������ʾ���
			bean.setPreviousPage(-1);
		}else{
			bean.setPreviousPage(info.getCurrentPage()-1);
		}
		if(info.getCurrentPage()==bean.getTotalPage() || bean.getTotalPage()==0){ //����ҳ��С�����ҳ�����������һҳ������ֵ-1��jspҳ�����ڿ��Ƴ�������ʾ���
			bean.setNextPage(-1);
		}else{
			bean.setNextPage(info.getCurrentPage()+1);
		}
		return bean;
		
	}
	
	//ͨ��tid���Ʊ������
	public Ticket findByTid(String tid){
		dao.findByTid(tid);
		
		return dao.findByTid(tid);
	}
	
	
	//�޸�tid��Ӧ��ticket��number
	public void ChangeNumberByTid(String tid, String number){
		dao.ChangeNumberByTid(tid, number);
	}
}
