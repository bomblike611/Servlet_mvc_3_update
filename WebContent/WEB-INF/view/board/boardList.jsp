<%@page import="com.iu.util.Pageing"%>
<%@page import="com.iu.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
		var kind='${make.kind}';//오류가 날수도 있으니 문자열로 표시
		$(".i").each(function(){
			if($(this).val()==kind){
				$(this).attr("selected",true);
			}
		});
	
	$(".list").click(function(){
		var cur = $(this).attr("title");
		document.frm.curPage.value=cur;
		document.frm.submit();
	});
});
</script>
</head>
<body>
	<h1>${board}</h1>
	<div>
		<form action="./${board}List.${board}" name="frm">
		<input type="hidden" name="curPage">
		<select name="kind" value="${make.kind}">
		<option value="title" class="i">title</option>
		<option value="contents" class="i">contents</option>
		<option value="writer" class="i">writer</option>
		</select>
		<input type="text" name="search" value="${make.search}">
		<button>Search</button>
		</form>
	</div>
	<table>
		<tr>
			<td>Num</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Contents</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>
		<c:forEach items="${list}" var="s">
		<tr>
			<td>${s.num}</td>
			<td>
			<c:catch var="null">
			<c:forEach begin="1" end="${s.depth}">--</c:forEach>
			</c:catch>
			<a href="./${board}View.${board}?num=${s.num}">${s.title}</a></td>
			<td>${s.writer }</td>
			<td>${s.contents }</td>
			<td>${s.hit }</td>
		<td></td>
		</tr>
		</c:forEach>
	</table>
	<c:if test="${page.curBlock gt 1}">
	<input type="button" value="[이전]" title="${page.startNum-1}" class="list">
	</c:if>
	<c:forEach begin="${page.startNum}" end="${page.lastNum}" var="i">
	<input type="button" value="${i}" title="${i}" class="list">
	</c:forEach>
	<c:if test="${page.curBlock lt page.totalBlock}">
	<input type="button" value="[다음]" title="${page.lastNum+1}" class="list">
	</c:if>
	<a href="./${board}Write.${board}">Write</a>
</body>
</html>