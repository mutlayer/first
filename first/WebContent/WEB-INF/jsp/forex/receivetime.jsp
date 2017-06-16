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
	String cur_time = null;
	
		cur_time = request.getAttribute("cur_time").toString();
		
	
	
	
	JSONArray list = new JSONArray();
	JSONObject obj = new JSONObject();
	
	obj.put("cur_time",cur_time);	//현재시간
	//list.add(obj);
	out.println(obj);
	
%>

