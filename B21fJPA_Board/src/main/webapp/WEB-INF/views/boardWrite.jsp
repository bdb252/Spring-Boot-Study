<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>게시판 쓰기</title>
   <link rel="stylesheet" href="./commons/style.css">
</head>
<body>
   <h2>JPA Board</h2>   
   <div class="board write-view">
   <form name="frm" method="post" action="./writeProc.do">   
       <table>
       <tbody>
           <tr>
               <th>작성자</th>
               <td style="text-align:left;"><input type="text" name="name" value="" style="width:200px;"></td>
           </tr>
           <tr>
               <th>제목</th>
               <td><input type="text" name="title" placeholder="제목을 입력하세요"></td>
           </tr>
           <tr>
               <th>내용</th>
               <td><textarea name="contents" rows="10" placeholder="내용을 입력하세요"></textarea></td>
           </tr>
           <!-- <tr>
               <th>비밀번호</th>
               <td style="text-align:left;"><input type="password" name="pass" value="" style="width:100px;"></td>
           </tr> -->
       </tbody>
       </table>           
   </div>
   <div class="form-actions">
       <button type="submit">저장</button>
       <button type="reset">취소</button>
       <button type="button" onclick="location.href='list.do';">목록</button>   
   </div>	
   </form>
</body>
</html>

