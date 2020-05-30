<%@ page import="java.util.*,com.TodoApp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	
	<head>
	
		<link type="text/css" rel="stylesheet" href="CSS/todo.css" />
	
	</head>
	<body>
		
		<%
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");    //HTTP 1.1
			
			response.setHeader("pragma","no-cache"); // http 1.0
			
			response.setHeader("Expires","0");
			
			
			if(session.getAttribute("EMAIL") == null) 
			{
				response.sendRedirect("LogIn.jsp");	
			}
		%>
		
		<h1>Tasks</h1>
		
		<form action = "ClientAccountServlet" method="post" >
				<input type="hidden" name="command" value="LogOut" />
				<div class="button button1">
					<input type="submit" name="submit" value="LOGOUT" style="float: right; ">
				</div>
		</form>
		
		
		
		<div class="add-student-button">
			<form action = "/NewTodoApp/NewTask.jsp">
				<button type="submit">Add new Task</button>
			</form>
		</div>
		
		
		<form>
			<table border="1">
				<c:forEach var="sudotask" items="${Tasks}">
					 <tr>
						<td>${sudotask.task}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<br/>
		
		
	</body>
</html>
