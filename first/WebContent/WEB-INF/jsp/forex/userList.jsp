
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Integer"%>
<%
	String input_id = "";
	if(request.getAttribute("input_id")!=null){
		input_id = request.getAttribute("input_id").toString();
		
	}
	String input_name = "";
	if(request.getAttribute("input_name")!=null){
		input_name=request.getAttribute("input_name").toString();
		
	}
	String column = "";
	if(request.getAttribute("column")!=null){
		column = request.getAttribute("column").toString();
	}
	String sort = "";
	if(request.getAttribute("sort")!=null){
		sort=request.getAttribute("sort").toString();
	}
	
	String cur_page = "";
	System.out.println("---------");
	System.out.println(request.getAttribute("cur_page"));
	if(request.getAttribute("cur_page")!=null){
		cur_page=request.getAttribute("cur_page").toString();
	}else{
		cur_page="1";
	}
	
	List<Map<String,Object>> arr = new ArrayList();
	arr = (List<Map<String,Object>>)request.getAttribute("list");
	int tot_cnt = 0;
	if(arr.size()>0){
		tot_cnt = (Integer)arr.get(0).get("CNT");
	}
	System.out.println(tot_cnt);
	System.out.println("=========");
	
	int paging_cnt = 10;
	int paging_no = 0;
	int paging_nam = 0;
	
	if(tot_cnt/paging_cnt>0){
		paging_no = tot_cnt/paging_cnt;
		paging_nam = tot_cnt%paging_cnt;
		if(paging_nam>0){
			paging_no = paging_no+1;
		}
	}
	
	//String inner_paging = "<li><a href='#' class='button'>Next</a></li>";
	String inner_paging="";
	int prevpage = Integer.parseInt(cur_page)-1;
	int nextpage = Integer.parseInt(cur_page)+1;
	if(cur_page.equals("1")){
		inner_paging ="<li><span class='button disabled' >Prev</span></li>";
	}else{
		inner_paging ="<li><span class='button' onclick='searchpage("+prevpage+")'>Prev</span></li>";
	}
		
	for(int i=1;i<=paging_no;i++){
		inner_paging +="<span class='button' id='page_bt"+i+"' onclick='searchpage("+i+")'>"+i+"</span>";
		//inner_paging += "<li><a href='searchpage("+i+")' class='page active'>"+i+"</a></li>";
	}
	if(Integer.parseInt(cur_page)==paging_no){
		inner_paging +="<li><span class='button disabled'>Next</span></li>";
	}else{
		inner_paging +="<li><span class='button' onclick='searchpage("+nextpage+")'>Next</span></li>";
	}
	
%>

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
		
		$(function() {
			
		    <%if(paging_no==0&&paging_nam>0){%>
		    
		    	$(".pagination").append("<%=inner_paging%>");
		    
		    <%}else if(paging_no>0){%>
		    	$(".pagination").append("<%=inner_paging%>");
		    <%}%>
		    
		    $("#page_bt<%=cur_page%>").attr("class","button disabled");
		  });
		
		function search(){
			$("#sort").val("asc");
			$("#column").val("USER_NAME");
			$("#cur_page").val("1");
			f.submit();
		}
		
		function searchpage(page){
			//$("#sort").val("asc");
			//$("#column").val("USER_NAME");
			$("#cur_page").val(page);
			f.submit();
		}
		
		$(function(){
			$('.col').click(function(){
				$("#cur_page").val("1");
				var column = $("#column").attr('value');
				var name = $(this).attr('name');
				if(column!=name){
					$("#sort").val("asc");
				}else{
					if($("#sort").attr('value')=="asc"){
						$("#sort").val("desc");
					}else{
						$("#sort").val("asc");
					}
				}
				$("#column").val(name);
				
				f.submit();
				
			});	
		});
		
		$(document).on("click","tr.rows", function(e){
		    //alert(e.target.innerHTML);
		    //alert($(this).$('td[name="r_user_id"]').innerHTML);
		    //alert($(this).html());
		    //alert($(this).find($('td[name=r_user_id]')).html());
		    var popUrl = "user_detail_pop.do";
		    var title = "user detail";
		    var popOption = "width=800,height=600,resizable=yes,scrollbar=yes,status=no,location=no;";
		    
		    var user_id = ($(this).find($('td[name=r_user_id]')).html());
		    $("#pop_user_id").val(user_id);
		    
		    
		    var f_pop = document.f_pop;
		    f_pop.pop_user_id.value = user_id;
		    $("#pop_user_id").val(user_id);
		    
		    
		    window.open("",title,popOption);
		    f_pop.target=title;
		    f_pop.action = popUrl;
		    f_pop.method="post";
		    f_pop.submit();
		    
		    
		});
		
		
/*
		function search(){
			$.ajax({
				type : "POST",
				url : "userList.do",
				data : ({page:page}),
				success : function(data){
					alert("success");
				},
				error : alert("error")
				
			})
		}
	*/	
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
										<form name="f_pop" method="post" >
							<input type="hidden" name="pop_user_id" id="pop_user_id" value="asd">
							</form>
									</ul>
								</header>
							
							<form name="f" method="post" action="userList.do" >
							<input type="hidden" name="column" id="column" value="<%=column%>">
							<input type="hidden" name="sort" id="sort" value="<%=sort%>">
							
							<input type="hidden" name="paging_no" value="<%=paging_no %>">
							<input type="hidden" name="paging_nam" value="<%=paging_nam %>">
							<input type="hidden" name="tot_cnt" value="<%=tot_cnt %>">
							<input type="hidden" name="cur_page" id="cur_page" value="<%=cur_page %>">
							
								<div class="row uniform" style="display:inline;" >
									<span>이름 :</span>
									<div class="2u 12u$(xsmall)">
										<input type="text" name="user_name" id="user_name" value="<%=input_name %>" placeholder="Name" />
									</div>
									<span>ID :</span>
									<div class="2u 12u$(xsmall)">
										<input type="text" name="user_id" id="user_id" value="<%=input_id %>" placeholder="ID" />
									</div>
									<div class="2u 12u$(xsmall)" >
										<input type="button" class="button special" onclick="search()" value="search"/>
										<!-- <input type="submit" class="button special"  value="search"/> -->
									</div>
									<!-- 	<a href="#" class="button special">검색</a> -->
									
								</div>
							<header id="header" style="padding: 0.5em 0 0.5em 0;">
							</header>
							</form>
							<table  class="table" style="width:100%;overflow:auto">
								<colgroup>
							        <col  width="5%"/>
							        <col  width="8%"/>
							        <col  width="10%"/>
							        <col  width="5%"/>
							        <col  width="12%"/>
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
							            <th scope="col" class="col" name="updatedate">수정일</th>
							            <th scope="col" class="col" name="update_id">수정자</th>
							            
							        </tr>
							    </thead>
							    
							    <tbody>
							        <c:choose>
							            <c:when test="${fn:length(list) > 0}">
							                <c:forEach items="${list }" var="row">
							                    <tr class="rows">
							                        <td name="r_user_id">${row.USER_ID }</td>
							                        <td name="r_user_name">${row.USER_NAME }</td>
							                        <td name="r_user_phone_number">${row.USER_PHONE_NUMBER }</td>
							                        <td name="r_recommend_id">${row.RECOMMEND_ID }</td>
							                        <td name="r_insertdate">${row.INSERTDATE }</td>
							                        <td name="r_user_ip">${row.USER_IP }</td>
							                        <td name="r_user_bank_nm">${row.USER_BANK_NM }</td>
							                        <td name="r_user_bank_number">${row.USER_BANK_NUMBER }</td>
							                        <td name="r_updatedate">${row.UPDATEDATE }</td>
							                        <td name="r_update_id">${row.UPDATE_ID }</td>
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
						    
						    <center>
						    <div>
								<ul class="pagination">
								<!--
									<li><span class="button disabled">Prev</span></li>
									 
									<li><a href="#" class="page active">1</a></li>
									<li><a href="#" class="button">Next</a></li>
									 -->
								</ul>
						    </div>
						    </center>
				    	</div>
				    </div>
				    
				    <jsp:include page="/sidebar.html" flush="true"></jsp:include>
				</div>
				    
	</body>
</html>