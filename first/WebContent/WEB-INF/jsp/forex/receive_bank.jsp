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
	arr = (List<Map<String,Object>>)request.getAttribute("receive_bank");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_Cd");
	String text = (String)request.getAttribute("text");
	
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("successYN",successYN);
	receiveobj.put("text",text);
	
	if(arr!=null){
		JSONArray list = new JSONArray();
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			
			String cd =(String)arr.get(i).get("CD"); 
			String nm =(String) arr.get(i).get("NM");
			
			obj.put("bank_cd",cd);
			obj.put("bank_nm",nm);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("bank_list",list);
	}else{
		receiveobj.put("bank_list",null);
	}
	out.println(receiveobj);
	
%>

