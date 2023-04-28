package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


//@WebServlet("/api/StudentRecords/putDetail")
public class PutDetail extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
		String requiredId = request.getParameter("id");
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		Gson gson = new Gson();
		Student student = gson.fromJson(jsonString, Student.class);
		
		//query execution
		String query = "UPDATE StudentDetails SET firstName=?,"
										+ "lastName=?,email=?,"
										+ "phoneNo=?,age=?,"
										+ "gender=?,address=?,"
										+ "state=?,program=?,"
										+ "dept=? WHERE id=?";
		
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, student.getFirstName());
		stmt.setString(2, student.getLastName());
		stmt.setString(3, student.getEmail());
		stmt.setString(4, student.getPhoneNo());
		stmt.setString(5, student.getAge());
		stmt.setString(6, student.getGender());
		stmt.setString(7, student.getAddress());
		stmt.setString(8, student.getState());
		stmt.setString(9, student.getProgram());
		stmt.setString(10, student.getDept());
		stmt.setString(11, requiredId);
		int i = stmt.executeUpdate();
		
		//sending Response back to client JS
		out.println(i+" Rows Updated!!");
		out.println(jsonString);
		stmt.close();
		con.close();
		
		}
		catch (ClassNotFoundException e)
		{
			out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		catch (SQLException e)
		{
        	out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
        }
		catch (Exception e)
		{
			out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		finally 
		{
			out.close();
		}
	}
}
