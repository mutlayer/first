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
	List<Map<String,Object>> arr = new ArrayList();
	arr = (List<Map<String,Object>>)request.getAttribute("receive_mydecomplete_order");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	String successYN = request.getAttribute("successYN").toString();
	String text = request.getAttribute("text").toString();
	String result_cd = request.getAttribute("result_cd").toString();
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",text);
	
	if(arr!=null){
		JSONArray list = new JSONArray();
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			
			String order_time="";
			String comp_time = "";
			String start_rate = "";
			String ext_rate = "";
			String event = "";
			String invest_money = "";
			String callput = "";
			String gametype = "";
			if(arr.get(i).get("ORDER_TIME")!=null){
				order_time = arr.get(i).get("ORDER_TIME").toString();
			}
			if(arr.get(i).get("COMP_TIME")!=null){
				comp_time = arr.get(i).get("COMP_TIME").toString();
			}
			if(arr.get(i).get("EVENT")!=null){
				event =arr.get(i).get("EVENT").toString();
			}
			if(arr.get(i).get("START_RATE")!=null){
				start_rate =arr.get(i).get("START_RATE").toString();
			}
			if(arr.get(i).get("EXT_RATE")!=null){
				ext_rate =arr.get(i).get("EXT_RATE").toString();
			}
			if(arr.get(i).get("INVEST_MONEY")!=null){
				invest_money =arr.get(i).get("INVEST_MONEY").toString();
			}
			if(arr.get(i).get("CALLPUT")!=null){
				callput =arr.get(i).get("CALLPUT").toString();
			}
			if(arr.get(i).get("GAME_TYPE")!=null){
				gametype =arr.get(i).get("GAME_TYPE").toString();
			}
			obj.put("order_time",order_time);
			obj.put("comp_time",comp_time);
			obj.put("event",event);
			obj.put("start_rate",start_rate);
			obj.put("ext_rate",ext_rate);
			obj.put("invest_money",invest_money);
			obj.put("callput",callput);
			obj.put("gametype",gametype);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("receive_mydecomplete_order",list);
	}else{
		receiveobj.put("receive_mydecomplete_order",null);
	}
	out.println(receiveobj);
	
%>

