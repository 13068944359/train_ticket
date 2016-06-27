package order.service;

import java.util.List;
import java.util.Map;

import order.dao.OrderDao;
import order.domain.Order;
import utils.JdbcUtil;

public class OrderService {

	private OrderDao dao = new OrderDao();
	
	public void add(Order order) throws ServiceException{
		
		try {
			
			//��������
			JdbcUtil.startTransaction();
			
			dao.addOrder(order);
			dao.addOrderItemList(order.getOrderItemList());
			
			//�ύ����
			JdbcUtil.commitTransaction();
			
		} catch (Exception e) {
			// �ع�����
			JdbcUtil.rollback();
			throw new ServiceException("�ܱ�Ǹ������ϵͳ�����˹��ϣ����ɶ���ʧ�ܣ����Ժ�����");
		}finally{
			JdbcUtil.release();
		}
	}
	
	//ȡ���ҵĶ���
	public List<Order> myOrders(String uid){
		return dao.findByUid(uid);
	}
	
	public Order findByOid(String oid){
		return dao.findOrderByOid(oid);
	}

	//���ƶ���Ŀ�Ķ�����state�޸�Ϊ2���Ѿ����
	public void pay(String oid) {
		dao.pay(oid);
	}

	
	//���ƶ���Ŀ�Ķ�����state�޸�Ϊ3��������ɣ�
	public void finishTransaction(String oid){
		dao.finishTransaction(oid);
	}
	
	//�˿�
	public void reFund(String oid) throws ServiceException {

		try {

			// ��������
			JdbcUtil.startTransaction();
			
			//�Ȳ�ѯoid��Ӧ������״̬�����state����2��Ҫ���û���ʾ����������Ϣ
			Order order = dao.findOrderByOid(oid);
			if(order.getState()==5){
				throw new ServiceException("�벻Ҫ�ظ��˿�");     //�û��Ѿ��˿����
			}else if(order.getState()!=2){
				throw new ServiceException("���Ĳ����������Ժ�����");    //�û���δ����
			}
			
			//��oid��� orderItemList
			//����orderItemList��ø���Ŀ��tid��count
			//����tid��count��ticket������Ӧ���޸�
			//��orders�ж�Ӧoid����Ŀ��state����Ϊ5(�Ѹ������ȡ��Ʊ֮ǰ�˿
						
			List<Map<String, Object>> mapList = dao.findOrderItemListByOid(oid);
			
			for(Map<String, Object> map : mapList){
				String tid = map.get("tid").toString();	
				String count = map.get("count").toString();	
				dao.reFundToTicket(tid, count);
			}
			
			dao.changeStateByOid(oid , 5);
			

			// �ύ����
			JdbcUtil.commitTransaction();

		} catch (Exception e) {
			// �ع�����
			JdbcUtil.rollback();
			throw new ServiceException("�ܱ�Ǹ������ϵͳ�����˹��ϣ��˿�ʧ�ܣ����Ժ�����");
		} finally {
			JdbcUtil.release();
		}

	}

	
	//ȡ����δ����Ķ���
	public void CancelOrder(String oid) throws ServiceException {
		try {

			System.out.println(1);
			// ��������
			JdbcUtil.startTransaction();
			
			//�Ȳ�ѯoid��Ӧ������״̬�����state����1��Ҫ���û���ʾ����������Ϣ
			Order order = dao.findOrderByOid(oid);
			if(order.getState()==4){
				throw new ServiceException("�ö����Ѿ�����ȡ��״̬");     //�û��Ѿ�ȡ�����ö���
			}else if(order.getState()!=1){
				throw new ServiceException("���Ĳ����������Ժ�����");    //�û����Ǵ�����δ�����״̬
			}
			
			//��oid��� orderItemList
			//����orderItemList��ø���Ŀ��tid��count
			//����tid��count��ticket������Ӧ���޸�
			//��orders�ж�Ӧoid����Ŀ��state����Ϊ4
						
			List<Map<String, Object>> mapList = dao.findOrderItemListByOid(oid);
			
			for(Map<String, Object> map : mapList){
				String tid = map.get("tid").toString();	
				String count = map.get("count").toString();	
				dao.reFundToTicket(tid, count);
				System.out.println(2);
			}
			
			dao.changeStateByOid(oid , 4);
			

			// �ύ����
			JdbcUtil.commitTransaction();

		} catch (Exception e) {
			// �ع�����
			JdbcUtil.rollback();
			throw new ServiceException("�ܱ�Ǹ������ϵͳ�����˹��ϣ�����ʧ�ܣ����Ժ�����");
		} finally {
			JdbcUtil.release();
		}
	}
}
