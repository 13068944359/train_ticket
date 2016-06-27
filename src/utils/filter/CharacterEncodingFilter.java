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

import utils.MyRequest;


//全局编码统一
public class CharacterEncodingFilter implements Filter {

	private FilterConfig config;
	private String defaultCharset = "UTF-8";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String charset = this.config.getInitParameter("charset");
		if(charset == null){
			charset = defaultCharset;
		}
		HttpServletRequest reque = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		MyRequest req = new MyRequest(reque);
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		resp.setContentType("text/html);charset=" +charset);
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}

}
