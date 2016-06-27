package user.test;

import org.junit.Test;

import user.domain.User;
import user.service.UserException;
import user.service.UserService;

public class UserServiceTest {

	@Test
	public void testRegist(){
		User user = new User();
		user.setUid("1232");
		user.setUsername("sdfssdf");
		user.setPassword("asdfasfd");
		user.setEmail("sadfassdfasdf");
		user.setCode("11111111");
		user.setState(false);
		
		UserService us = new UserService();
		try {
			us.regeist(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
