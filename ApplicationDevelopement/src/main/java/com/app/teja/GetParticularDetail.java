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
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String requiredId = request.getParameter("id");
		String query = "SELECT * FROM StudentDetails WHERE id="+requiredId;
		ResultSet rSet = stmt.executeQuery(query);
		Gson gson1 = new Gson();
		Student student2 = new Student();
		rSet.next();
		student2.setID(rSet.getString(1));
		student2.setFirstName(rSet.getString(2));
		student2.setLastName(rSet.getString(3));
		student2.setEmail(rSet.getString(4));
		student2.setPhoneNo(rSet.getString(5));
		student2.setAge(rSet.getString(6));
		student2.setGender(rSet.getString(7));
		student2.setAddress(rSet.getString(8));
		student2.setState(rSet.getString(9));
		student2.setProgram(rSet.getString(10));
		student2.setDept(rSet.getString(11));
		
		//sending data to Client via JSon Object
		String json = gson1.toJson(student2);
        response.setContentType("application/json");
        response.getWriter().write(json);
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
