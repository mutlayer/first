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
	arr = (List<Map<String,Object>>)request.getAttribute("receivefx03");
	Map<String, Object> receiveMap = new HashMap<String,Object>();
	
	String successYN = request.getAttribute("successYN").toString();
	String text = request.getAttribute("text").toString();
	String result_cd = request.getAttribute("result_cd").toString();
	
	String user_tot_amount = null;
	String user_practice_amount = null;
	String callpct = null;
	String putpct = null;
	String mission_money = null;
	String input_bonus = null;
	String processed_bonus = null;
	String tot_amount = null;
	String practice_amount = null;
	
	receiveobj.put("successYN",successYN);
	receiveobj.put("result_cd",result_cd);
	receiveobj.put("text",text);
	
	
	
	if(arr!=null){
		
		if(arr.get(0).get("TOT_AMOUNT")!=null){
			//user_tot_amount =arr.get(i).get("TOT_AMOUNT").toString();
			user_tot_amount =arr.get(0).get("TOT_AMOUNT").toString();
		}
		if(arr.get(0).get("PRACTICE_AMOUNT")!=null){
			//user_practice_amount =arr.get(i).get("PRACTICE_AMOUNT").toString();
			user_practice_amount =arr.get(0).get("PRACTICE_AMOUNT").toString();
		}
		if(arr.get(0).get("CALLPCT")!=null){
			callpct =arr.get(0).get("CALLPCT").toString();
		}
		if(arr.get(0).get("PUTPCT")!=null){
			putpct =arr.get(0).get("PUTPCT").toString();
		}
		if(arr.get(0).get("MISSION_MONEY")!=null){
			//mission_money =arr.get(i).get("MISSION_MONEY").toString();
			mission_money =arr.get(0).get("MISSION_MONEY").toString();
		}
		if(arr.get(0).get("INPUT_BONUS")!=null){
			//input_bonus = arr.get(i).get("INPUT_BONUS").toString();
			input_bonus = arr.get(0).get("INPUT_BONUS").toString();
		}
		if(arr.get(0).get("PROCESSED_BONUS")!=null){
			//processed_bonus = arr.get(i).get("PROCESSED_BONUS").toString();
			processed_bonus = arr.get(0).get("PROCESSED_BONUS").toString();
		}
		if(arr.get(0).get("TOT_AMOUNT")!=null){
			//tot_amount = arr.get(i).get("TOT_AMOUNT").toString();
			tot_amount = arr.get(0).get("TOT_AMOUNT").toString();
		}
		if(arr.get(0).get("PRACTICE_AMOUNT")!=null){
			//practice_amount = arr.get(i).get("PRACTICE_AMOUNT").toString();
			practice_amount = arr.get(0).get("PRACTICE_AMOUNT").toString();
		}
		
		receiveobj.put("callpct",callpct);
		receiveobj.put("putpct",putpct);
		receiveobj.put("mission_money",mission_money);
		receiveobj.put("input_bonus",input_bonus);
		receiveobj.put("processed_bonus",processed_bonus);
		receiveobj.put("tot_amount",user_tot_amount);
		receiveobj.put("practice_amount",practice_amount);
		
		
		JSONArray list = new JSONArray();
		List<Map<String,Object>> arr_line = new ArrayList();
		for(int i=0;i<arr.size();i++){
			JSONObject obj = new JSONObject();
			
			String rate_key = null;
			String rate = null;
			/*
			String user_tot_amount = null;
			String user_practice_amount = null;
			String callpct = null;
			String putpct = null;
			String mission_money = null;
			String input_bonus = null;
			String processed_bonus = null;
			String tot_amount = null;
			String practice_amount = null;
			*/
			if(arr.get(i).get("RATE_KEY")!=null){
				rate_key = arr.get(i).get("RATE_KEY").toString(); 
			}
			if(arr.get(i).get("RATE")!=null){
				rate =arr.get(i).get("RATE").toString();
			}
			/*
			if(arr.get(i).get("TOT_AMOUNT")!=null){
				//user_tot_amount =arr.get(i).get("TOT_AMOUNT").toString();
				user_tot_amount =arr.get(i).get("TOT_AMOUNT").toString();
			}
			if(arr.get(i).get("PRACTICE_AMOUNT")!=null){
				//user_practice_amount =arr.get(i).get("PRACTICE_AMOUNT").toString();
				user_practice_amount =arr.get(i).get("PRACTICE_AMOUNT").toString();
			}
			if(arr.get(i).get("CALLPCT")!=null){
				callpct =arr.get(i).get("CALLPCT").toString();
			}
			if(arr.get(i).get("PUTPCT")!=null){
				putpct =arr.get(i).get("PUTPCT").toString();
			}
			if(arr.get(i).get("MISSION_MONEY")!=null){
				//mission_money =arr.get(i).get("MISSION_MONEY").toString();
				mission_money =arr.get(i).get("MISSION_MONEY").toString();
			}
			if(arr.get(i).get("INPUT_BONUS")!=null){
				//input_bonus = arr.get(i).get("INPUT_BONUS").toString();
				input_bonus = arr.get(i).get("INPUT_BONUS").toString();
			}
			if(arr.get(i).get("PROCESSED_BONUS")!=null){
				//processed_bonus = arr.get(i).get("PROCESSED_BONUS").toString();
				processed_bonus = arr.get(i).get("PROCESSED_BONUS").toString();
			}
			if(arr.get(i).get("TOT_AMOUNT")!=null){
				//tot_amount = arr.get(i).get("TOT_AMOUNT").toString();
				tot_amount = arr.get(i).get("TOT_AMOUNT").toString();
			}
			if(arr.get(i).get("PRACTICE_AMOUNT")!=null){
				//practice_amount = arr.get(i).get("PRACTICE_AMOUNT").toString();
				practice_amount = arr.get(i).get("PRACTICE_AMOUNT").toString();
			}
			*/
			obj.put("rate_key",rate_key);
			obj.put("ext_rate",rate);
			//obj.put("callpct",callpct);
			//obj.put("putpct",putpct);
			//obj.put("mission_money",mission_money);
			//obj.put("input_bonus",input_bonus);
			//obj.put("processed_bonus",processed_bonus);
			//obj.put("tot_amount",tot_amount);
			//obj.put("practice_amount",practice_amount);
			
			list.add(obj);
				
		}
		//obj.put("list",list);
		receiveobj.put("receivefx03",list);
	}else{
		receiveobj.put("receivefx03",null);
	}
	out.println(receiveobj);
	
%>

