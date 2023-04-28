package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//@WebServlet("/api/StudentRecords/getParticularDetail")
public class GetParticularDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		Gson gson = new Gson();
		Student student = new Student();
		
		//query execution
		String requiredId = request.getParameter("id");
		String query = "SELECT * FROM StudentDetails WHERE id="+requiredId;
		ResultSet rSet = stmt.executeQuery(query);
		rSet.next();
		student.setID(rSet.getString(1));
		student.setFirstName(rSet.getString(2));
		student.setLastName(rSet.getString(3));
		student.setEmail(rSet.getString(4));
		student.setPhoneNo(rSet.getString(5));
		student.setAge(rSet.getString(6));
		student.setGender(rSet.getString(7));
		student.setAddress(rSet.getString(8));
		student.setState(rSet.getString(9));
		student.setProgram(rSet.getString(10));
		student.setDept(rSet.getString(11));
		
		//sending data to Client via JSon Object
		String jsonString = gson.toJson(student);
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
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
