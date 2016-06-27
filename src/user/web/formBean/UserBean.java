package user.web.formBean;

import user.domain.User;

public class UserBean {

	private String username;
	private String password;
	private String password2;
	private String email;
	private String checkcode;
	
	
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password
				+ ", password2=" + password2 + ", email=" + email + "]";
	}
	
	
	public User copyBean(User user){
		
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setEmail(this.getEmail());		
		return user;
	}
	
	
}
