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
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String requiredId = request.getParameter("id");
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		Gson gson = new Gson();
		Student student = gson.fromJson(jsonString, Student.class);
		String query = "UPDATE StudentDetails SET firstName=?,lastName=?,email=?,phoneNo=?,age=?,gender=?,address=?,state=?,program=?,dept=? WHERE id=?";
		PreparedStatement stmt2 = con.prepareStatement(query);
		stmt2.setString(1, student.getFirstName());
		stmt2.setString(2, student.getLastName());
		stmt2.setString(3, student.getEmail());
		stmt2.setString(4, student.getPhoneNo());
		stmt2.setString(5, student.getAge());
		stmt2.setString(6, student.getGender());
		stmt2.setString(7, student.getAddress());
		stmt2.setString(8, student.getState());
		stmt2.setString(9, student.getProgram());
		stmt2.setString(10, student.getDept());
		stmt2.setString(11, requiredId);
		
		int i = stmt2.executeUpdate();
		out.print(i+" Rows Updated!!");
		stmt2.close();
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
