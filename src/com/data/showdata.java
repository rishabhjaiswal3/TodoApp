package com.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/showdata")
public class showdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/TodoApp")
	private DataSource dataSource;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step 1: set up a writer
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
			
			// step 2: get a connection to the database
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
			try{
				
				myConn = dataSource.getConnection();

				// Step 3: create a SQL Query
					String sql = "select * from Info";
					myStmt = myConn.createStatement();
					
				// step 4: execute Query
					myRs=myStmt.executeQuery(sql);
					
				//  Step 5: process the SQL statement
					while(myRs.next()){
						String email = myRs.getString("email");
						String name = myRs.getString("name");
						out.println(email+" "+name);
					}
			}
			catch(Exception e) 
			{
				out.println("please check the connection");
			}
	}
}
