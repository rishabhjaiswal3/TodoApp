
<html>
	<head>
	
		<link type="text/css" rel="stylesheet" href="CSS/todo.css" />
	
	</head>
	<body>
		<h1>Add New Task</h1>
		
		<form action="ClientAccountServlet" method="post">
			<input type="hidden" name="command" value="NewTask">
		
			<table>
				<tr><td><input type="text" name="newtask"></td></tr>
				<tr><td><input type="submit" name="submit"></td></tr>
			</table>
				
		</form>
		
	</body>
</html>