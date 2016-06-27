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

		
		//�ȱ����ݰ�װ��bean��
		UserBean bean = RegistBeanUtil.request2Bean(request, UserBean.class);
		
		//����һ��map�������û�������Ϣ�����쳣����map���ڷ�װ�쳣��Ϣ��keyΪ�ֶ����ƣ�ֵΪ������Ϣ
		Map<String,String> errors = new HashMap<String, String>();
		
		//У����֤ͼƬ�Ƿ���ȷ
		if(!request.getSession().getAttribute("check").toString().equals(bean.getCheckcode())){
			errors.put("check", "��֤�ַ�����");
		}
		
		//���û��������Ϣ���кϷ���У��
		String username = bean.getUsername();
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "�û�������Ϊ��");
		}else if(username.length() < 3 || username.length() > 10){
			errors.put("username", "�û������ȱ�����3~10֮�䣡");
		}else if(username.equals("admin")){
			errors.put("username", "�û����Ѵ���");
		}
		
		String password = bean.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "���벻��Ϊ��");
		}else if(password.length() < 3||username.length() > 10){
			errors.put("password", "���볤�ȱ�����3~10֮�䣡");
		}
		
		String password2 = bean.getPassword2();
		if(password!=null && !password.equals(password2)){
			errors.put("password2", "�����������벻һ��");
		}
		
		String email = bean.getEmail();
		if(email == null || email.trim().isEmpty()){
			errors.put("email", "���䲻��Ϊ��");
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			errors.put("email", "�����ʽ����");
		}
		
		//�ж�map���Ƿ�����쳣��Ϣ
		if(errors.size() > 0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
			return;
		}
		User user = new User();
		user =  bean.copyBean(user); //����bean�ڵ���Ϣ
		
		//��ȫbean��Ϣ
		user.setUid(RegistBeanUtil.getUid().substring(0, 8));  //ȡ�������uid�ĵ�1��8λ��Ϊ�˻�id
		user.setCode(RegistBeanUtil.getUid());                 //ȡ���ɵ�uid��Ϊ�û��ļ�����
		user.setPassword(UserUtils.md5(user.getPassword()));
		
		//����service��regist����
		try{
			service.regeist(user);
		}catch(UserException e){
			request.setAttribute("message", e.getMessage());
			request.setAttribute("form", bean);
			request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
			return;
		}
		
		/*
		 * ���ʼ�
		 * ׼�������ļ���
		 */
		// ��ȡ�����ļ�����
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//��ȡ����������
		String uname = props.getProperty("uname");//��ȡ�û���
		String pwd = props.getProperty("pwd");//��ȡ����
		String from = props.getProperty("from");//��ȡ������
		String to = user.getEmail();//��ȡ�ռ���
		String subject = props.getProperty("subject");//��ȡ����
		String content = props.getProperty("content");//��ȡ�ʼ�����
		content = MessageFormat.format(content, user.getCode());//�滻{0}
		
		Session mailSession = MailUtils.createSession(host, uname, pwd);//�õ�session
		Mail mail = new Mail(from, to, subject, content);//�����ʼ�����
		try {
			MailUtils.send(mailSession, mail);//���ʼ���
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		request.getSession().invalidate();
		request.getSession().setAttribute("msg", "��ϲע��ɹ����뵽���伤�������ʺ�");
		response.sendRedirect(request.getContextPath()+"/message.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
