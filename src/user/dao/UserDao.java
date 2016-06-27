package user.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import user.domain.User;
import utils.JdbcUtil;

public class UserDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDs());
	
	public void add(User user){
		try{
			String sql = "insert into tb_user values(?,?,?,?,?,?)";
			Object[] param = {user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getCode(), user.isState()};
			qr.update(sql, param);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	public User findByName(String name){
		try{
			String sql = "select * from tb_user where username=?";
			return (User) qr.query(sql, name, new BeanHandler(User.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	public User findByEmail(String email){
		try{
			String sql = "select * from tb_user where email=?";
			return (User) qr.query(sql, email, new BeanHandler(User.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	public User findByCode(String code){
		try{
			String sql = "select * from tb_user where code=?";
			return (User) qr.query(sql, code, new BeanHandler(User.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	public void updateState(String uid ,boolean state){
		try{
			String sql = "update tb_user set state=? where uid=?";
			Object[] param = {state,uid};
			qr.update(sql, param);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}

	public List<User> getAllUser() {
		try{
			String sql = "select * from tb_user";
			return (List<User>) qr.query(sql, new BeanListHandler(User.class));
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
	}

	public void deFriend(String uid) {
		try{
			String sql = "select * from tb_user where uid='" + uid + "'";
			User user = (User) qr.query(sql, new BeanHandler(User.class));
			if(user.isState()){        //拉黑账户
				sql = "update tb_user set state='0' where uid='" + uid + "'";
				qr.update(sql);
				sql = "update tb_user set code='no' where uid='" + uid + "'";
				qr.update(sql);
			}else{                      //取消拉黑
				sql = "update tb_user set state='1' where uid='" + uid + "'";
				qr.update(sql);
				sql = "update tb_user set code='xx' where uid='" + uid + "'";
				qr.update(sql);
			}
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
		
	}
}
