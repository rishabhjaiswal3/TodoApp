package com.TodoApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	
	String url = "jdbc:mysql://localhost:3306/TodoApp";
	String username = "root";
	String password = "Rishabh@123";
	public void AddClient(Client client) throws Exception
	{
		ResultSet myRs = null;
		Connection myConn = null;
		PreparedStatement myStmt=null;
		
		try {	
			  	Class.forName("com.mysql.jdbc.Driver");
			  
			  	myConn = DriverManager.getConnection(url,username,password);
			  
			  	String sql = "insert into account (name,email,password) values (?,?,?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				myStmt.setString(1, client.getName());
				myStmt.setString(2,client.getEmail());
				myStmt.setString(3, client.getPassword());
				myStmt.executeUpdate();
				
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}

	public boolean verify(Client client) {
		ResultSet myRs = null;
		Connection myConn = null;
		PreparedStatement myStmt1=null;
		try{
				Class.forName("com.mysql.jdbc.Driver");
				myConn = DriverManager.getConnection(url,username,password);
				String sql1 = "select * from account where email = ?";
				myStmt1 = myConn.prepareStatement(sql1);
				myStmt1.setString(1,client.getEmail());
				myRs = myStmt1.executeQuery();
				if(myRs.next())
				{
					return false;
				}
				return true;
		}
		catch(Exception e){
			System.out.println("exception in db");
		}
		finally{
			close(myConn,myStmt1,myRs);
		}
		return false;
	}

	private void close(Connection myConn, PreparedStatement myStmt,
			ResultSet myRs) {
		
		if(myConn != null)
			myConn = null;
		
		if(myStmt != null)
			myStmt = null;
		
		if(myRs != null)
			myRs = null;
		
	}

	
	public void CreateNewDatabase(String email) {
			
			String DRIVER = "com.mysql.jdbc.Driver";  
			String URL = "jdbc:mysql://localhost/";
			String user= "root";
			String password ="Rishabh@123"; 
			Connection myConn = null;
			Statement myStmt = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			myConn = DriverManager.getConnection(URL,user,password);
			
			String table =email.replace('@','_').split(".com")[0];
			
			String sql = "CREATE DATABASE "+ table;
			
			myStmt = myConn.createStatement();
		
			myStmt.executeUpdate(sql);
			
			System.out.println("database Created");
		}
		catch(Exception e){
			System.out.println("fas gaya beta in database creation");
		}
		
	}

	public void CreateNewTable(String email) {
		
		
		String DRIVER = "com.mysql.jdbc.Driver";  
		String URL = "jdbc:mysql://localhost/"+email.replace('@','_').split(".com")[0];
		String user= "root";
		String password ="Rishabh@123"; 

		Connection myConn = null;
		Statement myStmt = null;
		try{
				Class.forName("com.mysql.jdbc.Driver");
				
				myConn = DriverManager.getConnection(URL,user,password);
				
				
				String sql = "create table Task( id int AUTO_INCREMENT,task varchar(255),primary key(id))";
				
				myStmt = myConn.createStatement();
			
				myStmt.executeUpdate(sql);
				
				System.out.println("table Created in database");
		}
		catch(Exception e){
			System.out.println("Error in table Creation");
		}
	}

}
