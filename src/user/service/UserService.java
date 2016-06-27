package user.service;

import java.util.List;

import user.dao.UserDao;
import user.domain.User;

public class UserService {

	private UserDao dao = new UserDao();
	//注册功能
	public void regeist(User form) throws UserException{
		
		User user = dao.findByName(form.getUsername());//检查用户名
		if(user != null) throw new UserException("用户名已被注册！");
		
		user = dao.findByEmail(form.getEmail());//检查邮箱
		if(user != null) throw new UserException("邮箱已被注册！");
		
		dao.add(form);//插入用户到数据库
	}
	
	//激活功能
	public void active(String code) throws UserException{
		//使用code查询数据库
		User user = dao.findByCode(code);
		if(user == null) throw new UserException("激活码无效！");//如果数据库中无该条目说明激活码错误
		if(user.isState()) throw new UserException("您已经处于激活状态，请不要重复激活");//检验用户账户的激活状态
		dao.updateState(user.getUid(), true);//修改用户激活状态
	}
	
	
	//拉黑功能
	public void deFriend(String uid){
		dao.deFriend(uid);
	}
	
	
	//登录功能
	public User login(User form) throws UserException{
		User user = dao.findByName(form.getUsername());//从数据库取出user
		if(user == null) throw new UserException("用户名不存在");//如果user为null,用户不存在，抛出异常
		if(!user.getPassword().equals(form.getPassword()))//输入密码与数据库存储密码不同，抛出异常
			throw new UserException("密码错误");
		if(!user.isState()) {
			if(user.getCode().equals("no")){
				throw new UserException("该账户已被拉入本系统黑名单，不允许登录操作");//禁止被拉黑的用户登录
			}
			throw new UserException("账户尚未激活，请先激活再登录");//用户尚未激活，抛出异常
		}
			
		return user;
	}
	
	//获得所有用户信息
	public List<User> getUserList(){
		return dao.getAllUser();
	}
	
	//获得指定用户名信息
	public User getUserByName(String name){
		return dao.findByName(name);
	}
}
