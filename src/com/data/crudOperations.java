package com.data;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class crudOperations
 */
@WebServlet("/crudOperations")
public class crudOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/TodoApp")
	private DataSource dataSource;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
		List<Client>clients = ClientDbUtil.getClientInfo();
		
		
		
	}

}
