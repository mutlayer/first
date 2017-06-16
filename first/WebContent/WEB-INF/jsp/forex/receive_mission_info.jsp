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
	System.out.println("============================================");
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	
	String successYN = request.getAttribute("successYN").toString();
	String text = request.getAttribute("text").toString();
	String result_cd = request.getAttribute("result_cd").toString();
	
	if(request.getAttribute("successYN")=="Y"){
		
		String mission_money = request.getAttribute("mission_money").toString();
		String input_bonus = request.getAttribute("input_bonus").toString();
		String processed_bonus = request.getAttribute("processed_bonus").toString();
		String possible_amount = request.getAttribute("possible_amount").toString();
		
		obj.put("successYN",successYN);
		obj.put("result_cd",result_cd);
		obj.put("text",text);
		obj.put("mission_money",mission_money);
		obj.put("input_bonus",input_bonus);
		obj.put("processed_bonus",processed_bonus);
		obj.put("possible_amount",possible_amount);
		//list.add(obj);
		out.println(obj);
	}else{
		obj.put("successYN","successYN");
		obj.put("result_cd",result_cd);
		obj.put("text",text);
		obj.put("mission_money",null);
		obj.put("input_bonus",null);
		obj.put("processed_bonus",null);
		obj.put("possible_amount",null);
		//list.add(obj);
		out.println(obj);
	}
	
%>

