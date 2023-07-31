<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 수정</title>
</head>
<body>
	<h2>유저 수정</h2>
	<form method="POST" action="/user-info/update">
	<input type="hidden" name="uiNum" value="${userInfo.uiNum }">
		<input type="text" name="uiId" value="${userInfo.uiId }"><br>
		<input type="text" name="uiName" value="${userInfo.uiName }"><br>
		<input type="text" name="uiPwd"><br>
		<textarea name="uiDesc">${userInfo.uiDesc }</textarea>
		<br> <input type="date" name="uiBirth"
			value="${userInfo.uiBirth }"><br>
		<button>수정</button>
		<button type="reset">취소</button>

	</form>



</body>
</html>