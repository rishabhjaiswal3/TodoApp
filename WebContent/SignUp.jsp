<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

	<head>
		<link type="text/css" rel="stylesheet" href="CSS/todo.css" />
	</head>
	<body>
			
		<form action="ClientAccountServlet" method="post">
			
			<input type="hidden" name="command" value="SignUp" />
			
			<h1>SignUp</h1>
			
			<table >
			
				<tr>
					<td>Name</td>
				</tr>
				
				<tr>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<td>Email</td>
				</tr>
				
				<tr>
					<td><input type="text" name="email"></td>
				</tr>
				
				<tr>
					<td>Password</td>
				</tr>
				
				<tr>
					<td><input type="password" name="password"></td>
				</tr>
				
				<tr>
					<td><input type="submit" name="submit"></td>
				</tr>
				
			</table>
			<br/>
		</form>
		<div class="Error">
				<c:out value="${verify}"/>
				
				<a href="LogIn.jsp"> <c:out value="${url}"/> </a>
		</div>
		
	</body>
	
</html>