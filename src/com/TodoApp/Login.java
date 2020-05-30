package com.TodoApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login {

	private Connection MyConnection(String db) throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/" + db;
		
		System.out.println(url);
		
		String username = "root";
		String password = "Rishabh@123";
		Connection myConn=null;
		Class.forName("com.mysql.jdbc.Driver");
		myConn = DriverManager.getConnection(url,username,password);
		return myConn;
		
	}
	
	public boolean verify(String email, String pass) {
			
		try{
			
				Connection myConn = MyConnection("TodoApp");
				
				String sql = "select * from account where email = ? and password = ?";
				
				PreparedStatement myStmt = myConn.prepareStatement(sql);
				
				myStmt.setString(1,email);
				myStmt.setString(2,pass);
				
				ResultSet myRs=myStmt.executeQuery();
				
				if(myRs.next())
					return true;
				else
					return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	public List<Task> showtask(String email){
		
		List<Task> tasks = new ArrayList<>();	
		try{
			Connection myConn = MyConnection( email.replace('@','_').split(".com")[0]);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = null;
			String sql="select * from Task";
			
			myRs=myStmt.executeQuery(sql);
	
			
			while(myRs.next())
			{
				String task=myRs.getString("task");
				Task sudotask = new Task(task);
				tasks.add(sudotask);
			}
			return tasks;
			
		}
		catch(Exception e)
		{
			System.out.println("data is not catched");
		}
		return tasks;
	}

	public void InsertIntoTask(String task,String email) {
		
		try{
			Connection myConn = MyConnection(email.replace('@','_').split(".com")[0]);
			
			String sql="insert into Task (task) values  (?)";
			PreparedStatement myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, task);
			
			myStmt.executeUpdate();
		}
		catch(Exception e){
			
			System.out.println("Error in InsertIntoTask method");
		}
		
	}
}
