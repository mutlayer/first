<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	out.println("login02");
	//String u_id = request.getParameter("id");
	//out.println(request.getParameter("email"));
	Map x = (Map)request.getAttribute("map");	
	String id = (String)x.get("user_id");
	String pwd = (String)x.get("user_pwd");
	String email = (String)x.get("user_email");
	String name = (String)x.get("user_name");
	String phone = (String)x.get("user_phone_number");
	
	JSONArray list = new JSONArray();
	list.add(id);
	list.add(pwd);
	list.add(email);
	list.add(name);
	list.add(phone);
	
	JSONObject obj = new JSONObject();
	obj.put("result","Y");
	obj.put("info",list);
	
	out.print(obj);
%>

