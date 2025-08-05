<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
	
	function validateForm(fm) {
		if(fm.name.value=='') {
			alert("작성자를 입력하세요.");
			return false;
		}
		if(fm.title.value=='') {
			alert("제목을 입력하세요.");
			return false;
		}
		if(fm.content.value=='') {
			alert("내용을 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	
	</script>


	<h2>게시판 작성(Mybatis)</h2>
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="./write.do" onsubmit="return validateForm(this);">
	<table border="1" width="90%">
	    <!-- 
	    
	     -->
	    <tr>
	        <td>작성자</td>
	        <td>
	            <input type="text" name="name" style="width:150px;" />
	        </td>
	    </tr>
	    <tr>
	        <td>온도</td>
	        <td>
	            <input type="text" name="temperature" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>습도</td>
	        <td>
	            <input type="text" name="humidity" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>일조량</td>
	        <td>
	            <input type="text" name="sunlight" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>키</td>
	        <td>
	            <input type="text" name="height" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>열매 개수</td>
	        <td>
	            <input type="text" name="fruit" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>설명</td>
	        <td>
	            <textarea name="description" style="width:90%;height:100px;"></textarea>
	        </td>
	    </tr>
	    <tr>
	        <td>이미지</td>
	        <td>
	        	<input type="file" name="ofile" />
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" align="center">
	            <button type="submit">작성 완료</button>
	            <button type="reset">RESET</button>
	            <button type="button" onclick="location.href='./list.do';">
	                목록 바로가기
	            </button>
	        </td>
	    </tr>
	</table>    
	</form>
</body>

</html>