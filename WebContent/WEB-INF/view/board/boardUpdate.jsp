<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Insert title here</title>
</head>
<body>
<h1>${board}Update</h1>
<form action="./${board}Update.${board}" method="post">
<input type="hidden" name="num" value="${a.num}">
<p>TITLE : <input type="text" name="title" value="${a.title}"></p>
<p>WRITER : <input type="text" name="writer" readonly="readonly" value="${a.writer}"></p>
<p>CONTENTS : <textarea name="contents">${a.contents}</textarea></p>
<button>Write</button>
</form>
</body>
</html>