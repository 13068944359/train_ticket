package user.utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class RegistBeanUtil {

	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		
		try {
			//������װ���ݵ�BEAN
			T bean = beanClass.newInstance();
			
			//����������bean��
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
