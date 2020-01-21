<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In</title>
</head>
<body>
	<form action = "j_security_check" method = "post">
	<table>
	<tr><td>User Name: <input type ="text" name = "j_username" /></td></tr>
	<tr><td>Password : <input type = "password" name = "j_password" /></td></tr>
	<tr><th><input type = "submit" value = "Log In" />
	</table>
	</form>

</body>
</html>