package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtil {

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSource ds;
	static{
		try{
			Properties prop = new Properties();
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			prop.load(in);
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DataSource getDs() {
		return ds;
	}
	
	//����������ط�������
	
	public static void startTransaction() {

		try {
			Connection conn = tl.get();  //��ȡ�߳��ϰ󶨵�����
			if (conn == null) {           //������δ�������򴴽���һ�����󶨵��߳���
				conn = ds.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commitTransaction(){
		try {
			Connection conn = tl.get();
			if(conn != null){
				conn.commit();
			}
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public static void rollback(){
		try {
			Connection conn = tl.get();
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	
	public static void release(){
		try {
			Connection conn = tl.get();
			if(conn != null){
				conn.close();
				tl.remove(); //����߳��ϰ󶨵�����
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
