<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>
function deletePost(idx){
    var confirmed = confirm("정말로 삭제하겠습니까?"); 
    if (confirmed) {
        var form = document.writeFrm;      
        form.method = "post";  
        form.action = "delete.do";
        form.submit();  
    }
}
</script>
<body>
	
	<h2>게시판 읽기(Mybatis)</h2>	
	<form name="writeFrm">
		<input type="hid-den" name="idx" value="${myDiaryDTO.diary_idx }" />
	</form>
	<table border="1" width="90%">
	    <colgroup>
	        <col width="18%"/> <col width="18%"/> <col width="18%"/>
	        <col width="18%"/> <col width="28"/>
	    </colgroup>	
	    <!-- 게시글 정보 -->
	    <tr>
	        <td>작성일</td> <td colspan="4">${ myDiaryDTO.postdate }</td>
	    </tr>
	    <tr>
	        <td>이미지</td>
	        <td colspan="4" height="100">
	        	<img src="/uploads/${myDiaryDTO.sfile }" />        	
	        </td>
	    </tr>
	    <tr>
	        <td>내용</td>
	        <td colspan="4" height="100">
	        	${ myDiaryDTO.description }	        	
	        </td>
	    </tr>
	    <tr>
	        <td>온도</td>
	        <td>습도</td>
	        <td>일조량</td>
	        <td>키</td>
	        <td>열매개수</td>
	    </tr>
	    <tr>
	        <td >
	        	${ myDiaryDTO.temperature }	        	
	        </td>
	        <td >
	        	${ myDiaryDTO.humidity }	
        	</td>        	
	        <td >
	        	${ myDiaryDTO.sunlight }	        	
	        </td>
	        <td >
	        	${ myDiaryDTO.height }	        	
	        </td>
	        <td >
	        	${ myDiaryDTO.fruit }	        	
	        </td>
	    </tr>
	    <!-- 하단 메뉴(버튼) -->
	    <tr>
	        <td colspan="5" align="center">
	            <button type="button" onclick="location.href='./edit.do?diary_idx=${ param.diary_idx }';">
	                수정하기
	            </button>
	            <button type="button" onclick="deletePost(${ param.diary_idx });">
	                삭제하기
	            </button>
	            <button type="button" onclick="location.href='./list.do';">
	                목록 바로가기
	            </button>
	        </td>
	    </tr>
	</table>
</body>

</html>