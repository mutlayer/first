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
	//arr = (List<Map<String,Object>>)request.getAttribute("receive_client_notice");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	receiveMap = (Map<String,Object>)request.getAttribute("receive_client_notice");
	
	String successYN = request.getAttribute("successYN").toString();
	String text = request.getAttribute("text").toString();
	String result_cd = request.getAttribute("result_cd").toString();
	
	System.out.println(successYN);
	System.out.println(text);
	System.out.println(result_cd);
	
	String s_date = (String)request.getAttribute("s_date");
	String e_date = (String)request.getAttribute("e_date");
	String content = (String)request.getAttribute("content");
	
	
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",content);
	
	//receiveobj.put("s_date",s_date);
	receiveobj.put("e_date",e_date);
	//receiveobj.put("content",content);
	
	out.println(receiveobj);
	
	/*
	if(arr!=null){
		JSONArray list = new JSONArray();
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			
			String title="";
			String contents = "";
			String s_date = "";
			String e_date = "";
			if(arr.get(i).get("TITLE")!=null){
				title =(String) arr.get(i).get("TITLE");
			}
			if(arr.get(i).get("CONTENTS")!=null){
				contents =arr.get(i).get("CONTENTS").toString();
			}
			if(arr.get(i).get("S_DATE")!=null){
				s_date =arr.get(i).get("S_DATE").toString();
			}
			if(arr.get(i).get("E_DATE")!=null){
				e_date =arr.get(i).get("E_DATE").toString();
			}
			
			obj.put("title",title);
			obj.put("contents",contents);
			obj.put("s_date",s_date);
			obj.put("e_date",e_date);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("receive_client_notice",list);
	}else{
		receiveobj.put("receive_client_notice",null);
	}
	out.println(receiveobj);
	*/
%>

