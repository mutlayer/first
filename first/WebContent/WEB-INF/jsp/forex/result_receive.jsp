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
	List<Map<String,Object>> arr = new ArrayList();
	arr = (List<Map<String,Object>>)request.getAttribute("list");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	
	
	if(arr!=null){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		JSONArray list = new JSONArray();
		
		JSONObject receiveobj = new JSONObject();
		
		String successYN = request.getAttribute("successYN").toString();
		String text = request.getAttribute("text").toString();
		String result_cd = request.getAttribute("result_cd").toString();
		
		receiveobj.put("successYN",successYN);
		receiveobj.put("result_cd",result_cd);
		receiveobj.put("text",text);
		
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			String g_type = (String)arr.get(i).get("GAME_TYPE");
			if(g_type.equals("R")){
				
				String result_fee =arr.get(i).get("RESULT_FEE").toString(); 
				String event =(String) arr.get(i).get("EVENT");
				String start_rate =arr.get(i).get("START_RATE").toString();
				String end_rate =arr.get(i).get("END_RATE").toString();
				String userid = (String)arr.get(i).get("USER_ID");
				String callput = (String) arr.get(i).get("CALLPUT");
				String order_time = dateFormat.format(arr.get(i).get("ORDER_TIME"));
				String ext_rate = arr.get(i).get("EXT_RATE").toString();
				String status = (String) arr.get(i).get("STATUS");
				String insertdate = dateFormat.format(arr.get(i).get("INSERTDATE"));
				String option_type = (String) arr.get(i).get("OPTION_TYPE");
				String comp_time = dateFormat.format(arr.get(i).get("COMP_TIME"));
				String invest_money = arr.get(i).get("INVEST_MONEY").toString();
				String tot_amount = arr.get(i).get("TOT_AMOUNT").toString();
				
				obj.put("result_fee",result_fee);
				obj.put("event",event);
				obj.put("start_rate",start_rate);
				obj.put("end_rate",end_rate);
				obj.put("user_id",userid);
				obj.put("callput",callput);
				obj.put("order_time",order_time);
				obj.put("ext_rate",ext_rate);
				obj.put("status",status);
				obj.put("insertdate",insertdate);
				obj.put("option_type",option_type);
				obj.put("comp_time",comp_time);
				obj.put("invest_money",invest_money);
				obj.put("tot_amount",tot_amount);
				
				list.add(obj);
			}else{
				String result_fee =arr.get(i).get("RESULT_FEE").toString(); 
				String event =(String) arr.get(i).get("EVENT");
				String start_rate =arr.get(i).get("START_RATE").toString();
				String end_rate =arr.get(i).get("END_RATE").toString();
				String userid = (String)arr.get(i).get("USER_ID");
				String callput = (String) arr.get(i).get("CALLPUT");
				String order_time = dateFormat.format(arr.get(i).get("ORDER_TIME"));
				String ext_rate = arr.get(i).get("EXT_RATE").toString();
				String status = (String) arr.get(i).get("STATUS");
				String insertdate = dateFormat.format(arr.get(i).get("INSERTDATE"));
				String option_type = (String) arr.get(i).get("OPTION_TYPE");
				String comp_time = dateFormat.format(arr.get(i).get("COMP_TIME"));
				String invest_money = arr.get(i).get("INVEST_MONEY").toString();
				String practice_amount = arr.get(i).get("PRACTICE_AMOUNT").toString();
				
				obj.put("result_fee",result_fee);
				obj.put("event",event);
				obj.put("start_rate",start_rate);
				obj.put("end_rate",end_rate);
				obj.put("user_id",userid);
				obj.put("callput",callput);
				obj.put("order_time",order_time);
				obj.put("ext_rate",ext_rate);
				obj.put("status",status);
				obj.put("insertdate",insertdate);
				obj.put("option_type",option_type);
				obj.put("comp_time",comp_time);
				obj.put("invest_money",invest_money);
				obj.put("practice_amount",practice_amount);
				list.add(obj);
				
			}
			
				
		}
		//obj.put("list",list);
		receiveobj.put("list",list);
		
		out.println(receiveobj);
	}else{
		out.println("");
	}
	
	
%>

