<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DTO</title>
</head>
<body>
	<h2>롬복 설치 후 DTO사용하기</h2>
	<div>
		이름 : ${ dto.name } <br>
		아이디 : ${ dto.id } <br>
		비밀번호 : ${ dto.pass } <br>
		수료일 : ${ dto.regidate } <br>
	</div>
</body>
</html>