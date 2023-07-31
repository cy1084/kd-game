<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 등록</title>
</head>
<body>
<h2>유저 등록</h2>

<form method="POST" action="/user-info/update">
		<input type="text" name="uiId" ><br>
		<input type="text" name="uiName"><br>
		<input type="text" name="uiPwd"><br>
		<textarea name="uiDesc"></textarea>
		<br> <input type="date" name="uiBirth"
			value="${userInfo.uiBirth }"><br>
		<button>등록</button>
		<button type="reset">취소</button>

	</form>



</body>
</html>