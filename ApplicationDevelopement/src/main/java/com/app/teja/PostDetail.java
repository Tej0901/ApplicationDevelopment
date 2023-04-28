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

//@WebServlet("/api/StudentRecords/postDetail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		Student student = gson.fromJson(jsonString, Student.class);
		
		//duplicate id validation while inserting
		String idCheckQuery = "SELECT id FROM StudentDetails";
		ResultSet rSetTwo = stmt.executeQuery(idCheckQuery);
		boolean flag=true;
		String applId = student.getID();
		rSetTwo.beforeFirst();
		while(rSetTwo.next())
		{
			if(applId.equals(rSetTwo.getString(1)))
			{
				flag=false;
			}
		}
		
		//actual insertion method ,if id not exist will insert, otherwise Error(409)
		if(!flag) 
		{
		      response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
		      response.setContentType("application/json");
		      response.getWriter().write("{\"message\": \"ID already exists\"}");
		}
		else 
		{
			//query execution
			String query ="INSERT INTO StudentDetails VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtTwo = con.prepareStatement(query);
			stmtTwo.setString(1, student.getID());
			stmtTwo.setString(2, student.getFirstName());
			stmtTwo.setString(3, student.getLastName());
			stmtTwo.setString(4, student.getEmail());
			stmtTwo.setString(5, student.getPhoneNo());
			stmtTwo.setString(6, student.getAge());
			stmtTwo.setString(7, student.getGender());
			stmtTwo.setString(8, student.getAddress());
			stmtTwo.setString(9, student.getState());
			stmtTwo.setString(10, student.getProgram());
			stmtTwo.setString(11, student.getDept());
			int i = stmtTwo.executeUpdate();
			stmtTwo.close();
			
			//sending data to client , if API call is success.
			out.println(i+" Rows Updated!!");
			out.println(jsonString);
		    response.setStatus(HttpServletResponse.SC_OK); // 200 OK
		    response.setContentType("application/json");
		    response.getWriter().write("{\"message\": \"Operation successful\"}");
		}
		stmt.close();
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
}