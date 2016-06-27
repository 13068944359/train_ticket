package utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.domain.User;

public class CheckUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String path = request.getServletPath();
		
		if(path.contains("admin")){ //�������admin��صķ�����Ҫ�ж��Ƿ�Ϊ����Ա��¼
			if(request.getSession().getAttribute("admin") == null){
				//�����ǹ���Ա��¼
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				
				return;
			}
			chain.doFilter(request, response);
			return;
		}
		
		if(request.getSession().getAttribute("session_user") == null ){ //�û���δ��¼
			
			if(path.contains("index.jsp") || path.contains("message.jsp")){  //�û����ʵ��ǹ���ҳ�棬����
				chain.doFilter(request, response);
			}else if(path.contains("RegistUIServlet") || path.contains("CheckPictureServlet") || path.contains("LoginUIServlet")
					|| path.contains("LoginServlet") || path.contains("RegistServlet") || path.contains("ActiveServlet")){
				//�û������˻���ص�ҳ�棬����
				chain.doFilter(request, response);
			}else{
				//�û����ʵ�����Ҫ�ȵ�¼���ܿ��õ���ҳ�棬�����У������ض�������
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			return;
		}
		
		if(request.getSession().getAttribute("session_user") != null ){
			
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
