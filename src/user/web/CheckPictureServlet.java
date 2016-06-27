package user.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPictureServlet extends HttpServlet {

	/**
	 * 输出随机图片
	 */
	
	public static final int width = 120;
	public static final int height = 35;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		//设置图片背景色
		setBackGround(g);
		
		//设置边框
		setBorder(g);
		
		//画干扰线
		drawRandomLine(g);
		
		//写随机数
		String check = drawRandomNum((Graphics2D) g);
		
		//http头
		response.setContentType("image/jpeg");
		
		//发头控制浏览器不要缓存(缓存认证图片)
		response.setHeader("expries", "-1");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
	
		//图形写给浏览器
		ImageIO.write(image,  "jpg",  response.getOutputStream());
		
		//把验证图片内的内容保存到session域中
		request.getSession().setAttribute("check", check);
		
	}


	//汉字编码区间    [\u4e00-u9fa5]       常用汉字区间则需要百度
	private String  drawRandomNum(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD , 20));
		
		String base = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97";
		String check="";
		
		int x = 5;
		
		for(int i = 0; i<4 ; i++){
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			check = check + ch;
			int dre = new Random().nextInt()%30;  //-30到30之间的数字
			g.rotate(dre*Math.PI/180, x, 20);//设置旋转弧度
			g.drawString(ch, x, 20);
			g.rotate(-dre*Math.PI/180, x, 20);
			x+=30;
		}
		return check;
		
	}


	private void drawRandomLine(Graphics g) {
		g.setColor(Color.WHITE);
		for(int i = 0; i<5; i++){
			int x1 = new Random().nextInt(width);
			int y1 = new Random().nextInt(height);
			
			int x2 = new Random().nextInt(width);
			int y2 = new Random().nextInt(height);
			
			g.drawLine(x1, y1, x2, y2);
		}
	}


	private void setBorder(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, width-2, height-2);
	}


	private void setBackGround(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

}
