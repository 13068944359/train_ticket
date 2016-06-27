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
	 * ������ͼƬ
	 */
	
	public static final int width = 120;
	public static final int height = 35;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		//����ͼƬ����ɫ
		setBackGround(g);
		
		//���ñ߿�
		setBorder(g);
		
		//��������
		drawRandomLine(g);
		
		//д�����
		String check = drawRandomNum((Graphics2D) g);
		
		//httpͷ
		response.setContentType("image/jpeg");
		
		//��ͷ�����������Ҫ����(������֤ͼƬ)
		response.setHeader("expries", "-1");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
	
		//ͼ��д�������
		ImageIO.write(image,  "jpg",  response.getOutputStream());
		
		//����֤ͼƬ�ڵ����ݱ��浽session����
		request.getSession().setAttribute("check", check);
		
	}


	//���ֱ�������    [\u4e00-u9fa5]       ���ú�����������Ҫ�ٶ�
	private String  drawRandomNum(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD , 20));
		
		String base = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97";
		String check="";
		
		int x = 5;
		
		for(int i = 0; i<4 ; i++){
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			check = check + ch;
			int dre = new Random().nextInt()%30;  //-30��30֮�������
			g.rotate(dre*Math.PI/180, x, 20);//������ת����
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
