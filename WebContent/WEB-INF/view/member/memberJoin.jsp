<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".job").click(function(){
	var job=document.frm.job.value;
	if(job=="T"){
		$("#A").attr("id","S");
		$("#T").attr("id","A");
	}else{
		$("#A").attr("id","T");
		$("#S").attr("id","A");
	}
	});
});
</script>
<style type="text/css">
#A{
display: inline-block;
}
#S{
display: none;
}
#T{
display: none;
}
</style>
</head>
<body>

<form action="./memberJoin.member" method="post" name="frm">
<p>ID : <input type="text" name="id"></p>
<p>PW : <input type="password" name="pw"></p>
<p>NAME : <input type="text" name="name"></p>
<p>EMAIL : <input type="email" name="email"></p>
<p>PHONE : <input type="text" name="phone"></p>
<p>AGE : <input type="number" name="age"></p>
<p>JOB : T<input type="radio" name="job" value="T" class="job">S<input type="radio" name="job" value="S" class="job"></p>
<div id="S">
<p>GRADE : <input type="number" name="grade"></p>
<p>ADDR : <input type="text" name="addr"></p>
<p>TID : <input type="text" name="tid"></p>
</div>
<div id="T">
<p>SUBJECT : <input type="text" name="subject"></p>
<p>BIRTH : <input type="date" name="birth"></p>
<p>SALARY : <input type="number" name="salary"></p>
</div>
<button>SUBMIT</button>
</form>
</body>
</html>