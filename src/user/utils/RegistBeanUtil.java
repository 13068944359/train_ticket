package user.utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class RegistBeanUtil {

	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		
		try {
			//创建封装数据的BEAN
			T bean = beanClass.newInstance();
			
			//把数据整如bean中
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String)e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getUid(){
		return UUID.randomUUID().toString();
	}
}
