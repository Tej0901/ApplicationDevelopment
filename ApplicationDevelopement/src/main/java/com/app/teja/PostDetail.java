package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//@WebServlet("/api/StudentRecords/postDetail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);

		String jsonString = request.getReader().lines().collect(Collectors.joining());
		Gson gson = new Gson();
		Student student = gson.fromJson(jsonString, Student.class);
		
		String query ="INSERT INTO StudentDetails VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt2 = con.prepareStatement(query);
		stmt2.setString(1, student.getID());
		stmt2.setString(2, student.getFirstName());
		stmt2.setString(3, student.getLastName());
		stmt2.setString(4, student.getEmail());
		stmt2.setString(5, student.getPhoneNo());
		stmt2.setString(6, student.getAge());
		stmt2.setString(7, student.getGender());
		stmt2.setString(8, student.getAddress());
		stmt2.setString(9, student.getState());
		stmt2.setString(10, student.getProgram());
		stmt2.setString(11, student.getDept());
		int i = stmt2.executeUpdate();
		out.println(i+" Rows Updated!!");
		stmt2.close();
		con.close();
		}
		catch (ClassNotFoundException e)
		{
			out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		catch (SQLException e)
		{
        	out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
        }
		catch (Exception e)
		{
			out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		finally 
		{
			out.close();
		}
	}
//	public void doPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		PrintWriter out = response.getWriter();
//		try 
//		{
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
//		String userName = "root";
//		String userPassword = "teja0901";
//		Connection con = DriverManager.getConnection(url, userName, userPassword);
//
//		String jsonString = request.getReader().lines().collect(Collectors.joining());
//		Gson gson = new Gson();
//		Student student1 = gson.fromJson(jsonString, Student.class);
//		
//		String query ="INSERT INTO StudentDetails VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//		PreparedStatement stmt2 = con.prepareStatement(query);
//		stmt2.setString(1, student1.getID());
//		stmt2.setString(2, student1.getFirstName());
//		stmt2.setString(3, student1.getLastName());
//		stmt2.setString(4, student1.getEmail());
//		stmt2.setString(5, student1.getPhoneNo());
//		stmt2.setString(6, student1.getAge());
//		stmt2.setString(7, student1.getGender());
//		stmt2.setString(8, student1.getAddress());
//		stmt2.setString(9, student1.getState());
//		stmt2.setString(10, student1.getProgram());
//		stmt2.setString(11, student1.getDept());
//		int i = stmt2.executeUpdate();
//		out.println("row updated: "+i);
//		stmt2.close();
//		con.close();
//		}
//		catch (ClassNotFoundException e)
//		{
//			out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
//		}
//		catch (SQLException e)
//		{
//        	out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
//        }
//		catch (Exception e)
//		{
//			out.println("<br>Invalid Operation!!! Error Occurred!!!  -->  "+e);
//		}
//		finally 
//		{
//			out.close();
//		}
//	}
}
