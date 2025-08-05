<%@page import="utils.MyFunctions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>게시판 목록</title>
   <link rel="stylesheet" href="./commons/style.css">
</head>
<body>
  	<h2>JPA Board</h2>   	
   <div class="board">
	    <table>
	        <thead>
	            <tr>
	                <th>No</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회수</th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${rows}" var="row" varStatus="vs">
	            <tr>
	                <td>${row.idx}</td>
	                <td class="bo_title"><a href="view.do?idx=${row.idx}">${row.title}</a></td>
	                <td>${row.name}</td>
	                <td>${MyFunctions.stringCut(row.postdate)}</td>
	                <td>${row.hits}</td>
	            </tr>
	        </c:forEach>   
	        </tbody>
	    </table>
   </div>
   <div class="form-actions">
   	<button type="button" onclick="location.href='write.do';">작성</button>
   </div>
</body>
</html>

