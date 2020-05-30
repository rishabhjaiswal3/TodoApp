package com.TodoApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/ClientAccountServlet")
public class ClientAccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Login lgin = new Login();
	private DbConnection db = new DbConnection();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String thecommand = request.getParameter("command");
		PrintWriter out = response.getWriter();
		switch(thecommand)
		{
			case "SignUp":
				try{
					CreateNewClient(request,response);
				}
				catch(Exception e){
					out.println("exception in creating a user account in doPost method");
				}
				break;
			case "LogIn":
				try {
					LogIn(request,response);
				} catch (Exception e) {
			
					e.printStackTrace();
				}
				break;
			case "LogOut":
				try{
					Logout(request,response);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			case "showTasks":
				try{
					showTask(request,response);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "NewTask":
				try{
					CreateTask(request,response);
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}
	}
	
	
	private void CreateTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("EMAIL");
		String task=request.getParameter("newtask");
		lgin.InsertIntoTask(task,email);
		
		showTask(request,response);
		response.sendRedirect("tasksfile.jsp");
		
	}

	private void showTask(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		Login ln = new Login();
		HttpSession session = request.getSession();
		String email=(String)session.getAttribute("EMAIL");
		List<Task>tasks = ln.showtask(email);
		
		System.out.println(tasks.toString());
		request.setAttribute("Tasks",tasks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("tasksfile.jsp");
		
		dispatcher.forward(request, response);
		
		response.sendRedirect("tasksfile.jsp");
		
	}

	private void Logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		session.removeAttribute("EMAIL");
		session.invalidate();
		response.sendRedirect("LogIn.jsp");
	}

	
	
	private void LogIn(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
        if(lgin.verify(email,password)){
        	
        	
        	HttpSession session = request.getSession();
        	session.setAttribute("EMAIL", email);
        	
        	showTask(request,response);
        	
		}
		else
		{
			request.setAttribute("valid","Please Enter Valid Email or Password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");
			dispatcher.forward(request,response);
			response.sendRedirect("LogIn.jsp");
		}	
	}

	
	private void newDatabaseandTable(HttpServletRequest request,
			HttpServletResponse response) {	
		
		String email = request.getParameter("email");
		
		db.CreateNewDatabase(email);
		
		db.CreateNewTable(email);
	}

	private void CreateNewClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

			PrintWriter out = response.getWriter();
			DbConnection db = new DbConnection();
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Client sudoclient = new Client(name,email,password);
			
			if( db.verify(sudoclient) )
			{
				db.AddClient(sudoclient);
				
				// CREATE NEW DATABASE and TABLE 
				newDatabaseandTable(request,response);

				response.sendRedirect("/NewTodoApp/LogIn.jsp");
			}
			else
			{
				System.out.println("already present");
				request.setAttribute("verify","This Email is already Regestered please");
				request.setAttribute("url","LogIn");
				RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
				dispatcher.forward(request, response);
				response.sendRedirect("SignUp.jsp");		
			}
	}
}
