package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginVerificationServlet extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uName = request.getParameter("userName");
		String uPassword = request.getParameter("userPassword");

		if(uPassword.equals("teja0901") && uName.equalsIgnoreCase("Admin"))
		{
			request.getRequestDispatcher("Link.html").include(request, response);
			Cookie cookie=new Cookie("uName", uName);
			response.addCookie(cookie);
			out.println("<hr>\n"
					+ "Succesfully Logged-In...<br>\n"
					+ "Welcome Teja vardhan<br>");
			out.println("<ul><li>You may Proceed With the Admin Operations you want to Perform by clicking the Admit Form");
			out.println("<li>You can logOut From the Aplication by clicking logOut button");
			out.println("<li>You can Go to Home Page ,if u want by clicking HomePage button"+"<hr>");
		}
		else
		{
			out.println("<hr>Your Name or Password Is Incorrect, Please Try Again with correct Details !!!!!!<hr>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
}