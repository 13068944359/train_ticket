package user.test;

import org.junit.Test;

import user.dao.UserDao;
import user.domain.User;

public class UserDaoTest {

	@Test
	public void testAdd(){
		User user = new User();
		user.setUid("123sd");
		user.setUsername("sdfsdf");
		user.setPassword("asdfasfd");
		user.setEmail("sadfasdfasdf");
		user.setCode("sadfasdfasfd");
		user.setState(false);
		UserDao dao = new UserDao();
		dao.add(user);
		
	}
	@Test
	public void testFindByEmail(){
		
		UserDao dao = new UserDao();
		User user = dao.findByEmail("sadfasdfasdf");
		System.out.println(user.getUid());
	}
	
	@Test
	public void testFindByName(){
		
		UserDao dao = new UserDao();
		User user = dao.findByName("sdfsdf");
		System.out.println(user);
	}
	
	@Test
	public void testFindByCode(){
		
		UserDao dao = new UserDao();
		User user = dao.findByCode("sadfasdfasfd");
		System.out.println(user.toString());
	}
	
	@Test
	public void updateState(){
		UserDao dao = new UserDao();
		dao.updateState("123", true);
	}
	
	@Test
	public void testFindAll(){
		UserDao dao = new UserDao();
		System.out.println(dao.getAllUser());
	}
	
}
