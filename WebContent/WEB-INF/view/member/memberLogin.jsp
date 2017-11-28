<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./memberLogin.member" method="post">
<p><input type="text" name="id"></p>
<p><input type="text" name="pw"></p>
<p>T<input type="radio" name="job" value="T" class="job" checked="checked">S<input type="radio" name="job" value="S" class="job"></p>
<button>Login</button>
</form>
</body>
</html>