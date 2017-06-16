<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	JSONObject receiveobj = new JSONObject();
	//List<Map<String,Object>> arr = new ArrayList();
	//arr = (List<Map<String,Object>>)request.getAttribute("deposit_info");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_cd");
	String text = (String)request.getAttribute("text");
	
	String user_id = null;
	String event = null;
	String start_rate = null;
	String invest_money = null;
	String callput = null;
	String ext_rate = null;
	String game_type = null;
	String invest_result = null;
	String result_fee = null;
	String result_user_tot_amount = null;
	String result_user_last_amount = null;
	String result_user_possible_amount = null;
	String result_user_practice_amount = null;
	
	if(request.getAttribute("user_id")!=null){
		user_id = (String)request.getAttribute("user_id");
	}
	if(request.getAttribute("event")!=null){
		event = (String)request.getAttribute("event");
	}
	if(request.getAttribute("start_rate")!=null){
		start_rate = request.getAttribute("start_rate").toString();
	}
	if(request.getAttribute("invest_money")!=null){
		invest_money = request.getAttribute("invest_money").toString();
	}
	if(request.getAttribute("callput")!=null){
		callput = (String)request.getAttribute("callput");
	}
	if(request.getAttribute("ext_rate")!=null){
		ext_rate = request.getAttribute("ext_rate").toString();
	}
	if(request.getAttribute("game_type")!=null){
		game_type = (String)request.getAttribute("game_type");
	}
	if(request.getAttribute("invest_result")!=null){
		invest_result = (String)request.getAttribute("invest_result");
	}
	if(request.getAttribute("result_fee")!=null){
		result_fee = request.getAttribute("result_fee").toString();
	}
	if(request.getAttribute("result_user_tot_amount")!=null){
		result_user_tot_amount = request.getAttribute("result_user_tot_amount").toString();
	}
	if(request.getAttribute("result_user_last_amount")!=null){
		result_user_last_amount = request.getAttribute("result_user_last_amount").toString();
	}
	if(request.getAttribute("result_user_possible_amount")!=null){
		result_user_possible_amount = request.getAttribute("result_user_possible_amount").toString();
	}
	if(request.getAttribute("result_user_practice_amount")!=null){
		result_user_practice_amount = request.getAttribute("result_user_practice_amount").toString();
	}
	
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",text);
	
	receiveobj.put("user_id",user_id);
	receiveobj.put("event",event);
	receiveobj.put("start_rate",start_rate);
	receiveobj.put("invest_money",invest_money);
	receiveobj.put("callput",callput);
	receiveobj.put("ext_rate",ext_rate);
	receiveobj.put("game_type",game_type);
	receiveobj.put("invest_result",invest_result);
	receiveobj.put("result_fee",result_fee);
	receiveobj.put("result_user_tot_amount",result_user_tot_amount);
	receiveobj.put("result_user_last_amount",result_user_last_amount);
	receiveobj.put("result_user_possible_amount",result_user_possible_amount);
	receiveobj.put("result_user_practice_amount",result_user_practice_amount);
	
	out.println(receiveobj);
	
%>

