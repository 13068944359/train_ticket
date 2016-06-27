package cart.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.domain.Cart;

public class CartDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Cart cart = (Cart) request.getSession().getAttribute("cart");  //从session中得到用户的购物车
		
		String tid = request.getParameter("tid");
		cart.delete(tid);
		
		request.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
