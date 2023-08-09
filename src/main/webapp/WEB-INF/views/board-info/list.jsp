<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp" />

</head>
<body>
	<h3>게시물 리스트</h3>
	<div class="container">
		<form action="/board-info/list" method="GET">
			<select name="searchType" placeholder="검색어" id="searchStr">
				<!--   ex) searchType이란 키 값으로 biTitle이 감-->
				<option value="1">제목</option>
				<option value="2">작성자</option>
				<option value="3">내용</option>
				<option value="4">제목+내용</option>
				<option value="5">작성자+내용</option>
				<option value="6">제목+작성자</option>
				<option value="7">제목+내용+작성자</option>
			</select> <input type="text" name="searchStr" placeholder="검색어">
			<button onclick="loadFunc">검색</button>
		</form>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody id="tBody">
			</tbody>
				<tr>
					<td colspan="4" align="right"><button type="button"
							class="btn btn-primary" onclick="goPage('/board-info/insert')">게시물
							등록</button>
				</tr>
		</table>
	</div>

	<script>	
		function goPage(url) {
			location.href = url;
		}
		//alert(1);
		//function (클래스의 메소드)

		const loadFunc=function(){
			const xhr=new XMLHttpRequest();
			const searchStr=document.querySelector('#searchStr');
			const searchType=document.querySelector('#searchType');
			alert(searchStr);
			alert(searchType);//메시지 잘 나오는지 확인
			
			let url='/json/list?'; //? 있어도 되고 없어도 됨
			if(searchStr.value !=''){
				url+='searchType='+searchType.value+'&searchStr='+searchStr.value;
				//alert(url);
				//return;
				//alert(searchStr.value);
				//alert(searchType.value);
			}
			
			xhr.open('GET','url'); //화면은 가만히 있는 상태에서 json 혼자 갔다옴
			xhr.onreadystatechange=function(){
				if(xhr.readyState===4){ 
			        if(xhr.status===200){
			        	const list=JSON.parse(xhr.responseText); 
			        	let html='';
			        	for(const board of list){
			                
			                html+='<tr>';
			                html+='<td>'+board.biNum+'</td>';
			                html+='<td><a href="/json/one?biNum='+board.biNum+'">'+board.biTitle+'</a></td>';
			                html+='<td>'+board.uiName+'</td>';
			                html+='<td>'+board.credat+'</td>';
			                html+='</tr>';
			                //jsp에서는 js의 el 태그 못씀
			              }
			              document.querySelector('#tBody').innerHTML=html;
			        }
			     }
			}
			 xhr.send();
		}
		window.addEventListener('load', function() {
			alert('do something');
		}); //load event를 window에 추가
	</script>

</body>
</html>