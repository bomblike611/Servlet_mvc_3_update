<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${board}Write</h1>
<form action="./${board }Write.${board}" method="post">
<p>TITLE : <input type="text" name="title"></p>
<p>WRITER : <input type="text" name="writer"></p>
<p>CONTENTS : <textarea name="contents"></textarea></p>
<button>Write</button>
</form>
</body>
</html>