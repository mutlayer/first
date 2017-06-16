<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
 <jsp:include page="/WEB-INF/jsp/forex/menu_header.jsp" flush="false"/> 
	<h2>사용자리스트</h2>
	<table class="board_list">
		<colgroup>
			<col width="10%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="15%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">계좌번호</th>
				<th scope="col">성명</th>
				<th scope="col">총잔고</th>
				<th scope="col">보너스금액</th>
				<th scope="col">미완료보너스금액</th>
				<th scope="col">출금가능액</th>
				<th scope="col">변경일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row">
						<tr>
							<td >
								<a href="#this" name="user_id">${row.USER_ID }</a>
								<input type="hidden" id="user_id" value="${row.USER_ID }">
							</td>
							<td>${row.ACCOUNT_NUMBER }</td>
							<td>${row.USER_NAME }</td>
							<td>${row.TOT_AMOUNT }</td>
							<td>${row.BONUS }</td>
							<td>${row.INCOMP_BONUS }</td>
							<td>${row.POSSIBLE_AMOUNT }</td>
							<td>${row.UPDATEDATE }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<br/>
	
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
	
		$(document).ready(function(){
			/*
			$("#write").on("click", function(e){ //글쓰기 버튼
				e.preventDefault();
				fn_openBoardWrite();
			});	
			*/
			$("a[name='user_id']").on("click", function(e){ //제목 
				popupOpen($(this));
				//fn_openBoardDetail($(this));
			});
		});
		
		function popupOpen(obj){
			
			var param1 = obj.parent().find("#user_id").val()
			var popUrl = "/first/forex/userDetail.do?USER_ID="+param1;	//팝업창에 출력될 페이지 URL
			var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
				window.open(popUrl,"popview",popOption);
			/*
			frmpop.action=popUrl;
			frmpop.target="popview";
			frmpop.arg1.value=param1;
			frmpop.submit();
			*/
		}
		
		function fn_openBoardWrite(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardWrite.do' />");
			comSubmit.submit();
		}
		
		function fn_openBoardDetail(obj){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardDetail.do' />");
			comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
			comSubmit.submit();
		}
	</script>	
	<form name="frmpop" method="POST">
		<input type="hidden" name="arg1">
	</form>
	
</body>
</html>