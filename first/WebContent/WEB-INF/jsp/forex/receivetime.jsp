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
	
	System.out.println(successYN);
	System.out.println(text);
	System.out.println(result_cd);
	
	
	String rate = null;
	String user_tot_amount = null;
	String user_practice_amount = null;
	/*
	String rate_id = null;
	String rate_name = null;
	String rate_time = null;
	String rate_date = null;
	String rate_bid = null;
	String rate_ask = null;
	String rate_rate = null;
	*/
	String cur_time = null;
	String callpct = null;
	String putpct = null;
	
	String mission_money = null;
	String input_bonus = null;
	String processed_bonus = null;
	
	if(successYN.equals("Y")){
		rate = request.getAttribute("rate").toString();
		user_tot_amount = request.getAttribute("user_tot_amount").toString();
		user_practice_amount = request.getAttribute("user_practice_amount").toString();
		/*
		rate_id = request.getAttribute("rate_id").toString();
		rate_name = request.getAttribute("rate_name").toString();
		rate_time = request.getAttribute("rate_time").toString();
		rate_date = request.getAttribute("rate_date").toString();
		rate_bid = request.getAttribute("rate_bid").toString();
		rate_ask = request.getAttribute("rate_ask").toString();
		rate_rate = request.getAttribute("rate_rate").toString();
		*/
		callpct = request.getAttribute("callpct").toString();
		putpct = request.getAttribute("putpct").toString();
		
		mission_money = request.getAttribute("mission_money").toString();
		input_bonus = request.getAttribute("input_bonus").toString();
		processed_bonus = request.getAttribute("processed_bonus").toString();
	}
		
	
	
	
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	
	obj.put("successYN",successYN);
	obj.put("text",text);
	obj.put("result_cd",result_cd);
	
	/*
	obj.put("rate_id",rate_id);
	obj.put("rate_name",rate_name);
	obj.put("rate_time",rate_time);
	obj.put("rate_date",rate_date);
	obj.put("rate_bid",rate_bid);
	obj.put("rate_ask",rate_ask);
	obj.put("rate_rate",rate_rate);
	*/
	obj.put("ext_rate",rate);	//이득율
	obj.put("callpct",callpct);//콜 퍼센트
	obj.put("putpct",putpct);// 풋 퍼센트
	
	obj.put("mission_money",mission_money);	//
	obj.put("input_bonus",input_bonus);//
	obj.put("processed_bonus",processed_bonus);//
	
	obj.put("tot_amount",user_tot_amount); // 총보유금액
	obj.put("practice_amount",user_practice_amount);	//연습계좌금액
	//list.add(obj);
	out.println(obj);
	
%>

