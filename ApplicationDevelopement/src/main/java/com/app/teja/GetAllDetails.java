package com.app.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


//@WebServlet("/api/StudentRecords/getAllDetails")
public class GetAllDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
      
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		try 
		{
		//Database Connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/StudentManagementSystem?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		Gson gson = new Gson();
		Student student[] = new Student[100];
		String json[] = new String[100];
		JsonArray jsonArray = new JsonArray();
		response.setContentType("application/json");
		
		//query execution
		String query = "SELECT * FROM StudentDetails";
		ResultSet rSet = stmt.executeQuery(query);
		int studentIndex = -1;
		
		//Storing data in Student POJO
		rSet.beforeFirst();
		while(rSet.next()) 
		{
		studentIndex++;
		student[studentIndex] = new Student();
		student[studentIndex].setID(rSet.getString(1));
		student[studentIndex].setFirstName(rSet.getString(2));
		student[studentIndex].setLastName(rSet.getString(3));
		student[studentIndex].setEmail(rSet.getString(4));
		student[studentIndex].setPhoneNo(rSet.getString(5));
		student[studentIndex].setAge(rSet.getString(6));
		student[studentIndex].setGender(rSet.getString(7));
		student[studentIndex].setAddress(rSet.getString(8));
		student[studentIndex].setState(rSet.getString(9));
		student[studentIndex].setProgram(rSet.getString(10));
		student[studentIndex].setDept(rSet.getString(11));
		json[studentIndex] = gson.toJson(student[studentIndex]);
		jsonArray.add(json[studentIndex]);
		}
		
		//sending data to Client via JSon Array of jsonStrings
		out.print(jsonArray);
		
		rSet.close();
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
