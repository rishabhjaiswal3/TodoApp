<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="CSS/todo.css" />
	</head>
	<body>
			<form action = "ClientAccountServlet" method="post">
			<input type="hidden" name="command" value="LogIn" />
				<h1>LogIn</h1>
				<table>
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
				<div class="Error">
					<p><c:out value="${valid}" /></p>
				</div>
			</form>
	</body>
</html>