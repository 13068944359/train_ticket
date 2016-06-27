package utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public MyRequest(HttpServletRequest request){
		super(request);
		this.request= request;
	}
	@Override
	public String getParameter(String name) {
		
		String value = this.request.getParameter(name);
		if(value == null){
			return null;
		}
		
		if(!request.getMethod().equalsIgnoreCase("get")){
			return value;
		}
		
		try{
			return value = new String(value.getBytes("iso8859-1"),request.getCharacterEncoding()==null?"UTF-8":request.getCharacterEncoding());
		}catch(UnsupportedEncodingException e){
			throw new RuntimeException(e);
		}
		
	}
	
	
}
