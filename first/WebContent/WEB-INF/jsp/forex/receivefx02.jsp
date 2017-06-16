<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.net.*" %>

<%
	//InetAddress inet= InetAddress.getLocalHost();
	//out.println(inet.getHostAddress());
	String successYN = request.getAttribute("successYN").toString();
	String text = request.getAttribute("text").toString();
	String result_cd = request.getAttribute("result_cd").toString();
	
	String rate = null;
	String user_tot_amount = null;
	String user_practice_amount = null;
	String cur_time = null;
	String callpct = null;
	String putpct = null;
	
	if(successYN.equals("Y")){
		rate = request.getAttribute("rate").toString();
		user_tot_amount = request.getAttribute("user_tot_amount").toString();
		user_practice_amount = request.getAttribute("user_practice_amount").toString();
		cur_time = request.getAttribute("cur_time").toString();
		callpct = request.getAttribute("callpct").toString();
		putpct = request.getAttribute("putpct").toString();
	}
		
	
	
	
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	
	obj.put("successYN",successYN);
	obj.put("text",text);
	obj.put("result_cd",result_cd);
	
	
	
	obj.put("ext_rate",rate);	//이득율
	obj.put("cur_time",cur_time);	//현재시간
	obj.put("callpct",callpct);//콜 퍼센트
	obj.put("putpct",putpct);// 풋 퍼센트
	obj.put("tot_amount",user_tot_amount); // 총보유금액
	obj.put("practice_amount",user_practice_amount);	//연습계좌금액
	//list.add(obj);
	out.println(obj);
	
%>

