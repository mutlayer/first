<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DecimalFormat df2 = new DecimalFormat("#.####");
	String login = (String)request.getAttribute("map");
	String currenTime = (String)request.getAttribute("currenTime");
	String amount_yn = (String)request.getAttribute("amount_yn");
	String recommend_id = (String)request.getAttribute("recommend_id");
	String bank_cd = (String)request.getAttribute("bank_cd");
	String bank_nm = (String)request.getAttribute("bank_nm");
	String bank_number = (String)request.getAttribute("bank_number");
	String phone_number = (String)request.getAttribute("phone_number");
	String successYN = (String)request.getAttribute("successYN");
	String result_cd = (String)request.getAttribute("result_Cd");
	String text = (String)request.getAttribute("text");
	String user_name = (String)request.getAttribute("user_name");
	

	String actkey="";
	String event="";
	String order_time;
	String comp_time;
	String invest_money="";
	String option_type = "";
	String start_rate="";
	String ext_rate="";
	String tot_amount="";
	String practice_amount="";
	String callput = "";
	
	String game_type = "";
	String last_event = "";
	String last_option_type = "";
	String last_tot_amount = "";
	
	String loginYN=login;
	
	String mission_money = "";
	if(request.getAttribute("mission_money")!=null){
		mission_money = request.getAttribute("mission_money").toString();
	}
	String processed_bonus = "";
	if(request.getAttribute("processed_bonus")!=null){
		processed_bonus = (String)request.getAttribute("processed_bonus").toString();
	}
	String input_bonus = "";
	if(request.getAttribute("input_bonus")!=null){
		input_bonus = (String)request.getAttribute("input_bonus").toString();
	}
	
	
	JSONArray list_info = new JSONArray();
	JSONArray list_last_info = new JSONArray();
	JSONObject obj = new JSONObject();
	
	//obj.put("loginYN",login);
	obj.put("result_cd",result_cd);
	obj.put("successYN",successYN);
	obj.put("text",text);
	obj.put("curTime",currenTime);
	obj.put("recommend_id",recommend_id);
	obj.put("bank_cd",bank_cd);
	obj.put("bank_nm",bank_nm);
	obj.put("bank_number",bank_number);
	obj.put("phone_number",phone_number);
	obj.put("user_name",user_name);
	obj.put("logindata",login);
	
	obj.put("MISSION_MONEY",mission_money);
	obj.put("INPUT_BONUS",input_bonus);
	obj.put("PROCESSED_BONUS",processed_bonus);
	
	if(login.equals("0")){
		//out.println("ID가 존재하지 않습니다.");
		obj.put("amount_yn",null);
		obj.put("info",null);
		obj.put("last_info",null);
		obj.put("decomplete_list",null);
	}else if(login.equals("9")){
		//out.println("패스워드가 5회 이상 틀렸습니다.");
		obj.put("amount_yn",null);
		obj.put("info",null);
		obj.put("last_info",null);
		obj.put("decomplete_list",null);
	}else if(login.equals("N")){
		//out.println("패스워드가 틀렸습니다.");
		obj.put("amount_yn",null);
		obj.put("info",null);
		obj.put("last_info",null);
		obj.put("decomplete_list",null);
	}else if(login.equals("Y")){
		obj.put("amount_yn",amount_yn);
		Map x = (Map)request.getAttribute("logindata");
		actkey = (String)x.get("ACTKEY");//키
		event = (String)x.get("EVENT");//종목
		order_time = dateFormat.format(x.get("ORDER_TIME"));//주문시간
		comp_time = dateFormat.format(x.get("COMP_TIME"));//결과시간
		invest_money = df2.format(x.get("INVEST_MONEY"));//투자금
		option_type = (String)x.get("OPTION_TYPE");//바이너리/터보
		start_rate = df2.format(x.get("START_RATE"));//시작가
		ext_rate = df2.format(x.get("EXT_RATE"));//이득률
		tot_amount = df2.format(x.get("TOT_AMOUNT"));//현재보유금
		practice_amount = df2.format(x.get("PRACTICE_AMOUNT"));//현재보유금
		callput = (String)x.get("CALLPUT");//콜풋
		if(actkey.equals("0")){
			
			
		}else{
			list_info.add(actkey);
			list_info.add(event);
			list_info.add(order_time);
			list_info.add(comp_time);
			list_info.add(invest_money);
			list_info.add(option_type);
			list_info.add(start_rate);
			list_info.add(ext_rate);
			list_info.add(tot_amount);
			list_info.add(practice_amount);
			list_info.add(callput);
		}
		
		obj.put("info",list_info);
		Map last_x = (Map)request.getAttribute("loginlastdata");
		if(last_x.size()!=0){
			System.out.println("5");
			System.out.println("last_x");
			System.out.println(last_x);
			game_type = (String)last_x.get("GAME_TYPE");//키
			last_event = (String)last_x.get("EVENT");//종목
			last_option_type = (String)last_x.get("OPTION_TYPE");//옵션타입
			last_tot_amount = df2.format(last_x.get("TOT_AMOUNT"));//현보유금액
			String last_rate = df2.format(last_x.get("RATE"));//현보유금액
			
			list_last_info.add(game_type);
			list_last_info.add(last_event);
			list_last_info.add(last_option_type);
			list_last_info.add(last_tot_amount);
			list_last_info.add(last_rate);
			obj.put("last_info",list_last_info);
		}else{
			obj.put("last_info",null);
		}
		/*
		List<Map<String,Object>> decom_arr = new ArrayList();
		decom_arr = (List<Map<String,Object>>)request.getAttribute("decomplete_list");
		
		if(decom_arr!=null){
			JSONArray decom_list = new JSONArray();
			for(int i=0;i<decom_arr.size();i++){
				
				JSONObject decom_obj = new JSONObject();
					
				String decom_actkey =(String)decom_arr.get(i).get("ACTKEY"); 
				String decom_user_id =(String) decom_arr.get(i).get("USER_ID");
				String decom_order_time =dateFormat.format(decom_arr.get(i).get("ORDER_TIME"));
				String decom_game_type =(String)decom_arr.get(i).get("GAME_TYPE");
				String decom_status = (String)decom_arr.get(i).get("STATUS");
				String decom_event = (String) decom_arr.get(i).get("EVENT");
				String decom_invest_money = decom_arr.get(i).get("INVEST_MONEY").toString();
				String decom_option_type = (String)decom_arr.get(i).get("OPTION_TYPE");
				String decom_callput = (String) decom_arr.get(i).get("CALLPUT");
				String decom_start_rate = decom_arr.get(i).get("START_RATE").toString();
				String decom_ext_rate = decom_arr.get(i).get("EXT_RATE").toString();
				
				decom_obj.put("actkey",decom_actkey);
				decom_obj.put("user_id",decom_user_id);
				decom_obj.put("order_time",decom_order_time);
				decom_obj.put("game_type",decom_game_type);
				decom_obj.put("status",decom_status);
				decom_obj.put("event",decom_event);
				decom_obj.put("invest_money",decom_invest_money);
				decom_obj.put("option_type",decom_option_type);
				decom_obj.put("callput",decom_callput);
				decom_obj.put("start_rate",decom_start_rate);
				decom_obj.put("ext_rate",decom_ext_rate);
				
				decom_list.add(decom_obj);
			}
			//obj.put("list",list);
			obj.put("decomplete_list",decom_list);
		}else{
			obj.put("decomplete_list",null);
		}
		*/
	}
	
	
	out.println(obj);
	
	
	
	
%>

