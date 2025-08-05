<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>게시판 상세보기</title>
   <link rel="stylesheet" href="./commons/style.css">
</head>
<body>
  	<h2>JPA Board</h2>
   <div class="board detail-view">
        <table>
           <tbody>
               <tr>
                   <th>No</th>
                   <td>${row.idx}</td>
                   <th>작성자</th>
                   <td>${row.name}</td>
               </tr>
               <tr>
                   <th>날짜</th>
                   <td>${row.postdate}</td>
                   <th>조회수</th>
                   <td>${row.hits}</td>
               </tr>
               <tr>
                   <th>제목</th>
                   <td colspan="3">${row.title}</td>
               </tr>
               <tr>
                   <th>내용</th>
                   <td colspan="3" id="contents">
                       ${row.contents}
                   </td>
               </tr>
           </tbody>
       </table>
   </div>
   <div class="form-actions">
       <button type="button" onclick="location.href='edit.do?idx=${row.idx}';">수정</button>
       <button type="button" onclick="location.href='delete.do?idx=${row.idx}';">삭제</button>
       <button type="button" onclick="location.href='list.do';">목록</button>
   </div>
</body>
</html>