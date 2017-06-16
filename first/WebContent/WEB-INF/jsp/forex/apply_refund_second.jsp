<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.net.*" %>

<%
	//InetAddress inet= InetAddress.getLocalHost();
	//out.println(inet.getHostAddress());
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	if(request.getAttribute("successYN")!=null){
		String successYN = request.getAttribute("successYN").toString();
		String text = request.getAttribute("text").toString();
		String result_cd = request.getAttribute("result_cd").toString();
		
		obj.put("successYN",successYN);
		obj.put("result_cd",result_cd);
		obj.put("text",text);
		//list.add(obj);
		out.println(obj);
	}else{
		obj.put("successYN","N");
		obj.put("text","인증실패");
		//list.add(obj);
		out.println(obj);
	}
	
%>

