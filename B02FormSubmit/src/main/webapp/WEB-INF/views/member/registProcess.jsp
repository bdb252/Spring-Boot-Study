<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입폼에서 전송된 값</h2>
	<ul>
		<li>아이디 : ${ authDTO.id }</li> 
		<li>비밀번호 : ${ authDTO.pass1 }</li>
		<li>이름 : ${ authDTO.name }</li>
		<li>성별 : ${ authDTO.sex }</li>
		<li>이메일 : ${ authDTO.email1 }@${ authDTO.email2 }</li>
		<li>이메일 수신여부 : ${ authDTO.mailing }</li>
		<li>우편번호 : ${ authDTO.zipcode }</li>
		<li>주소 : ${ authDTO.addr1 }</li>
		<li>핸드폰 : ${ authDTO.phone1 }-${ authDTO.phone2 }-${ authDTO.phone3 }</li>
		<li>SMS 수신 여부 : ${ authDTO.sms }</li>
		<li>관심분야 : ${ authDTO.etc_no1 }</li>
		<li>가입경로 : ${ authDTO.etc_no2 }</li>
	</ul>
</body>
</html>