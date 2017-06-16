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
	arr = (List<Map<String,Object>>)request.getAttribute("receive_myorder");
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
			
			String rownum = "";
			String comp = "";
			String user_id="";
			String order_time="";
			String comp_time = "";
			String event = "";
			String option_type = "";
			String start_rate = "";
			String end_rate = "";
			String ext_rate = "";
			String invest_money = "";
			String callput = "";
			String res_fee = "";
			String game_type = "";
			if(arr.get(i).get("ROWNUM")!=null){
				rownum = String.valueOf(arr.get(i).get("ROWNUM")); 
			}
			if(arr.get(i).get("COMP")!=null){
				comp =(String) arr.get(i).get("COMP");
			}
			if(arr.get(i).get("USER_ID")!=null){
				user_id =arr.get(i).get("USER_ID").toString();
			}
			if(arr.get(i).get("ORDER_TIME")!=null){
				order_time = arr.get(i).get("ORDER_TIME").toString();
			}
			if(arr.get(i).get("COMP_TIME")!=null){
				comp_time = arr.get(i).get("COMP_TIME").toString();
			}
			if(arr.get(i).get("EVENT")!=null){
				event =arr.get(i).get("EVENT").toString();
			}
			if(arr.get(i).get("OPTION_TYPE")!=null){
				option_type =arr.get(i).get("OPTION_TYPE").toString();
			}
			if(arr.get(i).get("START_RATE")!=null){
				start_rate =arr.get(i).get("START_RATE").toString();
			}
			if(arr.get(i).get("END_RATE")!=null){
				end_rate =arr.get(i).get("END_RATE").toString();
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
			if(arr.get(i).get("RES_FEE")!=null){
				res_fee =arr.get(i).get("RES_FEE").toString();
			}
			if(arr.get(i).get("GAME_TYPE")!=null){
				game_type =arr.get(i).get("GAME_TYPE").toString();
			}
			obj.put("rownum",rownum);
			obj.put("comp",comp);
			obj.put("user_id",user_id);
			obj.put("order_time",order_time);
			obj.put("comp_time",comp_time);
			obj.put("event",event);
			obj.put("option_type",option_type);
			obj.put("start_rate",start_rate);
			obj.put("end_rate",end_rate);
			obj.put("ext_rate",ext_rate);
			obj.put("invest_money",invest_money);
			obj.put("callput",callput);
			obj.put("res_fee",res_fee);
			obj.put("game_type",game_type);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("receive_myorder",list);
	}else{
		receiveobj.put("receive_myorder",null);
	}
	out.println(receiveobj);
	
%>

