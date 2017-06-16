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
	arr = (List<Map<String,Object>>)request.getAttribute("decomplete_list");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_Cd");
	String text = (String)request.getAttribute("text");
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",text);
	
	if(arr!=null){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		JSONArray list = new JSONArray();
		
		
		
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			
			String decom_actkey =(String)arr.get(i).get("ACTKEY"); 
			String decom_user_id =(String) arr.get(i).get("USER_ID");
			String decom_order_time =dateFormat.format(arr.get(i).get("ORDER_TIME"));
			String decom_game_type =(String)arr.get(i).get("GAME_TYPE");
			String decom_status = (String)arr.get(i).get("STATUS");
			String decom_event = (String) arr.get(i).get("EVENT");
			String decom_invest_money = arr.get(i).get("INVEST_MONEY").toString();
			String decom_option_type = (String)arr.get(i).get("OPTION_TYPE");
			String decom_callput = (String) arr.get(i).get("CALLPUT");
			String decom_start_rate = arr.get(i).get("START_RATE").toString();
			String decom_ext_rate = arr.get(i).get("EXT_RATE").toString();
			
			obj.put("actkey",decom_actkey);
			obj.put("user_id",decom_user_id);
			obj.put("order_time",decom_order_time);
			obj.put("game_type",decom_game_type);
			obj.put("status",decom_status);
			obj.put("event",decom_event);
			obj.put("invest_money",decom_invest_money);
			obj.put("option_type",decom_option_type);
			obj.put("callput",decom_callput);
			obj.put("start_rate",decom_start_rate);
			obj.put("ext_rate",decom_ext_rate);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("decomplete_list",list);
	}else{
		receiveobj.put("decomplete_list",null);
	}
	out.println(receiveobj);
	
%>

