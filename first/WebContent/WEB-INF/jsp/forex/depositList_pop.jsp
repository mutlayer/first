
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="java.math.BigDecimal"%>
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
	}else{
		column="order_time";
	}
	
	String sort = "";
	if(request.getAttribute("sort")!=null){
		sort=request.getAttribute("sort").toString();
	}else{
		sort="desc";
	}
	
	String cur_page = "";
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
	
	int paging_cnt = 20;
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
	
	String first_paging = "1";
	String end_paging = "10";
	
	if(Integer.parseInt(cur_page)<=10){
		first_paging = "1";
		end_paging = "10";
	}else if (Integer.parseInt(cur_page)>10){
		if(cur_page.charAt(cur_page.length()-1)!="0".charAt(0)){
			first_paging = cur_page.substring(0,1) +"1";
			
			BigDecimal tem1 = new BigDecimal(cur_page).divide(new BigDecimal("10"));
			BigDecimal tem2 = tem1.setScale(0,BigDecimal.ROUND_UP).multiply(new BigDecimal("10"));
			end_paging = tem2.toString();
		}else{
			first_paging = String.valueOf(Integer.parseInt(cur_page.substring(0,1))-1) +"1";
			BigDecimal tem1 = new BigDecimal(cur_page).divide(new BigDecimal("10"));
			end_paging = tem1.toString()+"0";
		}
		
	}
	
	if(cur_page.equals("1")){
		inner_paging ="<li><span class='button disabled' >First</span></li>";
		inner_paging +="<li><span class='button disabled' >Prev</span></li>";
	}else{
		inner_paging ="<li><span class='button' onclick='searchpage(1)'>First</span></li>";
		inner_paging +="<li><span class='button' onclick='searchpage("+prevpage+")'>Prev</span></li>";
	}
	
	
	if(Integer.parseInt(end_paging)>=paging_no){
		end_paging = String.valueOf(paging_no);
	}
	
	for(int i=Integer.parseInt(first_paging);i<=Integer.parseInt(end_paging);i++){
		inner_paging +="<span class='button' id='page_bt"+i+"' onclick='searchpage("+i+")'>"+i+"</span>";
		//inner_paging += "<li><a href='searchpage("+i+")' class='page active'>"+i+"</a></li>";
	}
	if(Integer.parseInt(cur_page)==paging_no){
		inner_paging +="<li><span class='button disabled'>Next</span></li>";
		inner_paging +="<li><span class='button disabled'>End</span></li>";
	}else{
		inner_paging +="<li><span class='button' onclick='searchpage("+nextpage+")'>Next</span></li>";
		inner_paging +="<li><span class='button' onclick='searchpage("+paging_no+")'>End</span></li>";
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
			$("#sort").val("desc");
			$("#column").val("apply_date");
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
		</script>
		
		<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
		
							<!-- Header -->
							
							<form name="f" method="post" action="depositList_pop.do" >
							<input type="hidden" name="column" id="column" value="<%=column%>">
							<input type="hidden" name="sort" id="sort" value="<%=sort%>">
							
							<input type="hidden" name="paging_no" value="<%=paging_no %>">
							<input type="hidden" name="paging_nam" value="<%=paging_nam %>">
							<input type="hidden" name="tot_cnt" value="<%=tot_cnt %>">
							<input type="hidden" name="cur_page" id="cur_page" value="<%=cur_page %>">
							<input type="hidden" name="deposit_pop_user_id" id="deposit_pop_user_id" value="<%=input_id %>">
							
							<header id="header" style="padding: 0.5em 0 0.5em 0;">
							</header>
							</form>
							<table  class="table" style="width:100%;overflow:auto">
								<colgroup>
							        <col  width="10%"/>
							        <col  width="15%"/>
							        <col  width="10%"/>
							        <col  width="10%"/>
							        <col  width="10%"/>
							        <col  width="10%"/>
							        <col  width="15%"/>
							        <col  width="10%"/>
							    </colgroup>
							    
							    <thead>
							        <tr>
							            <th scope="col" class="col" name="user_id">ID</th>
							            <th scope="col" class="col" name="apply_date">요청일</th>
							            <th scope="col" class="col" name="apply_money">입금액</th>
							            <th scope="col" class="col" name="deposit_status">처리여부</th>
							            <th scope="col" class="col" name="total_amount">처리후 보유금액</th>
							            <th scope="col" class="col" name="deposit_bonus">발생 보너스</th>
							            <th scope="col" class="col" name="updatedate">승인일</th>
							            <th scope="col" class="col" name="updateid">승인자</th>
							        </tr>
							    </thead>
							    
							    <tbody>
							        <c:choose>
							            <c:when test="${fn:length(list) > 0}">
							                <c:forEach items="${list }" var="row">
							                    <tr class="rows">
							                        <td name="r_user_id">${row.ID }</td>
							                        <td name="r_apply_date">${row.APPLY_DATE }</td>
							                        <td name="r_apply_money">${row.APPLY_MONEY }</td>
							                        <td name="r_deposit_status">${row.DEPOSIT_STATUS }</td>
							                        <td name="r_total_amount">${row.TOTAL_AMOUNT }</td>
							                        <td name="r_deposit_bonus">${row.DEPOSIT_BONUS }</td>
							                        <td name="r_updatedate">${row.UPDATEDATE }</td>
							                        <td name="r_updateid">${row.UPDATEID }</td>
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
						    
						    <div>
								<ul class="pagination">
								<!--
									<li><span class="button disabled">Prev</span></li>
									 
									<li><a href="#" class="page active">1</a></li>
									<li><a href="#" class="button">Next</a></li>
									 -->
								</ul>
						    </div>
				    	</div>
				    </div>
				    
				</div>
				    
	</body>
</html>