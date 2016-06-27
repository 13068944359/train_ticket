package ticket.test;

import org.junit.Test;

import ticket.service.ticketService;
import ticket.web.bean.PageBean;
import ticket.web.bean.QueryInfo;

public class ticketServiceTest {

	ticketService service = new ticketService();
	
	@Test
	public void testFind(){
		QueryInfo info = new QueryInfo();
		PageBean bean = service.findAll(info);
		System.out.println(bean.toString());
		
	}
}
