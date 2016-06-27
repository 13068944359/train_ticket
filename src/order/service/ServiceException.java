package order.service;

public class ServiceException extends Exception {

	private String message;
	
	public ServiceException(){
		super();
	}
	
	
	public ServiceException(String str){
		super(str);
		this.message = str;
		
	}
	
	
	
	public String getMessage(){
		return message;
	}
}

