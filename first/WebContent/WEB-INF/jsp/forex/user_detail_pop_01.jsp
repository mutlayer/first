
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
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
	}
	String sort = "";
	if(request.getAttribute("sort")!=null){
		sort=request.getAttribute("sort").toString();
	}
	
	String cur_page = "";
	if(request.getAttribute("cur_page")!=null){
		cur_page=request.getAttribute("cur_page").toString();
	}else{
		cur_page="1";
	}
	
	int paging_cnt = 0;
	int paging_no = 0;
	int paging_nam = 0;
	int tot_cnt = 0;
	
	String inner_paging="";
	
	List<Map<String,Object>> arr = new ArrayList();
	if(request.getAttribute("orderMap")!=null){
		arr = (List<Map<String,Object>>)request.getAttribute("orderMap");
		System.out.println("-====arr=====");
		System.out.println(arr);
		tot_cnt = 0;
		if(arr.size()>0){
			tot_cnt = (Integer)arr.get(0).get("CNT");
		}
		
		paging_cnt = 20;
		paging_no = 0;
		paging_nam = 0;
		
		if(tot_cnt/paging_cnt>0){
			paging_no = tot_cnt/paging_cnt;
			paging_nam = tot_cnt%paging_cnt;
			if(paging_nam>0){
				paging_no = paging_no+1;
			}
		}
		
		//String inner_paging = "<li><a href='#' class='button'>Next</a></li>";
		
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
	}
	
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	receiveMap = (Map<String,Object>)request.getAttribute("map");
	
	String user_id = "";
	String user_name = "";
	String last_amount = "";
	String practice_amount = "";
	String user_bank_nm = "";
	String user_bank_number = "";
	String user_phone_number = "";
	String possible_amount = "";
	String recommend_id = "";
	String updatedate="";
	String update_id = "";
	String user_ip = "";
	String bonus = "";
	String user_level = "";
	String del_yn = "";
	String login_fail_cnt = "";
	String insertdate = "";
	String tot_amount = "";
	
	
	if(receiveMap.get("USER_ID")!=null) { user_id = receiveMap.get("USER_ID").toString();	}
	if(receiveMap.get("USER_NAME")!=null){		user_name = receiveMap.get("USER_NAME").toString();	}
	if(receiveMap.get("LAST_AMOUNT")!=null){		last_amount = receiveMap.get("LAST_AMOUNT").toString();	}
	if(receiveMap.get("PRACTICE_AMOUNT")!=null){		practice_amount = receiveMap.get("PRACTICE_AMOUNT").toString();	}
	if(receiveMap.get("USER_BANK_NM")!=null){		user_bank_nm = receiveMap.get("USER_BANK_NM").toString();	}
	if(receiveMap.get("USER_BANK_NUMBER")!=null){		user_bank_number = receiveMap.get("USER_BANK_NUMBER").toString();	}
	if(receiveMap.get("POSSIBLE_AMOUNT")!=null){		possible_amount = receiveMap.get("POSSIBLE_AMOUNT").toString();	}
	if(receiveMap.get("RECOMMEND_ID")!=null){		recommend_id = receiveMap.get("RECOMMEND_ID").toString();	}
	if(receiveMap.get("UPDATEDATE")!=null){		updatedate = receiveMap.get("UPDATEDATE").toString();	}
	if(receiveMap.get("INSERTDATE")!=null){		insertdate = receiveMap.get("INSERTDATE").toString();	}
	if(receiveMap.get("UPDATE_ID")!=null){		update_id = receiveMap.get("UPDATE_ID").toString();	}
	if(receiveMap.get("USER_IP")!=null){		user_ip = receiveMap.get("USER_IP").toString();	}
	if(receiveMap.get("BONUS")!=null){		bonus = receiveMap.get("BONUS").toString();	}
	if(receiveMap.get("USER_LEVEL")!=null){		user_level = receiveMap.get("USER_LEVEL").toString();	}
	if(receiveMap.get("DEL_YN")!=null){		del_yn = receiveMap.get("DEL_YN").toString();	}
	if(receiveMap.get("LOGIN_FAIL_CNT")!=null){		login_fail_cnt = receiveMap.get("LOGIN_FAIL_CNT").toString();	}
	if(receiveMap.get("TOT_AMOUNT")!=null){		tot_amount = receiveMap.get("TOT_AMOUNT").toString();	}
	if(receiveMap.get("USER_PHONE_NUMBER")!=null){		user_phone_number = receiveMap.get("USER_PHONE_NUMBER").toString();	}
	
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
	<style>
    .tabmenu {position:relative; width:400px; height:10px;
             font-family:dotum,"",verdana;line-height:17px;font-size:12px;color:#555;}
    .tabmenu img {border:none;vertical-align:top;}
    .tabmenu ul {margin:0px;padding:0px;list-style:none;margin-top: 15px;}
    .tabmenu ul li {float:left}
    .tabmenu .tabcontent {display:none; width:240px;height:125px;position:absolute; left:0px;top:60px }
    .tabmenu .morebtn {position:absolute;right:0;top:30px;}
	</style>
	<body>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="/first/assets/js/jquery.min.js"></script>
		<script src="/first/assets/js/skel.min.js"></script>
		<script src="/first/assets/js/util.js"></script>
		<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
		<script src="/first/assets/js/main.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function(){
	        $(".tabmenu").each(function(){
	            var tab = $(this).children("ul");
	            var tabBtn = tab.children("li").children("a");
	            var content = tabBtn.nextAll();
	             
	            // 탭버튼을 클릭했을때
	            tabBtn.click(function(){
	            	alert($(this).attr("id"));
	                // 이미 on 상태면 pass
	                if( $(this).hasClass("on") ) return;
	 
	                // 모든 컨텐츠 부분을 안보이게 한뒤
	                content.hide();
	 
	                // 클릭한 tab 버튼(a태그) 옆의 모든 태그들은 보이도록
	                $(this).nextAll().show();
	                 
	                // 모든탭 버튼에 있던 on 클래스를 빼고
	                // 현재 클릭한 탭메뉴 버튼에 on 클래스 추가
	                tabBtn.removeClass("on");
	                $(this).addClass("on");
	                 
	                // 탭버튼를 쭉 돌면서 on 클래스가 있는 버튼만 on 이미지로 바꾸고
	                // 나머지 버튼들은 off 이미지로 바꾼다.
	                tabBtn.each(function(){
	                    var src;
	                    var img = $(this).children("img");
	                    if( $(this).hasClass("on") ){
	                        src = img.attr("src").replace("_off.", "_on.");
	                    }else{
	                        src = img.attr("src").replace("_on.", "_off.");
	                    }
	                     
	                    img.attr("src", src);
	                });
	            });
	             
	            // 맨첫번째 탭버튼 클릭처리
	            tabBtn.eq(0).click();
	        });
	    });
		
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
		
		</script>
		
		<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
		
							<!-- Header -->
								<header id="header" style="padding: 1.5em 0 0.5em 0;">
									<a href="/first/index.html" class="logo"><strong>사용자 상세</strong> by HTML5 UP</a>
									<ul class="icons">
										<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
									</ul>
								</header>
							<h3>기본정보</h3>

							<form method="post" action="#">
								<h4>Alternate</h4>
									<div class="table-wrapper">
										<table class="alt">
											<tbody>
												<tr>
													<td style="background-color:#F8E0E0">ID</td>
													<td style="background-color:#FFFFFF"><%=user_id %></td>
													<td style="background-color:#F8E0E0">이름</td>
													<td style="background-color:#FFFFFF"><%=user_name %></td>
													<td style="background-color:#F8E0E0">전화번호</td>
													<td style="background-color:#FFFFFF"><%=user_phone_number %></td>
												</tr>
												<tr>
													<td style="background-color:#F8E0E0">추천인</td>
													<td style="background-color:#FFFFFF"><%=recommend_id %></td>
													<td style="background-color:#F8E0E0">가입 IP</td>
													<td style="background-color:#FFFFFF"><%=user_ip %></td>
													<td style="background-color:#F8E0E0">가입일</td>
													<td style="background-color:#FFFFFF"><%=insertdate %></td>
												</tr>
												<tr>
													<td style="background-color:#F8E0E0">은행</td>
													<td style="background-color:#FFFFFF"><%=user_bank_nm %></td>
													<td style="background-color:#F8E0E0">계좌번호</td>
													<td style="background-color:#FFFFFF"><%=user_bank_number %></td>
													<td style="background-color:#F8E0E0">보유계좌금액</td>
													<td style="background-color:#FFFFFF"><%=tot_amount %></td>
												</tr>
												<tr>
													<td style="background-color:#F8E0E0">보너스</td>
													<td style="background-color:#FFFFFF"><%=bonus %></td>
													<td style="background-color:#F8E0E0">연습계좌금액</td>
													<td style="background-color:#FFFFFF"><%=practice_amount %></td>
													<td style="background-color:#F8E0E0">레벨</td>
													<td style="background-color:#FFFFFF"><%=user_level %></td>
												</tr>
												<tr>
													<td style="background-color:#F8E0E0">삭제여부</td>
													<td style="background-color:#FFFFFF"><%=del_yn %></td>
													<td style="background-color:#F8E0E0">수정일</td>
													<td style="background-color:#FFFFFF"><%=updatedate %></td>
													<td style="background-color:#F8E0E0">수정자</td>
													<td style="background-color:#FFFFFF"><%=update_id %></td>
												</tr>
												<tr>
													<td style="background-color:#F8E0E0">로그인실패</td>
													<td colspan="5"><%=login_fail_cnt %></td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="tabmenu">
								 
								    <ul>
								        <li><a href="#link" id="orderList" name="orderList"><img src="/first/images/menu_01_off.gif" alt="메뉴01" /></a>
								        </li>
								        <li><a href="#link" id="menu2" name="menu2"><img src="/first/images/menu_02_off.gif" alt="메뉴02" /></a>
								        </li>
								        <li><a href="#link" id="menu3" name="menu3"><img src="/first/images/menu_03_off.gif" alt="메뉴03" /></a>
								        </li>
								        <li><a href="#link" id="menu4" name="menu4"><img src="/first/images/menu_04_off.gif" alt="메뉴04" /></a>
								            <ul class="tabcontent">
								                <li><a href="#">ㄷㄹㄴㄹㄴㄹㄴㄷㄹㄴㄹ</a></li>
								                <li><a href="#">ㄷㄹㄴㄹㄴㄹㄴㄷㄹㄴㄹ</a></li>
								            </ul>
								            <p class="morebtn"><a href="#"><img src="/first/images/more.gif" alt="MORE" /></a></p>
								        </li>
								    </ul>
								</div>
								
								<div class="inner">
		
									
									<form name="f" method="post" action="orderList.do" >
									<input type="hidden" name="column" id="column" value="<%=column%>">
									<input type="hidden" name="sort" id="sort" value="<%=sort%>">
									
									<input type="hidden" name="paging_no" value="<%=paging_no %>">
									<input type="hidden" name="paging_nam" value="<%=paging_nam %>">
									<input type="hidden" name="tot_cnt" value="<%=tot_cnt %>">
									<input type="hidden" name="cur_page" id="cur_page" value="<%=cur_page %>">
									
									<header id="header" style="padding: 0.5em 0 0.5em 0;">
									</header>
									</form>
									<table  class="table" style="width:100%;overflow:auto">
										<colgroup>
									        <col  width="6%"/>
									        <col  width="7%"/>
									        <col  width="10%"/>
									        <col  width="10%"/>
									        <col  width="5%"/>
									        <col  width="7%"/>
									        <col  width="7%"/>
									        <col  width="5%"/>
									        <col  width="5%"/>
									        <col  width="10%"/>
									        <col  width="10%"/>
									        <col  width="5%"/>
									        <col  width="5%"/>
									        <col  width="8%"/>
									        
									    </colgroup>
									    
									    <thead>
									        <tr>
									            <th scope="col" class="col" name="user_id">ID</th>
									            <th scope="col" class="col" name="user_name">이름</th>
									            <th scope="col" class="col" name="order_time">주문시간</th>
									            <th scope="col" class="col" name="comp_time">완료시간</th>
									            <th scope="col" class="col" name="status">상태</th>
									            <th scope="col" class="col" name="event">종목</th>
									            <th scope="col" class="col" name="invest_money">투자금</th>
									            <th scope="col" class="col" name="option_type">옵션타입</th>
									            <th scope="col" class="col" name="callput">C/P</th>
									            <th scope="col" class="col" name="start_rate">시작가</th>
									            <th scope="col" class="col" name="end_rate">종료가</th>
									            <th scope="col" class="col" name="ext_rate">이득율</th>
									            <th scope="col" class="col" name="invest_result">결과</th>
									            <th scope="col" class="col" name="result_fee">지불금</th>
									            
									        </tr>
									    </thead>
									    
									    <tbody>
									        <c:choose>
									            <c:when test="${fn:length(orderMap) > 0}">
									                <c:forEach items="${orderMap }" var="row">
									                    <tr class="rows">
									                        <td name="r_user_id">${row.USER_ID }</td>
									                        <td name="r_user_name">${row.USER_NAME }</td>
									                        <td name="r_order_time">${row.ORDER_TIME }</td>
									                        <td name="r_comp_time">${row.COMP_TIME }</td>
									                        <td name="r_status">${row.STATUS }</td>
									                        <td name="r_event">${row.EVENT }</td>
									                        <td name="r_invest_money">${row.INVEST_MONEY }</td>
									                        <td name="r_option_type">${row.OPTION_TYPE }</td>
									                        <td name="r_callput">${row.CALLPUT }</td>
									                        <td name="r_start_rate">${row.START_RATE }</td>
									                        <td name="r_end_rate">${row.END_RATE }</td>
									                        <td name="r_ext_rate">${row.EXT_RATE }</td>
									                        <td name="r_invest_result">${row.INVEST_RESULT }</td>
									                        <td name="r_result_fee">${row.RESULT_FEE }</td>
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
								
								
							</form>
				    	</div>
				    </div>
				    
				    <jsp:include page="/sidebar.html" flush="true"></jsp:include>
				</div>
				    
	</body>
</html>