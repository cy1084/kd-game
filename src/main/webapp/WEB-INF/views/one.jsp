<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	번호:
	<span id="num">${param.num }</span>
	<br> 이름:
	<span id="name"></span>
	<br> 나이:
	<span id="age"></span>
	<br> 주소:
	<span id="address"></span>
	<br>
	<button onclick='goPage("/views/list/update?num=${param.num}")'>수정</button onclick='deleteObj()'>
	<button>삭제</button>
	<!-- delete는 이미 있는 예약어이기 때문에 사용 못함 -->
	<script>
	function deleteObj(){
		const xhr=new XMLHttpRequest();
		xhr.open('POST','list/delete?num=${param.num}');
		xhr.onreadystatechange=function(){
			if(xhr.readyState===4){
				if(xhr.status===200){
					if(xhr.responseText==='1'){
						alert('삭제 완료');
					}else('이미 삭제된 내용입니다.');
				}
				location.href-'/views/list';
			}
		}
	}
	xhr.send();
	}
		window.addEventListener('load', function() {
			const xhr=new XMLHttpRequest(){
				xhr.open('GET','/list/one?num='+${param.num});
				xhr.onreadystatechange=function(){
					if(xhr.readyState===4){
						if(xhr.status===200){
							const obj=JSON.parse(xhr.responseText);
							
							for(const key in obj){
								document.querySelector(('#'+key).innerText=obj[key]);
							}
						}
					}
				}
			}

		})
	</script>
</body>
</html>