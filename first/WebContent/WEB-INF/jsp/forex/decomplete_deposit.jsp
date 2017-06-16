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
	JSONObject obj = new JSONObject();
	String deposit_YN = null;
	
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_Cd");
	String text = (String)request.getAttribute("text");
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",text);
	
	if(request.getAttribute("decomplete_deposit")!=null){
		deposit_YN = request.getAttribute("decomplete_deposit").toString();
		
		receiveobj.put("decomplete_deposit",deposit_YN);
		//list.add(obj);
		
	}else{
		receiveobj.put("decomplete_deposit",null);
		//list.add(obj);
		
	}
	
	arr = (List<Map<String,Object>>)request.getAttribute("deposit_info");
	if(arr!=null){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		JSONArray list = new JSONArray();
		
		for(int i=0;i<arr.size();i++){
			JSONObject obj02 = new JSONObject();
			
			String idx =arr.get(i).get("IDX").toString();
			String deposit_money =(String)arr.get(i).get("DEPOSIT_MONEY").toString(); 
			String bonus_per =(String) arr.get(i).get("BONUS_PER").toString();
			String mission_multiple =(String)arr.get(i).get("MISSION_MULTIPLE").toString();
			
			obj02.put("idx",idx);
			obj02.put("deposit_money",deposit_money);
			obj02.put("bonus_per",bonus_per);
			obj02.put("mission_multiple",mission_multiple);
			
			list.add(obj02);
				
		}
		//obj.put("list",list);
		receiveobj.put("deposit_info",list);
	}else{
		receiveobj.put("deposit_info",null);
	}
	
	out.println(receiveobj);
%>

