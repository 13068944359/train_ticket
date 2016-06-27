package user.service;

import java.util.List;

import user.dao.UserDao;
import user.domain.User;

public class UserService {

	private UserDao dao = new UserDao();
	//ע�Ṧ��
	public void regeist(User form) throws UserException{
		
		User user = dao.findByName(form.getUsername());//����û���
		if(user != null) throw new UserException("�û����ѱ�ע�ᣡ");
		
		user = dao.findByEmail(form.getEmail());//�������
		if(user != null) throw new UserException("�����ѱ�ע�ᣡ");
		
		dao.add(form);//�����û������ݿ�
	}
	
	//�����
	public void active(String code) throws UserException{
		//ʹ��code��ѯ���ݿ�
		User user = dao.findByCode(code);
		if(user == null) throw new UserException("��������Ч��");//������ݿ����޸���Ŀ˵�����������
		if(user.isState()) throw new UserException("���Ѿ����ڼ���״̬���벻Ҫ�ظ�����");//�����û��˻��ļ���״̬
		dao.updateState(user.getUid(), true);//�޸��û�����״̬
	}
	
	
	//���ڹ���
	public void deFriend(String uid){
		dao.deFriend(uid);
	}
	
	
	//��¼����
	public User login(User form) throws UserException{
		User user = dao.findByName(form.getUsername());//�����ݿ�ȡ��user
		if(user == null) throw new UserException("�û���������");//���userΪnull,�û������ڣ��׳��쳣
		if(!user.getPassword().equals(form.getPassword()))//�������������ݿ�洢���벻ͬ���׳��쳣
			throw new UserException("�������");
		if(!user.isState()) {
			if(user.getCode().equals("no")){
				throw new UserException("���˻��ѱ����뱾ϵͳ���������������¼����");//��ֹ�����ڵ��û���¼
			}
			throw new UserException("�˻���δ������ȼ����ٵ�¼");//�û���δ����׳��쳣
		}
			
		return user;
	}
	
	//��������û���Ϣ
	public List<User> getUserList(){
		return dao.getAllUser();
	}
	
	//���ָ���û�����Ϣ
	public User getUserByName(String name){
		return dao.findByName(name);
	}
}
