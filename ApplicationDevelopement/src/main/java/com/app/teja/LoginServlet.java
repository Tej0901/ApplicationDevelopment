package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Cookie cookies[]=request.getCookies();
		if(cookies==null)
		{
			request.getRequestDispatcher("login.html").include(request, response);
		}
		else {
			request.getRequestDispatcher("Link.html").include(request, response);
			out.println("\nYou are already Logged-In...<br>");
			out.println("<ul><li>You may Proceed With the Admin Operations you want to Perform by clicking the Admit Form");
			out.println("<li>You can logOut From the Aplication by clicking logOut button");
			out.println("<li>You can Go to Home Page ,if u want by clicking HomePage button"+"<hr>");
		}
		out.close();
	}
}