package com.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ClientDbUtil {
	
	private  DataSource dataSource;
	
	public ClientDbUtil(DataSource thedataSource)
	{
		dataSource=thedataSource;
	}
	
	public static List<Client> getClientInfo() throws Exception
	{
		List<Client> clients = new ArrayList<>();
		
		// make connection variable
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			// step 1: get connection
			myConn = dataSource.getConnection();
			String sql = "select * from Info";
			
			// step 2: create statement 
			myStmt = myConn.createStatement();
			
			//step 3: execute query
			myRs = myStmt.executeQuery(sql);
			
			// process the data from myRs
			while(myRs.next()){
				
				String name = myRs.getString("name");
				String email = myRs.getString("email");
				
				Client tempClient = new Client(name,email);
				
				clients.add(tempClient);
			}
			return clients;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		
		try{
			if(myConn != null){
				myConn = null;
			}
			if(myStmt != null){
				myStmt = null;
			}
			if(myRs != null){
				myRs = null;
			}
		}
		catch(Exception e){
			throw e;
		}
	}
}
