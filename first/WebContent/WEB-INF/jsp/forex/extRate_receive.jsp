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
			String rate_key = (String)arr.get(i).get("RATE_KEY");
			String rate = arr.get(i).get("RATE").toString();
			
			obj.put("rate_name",rate_key);
			obj.put("rate",rate);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("list",list);
		
		out.println(receiveobj);
	}else{
		out.println("관리자에게 문의하세요.");
	}
	
	
%>

