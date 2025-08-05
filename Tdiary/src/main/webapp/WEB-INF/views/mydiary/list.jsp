<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis게시판</title>
</head>
<body>
	<h2>게시판 목록(Mybatis)</h2>

    <!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">아이디</th>
            <th width="15%">설명</th>
            <th width="10%">온도</th>
            <th width="10%">습도</th>
            <th width="10%">일조량</th>
            <th width="15%">작성일</th>
        </tr>
<c:choose>
    <c:when test="${ empty lists }"> 
        <tr>
            <td colspan="5" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when> 
    <c:otherwise> 
        <c:forEach items="${ lists }" var="row" varStatus="loop">    
        <tr align="center">
            <td> 
            <!-- 게시물의갯수, 페이지번호, 페이지사이즈를 통해 가상번호를 계산해서
            출력한다. -->
            ${ maps.totalCount - 
                (((maps.pageNum-1) * maps.pageSize)	+ loop.index)}
            </td>
            <td align="left"> 
                <a href="./view.do?diary_idx=${ row.diary_idx }">${ row.member_idx}</a> 
            </td> 
            <td>${ row.description }</td> 
            <td>${ row.temperature }</td> 
            <td>${ row.humidity}</td> 
            <td>${ row.sunlight}</td> 
            <td>${ row.postdate }</td> 
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>
    
    <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%">
        <tr align="center">
            <td>
                ${ pagingImg }
            </td>
            <td width="100"><button type="button"
                onclick="location.href='./write.do';">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>
