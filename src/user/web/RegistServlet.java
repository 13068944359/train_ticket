package user.web;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import user.domain.User;
import user.service.UserException;
import user.service.UserService;
import user.utils.RegistBeanUtil;
import user.utils.UserUtils;
import user.web.formBean.UserBean;


public class RegistServlet extends HttpServlet {

	private UserService service = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//先表单数据包装到bean中
		UserBean bean = RegistBeanUtil.request2Bean(request, UserBean.class);
		
		//创建一个map，倘若用户输入信息发生异常，则map用于封装异常信息，key为字段名称，值为错误信息
		Map<String,String> errors = new HashMap<String, String>();
		
		//校对验证图片是否正确
		if(!request.getSession().getAttribute("check").toString().equals(bean.getCheckcode())){
			errors.put("check", "验证字符错误");
		}
		
		//对用户输入的信息进行合法性校验
		String username = bean.getUsername();
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length() < 3 || username.length() > 10){
			errors.put("username", "用户名长度必须在3~10之间！");
		}else if(username.equals("admin")){
			errors.put("username", "用户名已存在");
		}
		
		String password = bean.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}else if(password.length() < 3||username.length() > 10){
			errors.put("password", "密码长度必须在3~10之间！");
		}
		
		String password2 = bean.getPassword2();
		if(password!=null && !password.equals(password2)){
			errors.put("password2", "两次输入密码不一致");
		}
		
		String email = bean.getEmail();
		if(email == null || email.trim().isEmpty()){
			errors.put("email", "邮箱不能为空");
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			errors.put("email", "邮箱格式不对");
		}
		
		//判断map中是否存在异常信息
		if(errors.size() > 0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
			return;
		}
		User user = new User();
		user =  bean.copyBean(user); //拷贝bean内的信息
		
		//补全bean信息
		user.setUid(RegistBeanUtil.getUid().substring(0, 8));  //取生成随机uid的第1到8位作为账户id
		user.setCode(RegistBeanUtil.getUid());                 //取生成的uid作为用户的激活码
		user.setPassword(UserUtils.md5(user.getPassword()));
		
		//调用service层regist方法
		try{
			service.regeist(user);
		}catch(UserException e){
			request.setAttribute("message", e.getMessage());
			request.setAttribute("form", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
			return;
		}
		
		/*
		 * 发邮件
		 * 准备配置文件！
		 */
		// 获取配置文件内容
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//获取服务器主机
		String uname = props.getProperty("uname");//获取用户名
		String pwd = props.getProperty("pwd");//获取密码
		String from = props.getProperty("from");//获取发件人
		String to = user.getEmail();//获取收件人
		String subject = props.getProperty("subject");//获取主题
		String content = props.getProperty("content");//获取邮件内容
		content = MessageFormat.format(content, user.getCode());//替换{0}
		
		Session mailSession = MailUtils.createSession(host, uname, pwd);//得到session
		Mail mail = new Mail(from, to, subject, content);//创建邮件对象
		try {
			MailUtils.send(mailSession, mail);//发邮件！
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		request.getSession().invalidate();
		request.getSession().setAttribute("msg", "恭喜注册成功，请到邮箱激活您的帐号");
		response.sendRedirect(request.getContextPath()+"/message.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
