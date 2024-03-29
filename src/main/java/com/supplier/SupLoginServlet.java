package com.supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supplier.Supplier;
import com.supplier.Supplier;


@WebServlet("/SupLoginServlet")
public class SupLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isTrue;
		
		isTrue = SupplierDBUtil.validate(username, password);
		
		if (isTrue == true) {
			HttpSession session=request.getSession();
			session.setAttribute("supuser", username);
			List<Supplier> supDetails = SupplierDBUtil.getSupplier(username);
			request.setAttribute("supDetails", supDetails);
			request.setAttribute("username", username);
			RequestDispatcher dis = request.getRequestDispatcher("Home.jsp");
			dis.forward(request, response);
		} 
		else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Your username or password is incorrect');");
			out.println("location='suplogin.jsp'");
			out.println("</script>");
		}
	}

}
