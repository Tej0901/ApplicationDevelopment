package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/api/StudentRecords/deleteDetail")
public class DeleteRecord extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		try 
		{
		//Database Connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);
		
		//query execution
		String deletingId = request.getParameter("id");
		String query = "DELETE FROM StudentDetails WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, deletingId);
		int i = stmt.executeUpdate();
		
		//sending response to client
		String res = " "+i+" Rows Updated!!";
		response.getWriter().write(res);
		stmt.close();
		con.close();
		
		}
		catch (ClassNotFoundException e)
		{
			out.println("Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		catch (SQLException e)
		{
	    	out.println("Invalid Operation!!! Error Occurred!!!  -->  "+e);
	    }
		catch (Exception e)
		{
			out.println("Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		finally 
		{
			out.close();
		}
	}
		
	       

}



