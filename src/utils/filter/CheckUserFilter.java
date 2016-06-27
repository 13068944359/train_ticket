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
		
		if(path.contains("admin")){ //如果请求admin相关的服务，则要判断是否为管理员登录
			if(request.getSession().getAttribute("admin") == null){
				//并不是管理员登录
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				
				return;
			}
			chain.doFilter(request, response);
			return;
		}
		
		if(request.getSession().getAttribute("session_user") == null ){ //用户尚未登录
			
			if(path.contains("index.jsp") || path.contains("message.jsp")){  //用户访问的是公共页面，放行
				chain.doFilter(request, response);
			}else if(path.contains("RegistUIServlet") || path.contains("CheckPictureServlet") || path.contains("LoginUIServlet")
					|| path.contains("LoginServlet") || path.contains("RegistServlet") || path.contains("ActiveServlet")){
				//用户访问账户相关的页面，放行
				chain.doFilter(request, response);
			}else{
				//用户访问的是需要先登录才能看得到的页面，不放行，发送重定向请求
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
