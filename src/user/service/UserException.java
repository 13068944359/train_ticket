package user.service;

public class UserException extends Exception{

	private String message;
	
	
	public UserException(String str ){
		super(str);
		this.message = str;
	}
	
	
	public UserException() {
		super();
	}


	public String getMessage() {
		return message;
	}
	
	
}
