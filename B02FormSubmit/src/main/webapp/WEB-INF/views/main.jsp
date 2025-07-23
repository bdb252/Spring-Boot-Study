<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringBoot</title> 
</head>
<body>
	<h2>스프링 부트 프로젝트</h2>
	<ul>
		<li><a href="./index.html">index.html</a></li>
		<li><a href="./sub/sub.html">sub.html</a></li>
	</ul>
	<!-- 요청명에 대한 링크 설정 -->
	<h2>view 매핑</h2>
	<ul>
		<li><a href="./index.do">index.do</a></li>
		<li><a href="./sub.do">sub.do</a></li>
	</ul>
	
	<h2>폼값 전송</h2>
	<ul>
		<li><a href="form1.do?name=손오공&age=11">내장객체</a></li>
		<li><a href="form2.do?name=저팔계&age=22">어노테이션</a></li>
		<li><a href="form3.do?name=사오정&age=33">커맨드객체</a></li>
		<li><a href="form4/삼장법사/44">경로변수</a></li>
	</ul>
	
	<h2>퀴즈</h2>
	<ul>
		<li><a href="memberRegist.do">회원가입</a></li>
		<li><a href="./memberLogin.do">로그인</a></li>
	</ul>
</body>
</html>