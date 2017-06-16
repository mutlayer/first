<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	String game_type = (String)request.getAttribute("game_type");
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_cd");
	String text = (String)request.getAttribute("text");
	
	String mission_money = "";
	if(request.getAttribute("mission_money")!=null){
		mission_money = request.getAttribute("mission_money").toString();
	}
	String processed_bonus = "";
	if(request.getAttribute("processed_bonus")!=null){
		processed_bonus = (String)request.getAttribute("processed_bonus").toString();
	}
	String input_bonus = "";
	if(request.getAttribute("input_bonus")!=null){
		input_bonus = (String)request.getAttribute("input_bonus").toString();
	}
	
	System.out.println(successYN);
	System.out.println(game_type);
	System.out.println(result_cd);
	System.out.println(text);
	if(successYN.equals("Y")){
		if(game_type.equals("R")){
		
			//Map x = (Map)request.getAttribute("map");	
			String bet = (String)request.getAttribute("order_YN");
			String alert = (String)request.getAttribute("alert");
			String alert_txt = (String)request.getAttribute("alert_txt");
			String tot_amount = request.getAttribute("tot_amount").toString();
			String actkey = (String)request.getAttribute("actkey");
			String order_time = (String)request.getAttribute("order_time");
			String comp_time = (String)request.getAttribute("comp_time");
			String practice_amount = request.getAttribute("practice_amount").toString();
			String start_rate = request.getAttribute("start_rate").toString();
			
			JSONArray list = new JSONArray();
			JSONObject obj = new JSONObject();
			JSONObject receiveobj = new JSONObject();
			/*
			obj.put("BET_YN",bet);
			obj.put("ALERT",alert);
			obj.put("ALERT_TXT",alert_txt);
			obj.put("TOT_AMOUNT",tot_amount);
			obj.put("PRACTICE_AMOUNT",practice_amount);
			obj.put("ACTKEY",actkey);
			obj.put("ORDER_TIME",order_time);
			obj.put("COMP_TIME",comp_time);
			obj.put("START_RATE",start_rate);
			
			obj.put("MISSION_MONEY",mission_money);
			obj.put("INPUT_BONUS",input_bonus);
			obj.put("PROCESSED_BONUS",processed_bonus);
			*/
			
			receiveobj.put("BET_YN",bet);
			//receiveobj.put("ALERT",alert);
			//receiveobj.put("ALERT_TXT",alert_txt);
			receiveobj.put("TOT_AMOUNT",tot_amount);
			receiveobj.put("PRACTICE_AMOUNT",practice_amount);
			receiveobj.put("ACTKEY",actkey);
			receiveobj.put("ORDER_TIME",order_time);
			receiveobj.put("COMP_TIME",comp_time);
			receiveobj.put("START_RATE",start_rate);
			
			receiveobj.put("MISSION_MONEY",mission_money);
			receiveobj.put("INPUT_BONUS",input_bonus);
			receiveobj.put("PROCESSED_BONUS",processed_bonus);
			receiveobj.put("successYN",successYN);
			receiveobj.put("result_cd",result_cd);
			receiveobj.put("text",text);
			
			//list.add(obj);
			
			//receiveobj.put("bet_info",list);
			out.println(receiveobj);
		}else{
			//Map x = (Map)request.getAttribute("map");	
			String bet = (String)request.getAttribute("order_YN");
			String alert = (String)request.getAttribute("alert");
			String alert_txt = (String)request.getAttribute("alert_txt");
			String practice_amount = request.getAttribute("practice_amount").toString();
			String actkey = (String)request.getAttribute("actkey");
			String order_time = (String)request.getAttribute("order_time");
			String comp_time = (String)request.getAttribute("comp_time");
			String tot_amount = request.getAttribute("tot_amount").toString();
			String start_rate = request.getAttribute("start_rate").toString();
			
			JSONArray list = new JSONArray();
			JSONObject obj = new JSONObject();
			JSONObject receiveobj = new JSONObject();
			
			/*
			obj.put("BET_YN",bet);
			obj.put("ALERT",alert);
			obj.put("ALERT_TXT",alert_txt);
			obj.put("PRACTICE_AMOUNT",practice_amount);
			obj.put("TOT_AMOUNT",tot_amount);
			obj.put("ACTKEY",actkey);
			obj.put("ORDER_TIME",order_time);
			obj.put("COMP_TIME",comp_time);
			obj.put("START_RATE",start_rate);
			
			obj.put("MISSION_MONEY",mission_money);
			obj.put("INPUT_BONUS",input_bonus);
			obj.put("PROCESSED_BONUS",processed_bonus);
			*/
			
			receiveobj.put("BET_YN",bet);
			//receiveobj.put("ALERT",alert);
			//receiveobj.put("ALERT_TXT",alert_txt);
			receiveobj.put("PRACTICE_AMOUNT",practice_amount);
			receiveobj.put("TOT_AMOUNT",tot_amount);
			receiveobj.put("ACTKEY",actkey);
			receiveobj.put("ORDER_TIME",order_time);
			receiveobj.put("COMP_TIME",comp_time);
			receiveobj.put("START_RATE",start_rate);
			
			receiveobj.put("MISSION_MONEY",mission_money);
			receiveobj.put("INPUT_BONUS",input_bonus);
			receiveobj.put("PROCESSED_BONUS",processed_bonus);
			
			receiveobj.put("successYN",successYN);
			receiveobj.put("result_cd",result_cd);
			receiveobj.put("text",text);
			
			//list.add(obj);
			
			//receiveobj.put("bet_info",list);
			out.println(receiveobj);
		}
	}else{
		JSONObject receiveobj = new JSONObject();
		receiveobj.put("successYN",successYN);
		receiveobj.put("result_cd",result_cd);
		receiveobj.put("text",text);
		receiveobj.put("bet_info",null);
		out.println(receiveobj);
	}
	
%>

