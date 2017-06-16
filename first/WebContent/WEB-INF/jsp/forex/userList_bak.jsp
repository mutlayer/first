<!DOCTYPE html>

<html lang="en">

	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<title>user list</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="/first/assets/css/main.css" />
	</head>
	
	<body>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="/first/assets/js/jquery.min.js"></script>
		<script src="/first/assets/js/skel.min.js"></script>
		<script src="/first/assets/js/util.js"></script>
		<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
		<script src="/first/assets/js/main.js"></script>
		
		<script type="text/javascript">
		$(function(){
			$('.col').click(function(){
				var name = $(this).attr('name');
				alert(name);
			});	
		})
		

		function init() {
			var titles = document.getElementsByTagName("th"); 
			for ( var i = 0, len = titles.length; i < len; i++ ) {    
			    titles[i].addEventListener("click", function() { 
			        alert( this.innerHTML ); 
			    }, false); 
			} 
			
		}//end of function
		
		</script>
		<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
		
							<!-- Header -->
								<header id="header" style="padding: 1.5em 0 0.5em 0;">
									<a href="/first/index.html" class="logo"><strong>사용자</strong> by HTML5 UP</a>
									<ul class="icons">
										<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
									</ul>
								</header>
							<form method="post" action="userList.do" method="post">
								<div class="row uniform" style="display:inline;" >
									<div class="3u 12u$(xsmall)" style="display:inline;">
										이름 : <input type="text" name="user_name" id="user_name" value="" placeholder="Name" />
									</div>
									<div class="3u 12u$(xsmall)" style="display:inline;">
										아이디 : <input type="text" name="user_id" id="user_id" value="" placeholder="ID" />
									</div>
									<div class="2u 12u$(xsmall)" >
										<input type="submit" class="button special" value="search"/>
									</div>
									<!-- 	<a href="#" class="button special">검색</a> -->
									
								</div>
							<header id="header" style="padding: 0.5em 0 0.5em 0;">
							</header>
							</form>
							<table  class="table">
								<colgroup>
							        <col  width="5%"/>
							        <col  width="5%"/>
							        <col  width="10%"/>
							        <col  width="5%"/>
							        <col  width="15%"/>
							        <col  width="10%"/>
							        <col  width="10%"/>
							        <col  width="15%"/>
							        <col  width="15%"/>
							        <col  width="10%"/>
							    </colgroup>
							    
							    <thead>
							        <tr>
							            <th scope="col" class="col" name="user_id">ID</th>
							            <th scope="col" class="col" name="user_name">이름</th>
							            <th scope="col" class="col" name="user_phone_number">전화번호</th>
							            <th scope="col" class="col" name="recommend_id">추천인</th>
							            <th scope="col" class="col" name="insertdate">가입일</th>
							            <th scope="col" class="col" name="user_ip">IP</th>
							            <th scope="col" class="col" name="user_bank_nm">은행</th>
							            <th scope="col" class="col" name="user_bank_number">계좌번호</th>
							            <th scope="col" class="col" name="upatedate">수정일</th>
							            <th scope="col" class="col" name="update_id">수정자</th>
							        </tr>
							    </thead>
							    
							    <tbody>
							        <c:choose>
							            <c:when test="${fn:length(list) > 0}">
							                <c:forEach items="${list }" var="row">
							                    <tr>
							                        <td>${row.USER_ID }</td>
							                        <td>${row.USER_NAME }</td>
							                        <td>${row.USER_PHONE_NUMBER }</td>
							                        <td>${row.RECOMMEND_ID }</td>
							                        <td>${row.INSERTDATE }</td>
							                        <td>${row.USER_IP }</td>
							                        <td>${row.USER_BANK_NM }</td>
							                        <td>${row.USER_BANK_NUMBER }</td>
							                        <td>${row.UPDATEDATE }</td>
							                        <td>${row.UPDATE_ID }</td>
							                    </tr>
							                </c:forEach>
							            </c:when>
							            <c:otherwise>
							                <tr>
							                    <td colspan="10">조회된 결과가 없습니다.</td>
							                </tr>
							            </c:otherwise>
							        </c:choose>
							         
							    </tbody>
						    </table>
				    	</div>
				    </div>
				    
				    <jsp:include page="/sidebar.html" flush="true"></jsp:include>
				</div>
				    
	</body>
</html>