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
	arr = (List<Map<String,Object>>)request.getAttribute("receive_refund_his");
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
			String id = "";
			String apply_date="";
			String apply_money="";
			String refund_status = "";
			String updateid = "";
			String updatedate = "";
			if(arr.get(i).get("ROWNUM")!=null){
				rownum = String.valueOf(arr.get(i).get("ROWNUM")); 
			}
			if(arr.get(i).get("ID")!=null){
				id =(String) arr.get(i).get("ID");
			}
			if(arr.get(i).get("APPLY_DATE")!=null){
				apply_date =arr.get(i).get("APPLY_DATE").toString();
			}
			if(arr.get(i).get("APPLY_MONEY")!=null){
				apply_money =arr.get(i).get("APPLY_MONEY").toString();
			}
			if(arr.get(i).get("REFUND_STATUS")!=null){
				refund_status =(String) arr.get(i).get("REFUND_STATUS");
			}
			if(arr.get(i).get("UPDATEID")!=null){
				updateid =(String) arr.get(i).get("UPDATEID");
			}
			if(arr.get(i).get("UPDATEDATE")!=null){
				updatedate = arr.get(i).get("UPDATEDATE").toString();
			}
			
			obj.put("rownum",rownum);
			obj.put("id",id);
			obj.put("apply_date",apply_date);
			obj.put("apply_money",apply_money);
			obj.put("refund_status",refund_status);
			obj.put("updateid",updateid);
			obj.put("updatedate",updatedate);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("receive_refund_his",list);
	}else{
		receiveobj.put("receive_refund_his",null);
	}
	out.println(receiveobj);
	
%>

