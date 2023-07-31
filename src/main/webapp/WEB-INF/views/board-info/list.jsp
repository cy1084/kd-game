<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>

</head>
<body>
	<h3>게시물 리스트</h3>
	<form action="/board-info/list" method="GET">
		<select name="searchType">
			<!-- ex) searchType이란 키 값으로 biTitle이 감 -->
			<option value="1">제목</option>
			<option value="2">작성자</option>
			<option value="3">내용</option>
			<option value="4">제목+내용</option>
			<option value="5">작성자+내용</option>
			<option value="6">제목+작성자</option>
			<option value="7">제목+내용+작성자</option>
		</select> <input type="text" name="searchStr" placeholder="검색어">
		<button>검색</button>
	</form>

	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ biList}" var="boardInfo">
					<tr>
						<td>${boardInfo.biNum }</td>
						<td><a href="/board-info/view?biNum=${boardInfo.biNum }">${boardInfo.biTitle }</a></td>
						<td>${boardInfo.uiName }</td>
						<td>${boardInfo.credat }</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="4" align="right"><button type="button"
							class="btn btn-primary" onclick="goPage('/board-info/insert')">게시물
							등록</button>
				</tr>

			</tbody>
		</table>
	</div>
	<script>
		function goPage(url) {
			location.href = url;
		}
		//alert(1);
		//function (클래스의 메소드)
	</script>

</body>
</html>