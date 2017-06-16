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
	String result_fee = request.getAttribute("final_result_fee").toString();
	String tot_amount = request.getAttribute("final_tot_amount").toString();
	
//	out.println(result_fee);
//	out.println(tot_amount);
	
	
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	
	//list.add(result_fee);
	//list.add(tot_amount);
	
	obj.put("result_fee",result_fee);
	obj.put("tot_amount",tot_amount);
	
	list.add(obj);
	
	out.println(list);
	//obj.put("bet_final_info",list);
	//out.println(obj);
	
%>

