package first.forex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import first.common.util.FileUtils;
import first.forex.blueHoueLabSMS.Config;
import first.forex.dao.ForexDAO;

@Service("forexService")
public class ForexServiceImpl implements ForexService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Resource(name = "forexDAO")
	private ForexDAO forexDAO;

	private static String dep_phone_number = "01075415580";
	private static String ref_phone_number = "01075415580";

	@Override
	public List<Map<String, Object>> selectUserList(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");

		if (column == null) {
			column = "USER_NAME";
		}

		if (sort == null) {
			sort = "asc";
		}

		if (cur_page == null) {
			cur_page = "1";
		}

		map.put("cur_page", cur_page + "0");
		if (user_name != null) {
			if (user_name.trim().length() > 0) {
				map.put("user_name", user_name);
			} else {
				map.put("user_name", null);
			}
		} else {
			map.put("user_name", null);
		}
		if (user_id != null) {
			if (user_id.length() > 0) {
				map.put("user_id", user_id);
			} else {
				map.put("user_id", null);
			}
		} else {
			map.put("user_id", null);
		}

		if (column != null) {
			map.put("column", column + " " + sort);
		} else {
			map.put("column", null);
		}

		if (column != null) {
			map.put("sort", sort);
		} else {
			map.put("sort", "ASC");
		}

		return forexDAO.selectUserList(map);

	}

	@Override
	public List<Map<String, Object>> selectOrderList_pop(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("order_pop_user_id");
		// String input_id = request.getParameter("order_pop_user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");

		if (column == null) {
			column = "USER_NAME";
		}

		if (sort == null) {
			sort = "asc";
		}

		if (cur_page == null) {
			cur_page = "1";
		}

		char last_cur = cur_page.charAt(cur_page.length() - 1);

		int page_cnt = 20;
		// if(Integer.parseInt(cur_page)<10){
		page_cnt = Integer.parseInt(cur_page) * 2;
		// }
		cur_page = String.valueOf(page_cnt);

		// map.put("cur_page",cur_page+"0");
		map.put("cur_page", cur_page + "0");

		if (user_name != null) {
			if (user_name.trim().length() > 0) {
				map.put("user_name", user_name);
			} else {
				map.put("user_name", null);
			}
		} else {
			map.put("user_name", null);
		}
		if (user_id != null) {
			if (user_id.length() > 0) {
				map.put("user_id", user_id);
			} else {
				map.put("user_id", null);
			}
		} else {
			map.put("user_id", null);
		}

		if (column != null) {
			if (column.equals("user_id")) {
				map.put("column", "U.USER_ID" + " " + sort);
			} else {
				map.put("column", column + " " + sort);
			}
		} else {
			map.put("column", null);
		}

		if (column != null) {
			map.put("sort", sort);
		} else {
			map.put("sort", "ASC");
		}

		return forexDAO.selectOrderList(map);

	}

	@Override
	public List<Map<String, Object>> selectDepositList_pop(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("deposit_pop_user_id");
		// String input_id = request.getParameter("order_pop_user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");

		if (column == null) {
			column = "apply_date";
		}

		if (sort == null) {
			sort = "desc";
		}

		if (cur_page == null) {
			cur_page = "1";
		}

		char last_cur = cur_page.charAt(cur_page.length() - 1);

		int page_cnt = 20;
		// if(Integer.parseInt(cur_page)<10){
		page_cnt = Integer.parseInt(cur_page) * 2;
		// }
		cur_page = String.valueOf(page_cnt);

		// map.put("cur_page",cur_page+"0");
		map.put("cur_page", cur_page + "0");

		if (user_name != null) {
			if (user_name.trim().length() > 0) {
				map.put("user_name", user_name);
			} else {
				map.put("user_name", null);
			}
		} else {
			map.put("user_name", null);
		}
		if (user_id != null) {
			if (user_id.length() > 0) {
				map.put("user_id", user_id);
			} else {
				map.put("user_id", null);
			}
		} else {
			map.put("user_id", null);
		}

		if (column != null) {
			if (column.equals("user_id")) {
				map.put("column", "U.USER_ID" + " " + sort);
			} else {
				map.put("column", column + " " + sort);
			}
		} else {
			map.put("column", null);
		}

		if (column != null) {
			map.put("sort", sort);
		} else {
			map.put("sort", "ASC");
		}

		return forexDAO.selectDepositList(map);

	}

	@Override
	public List<Map<String, Object>> selectOrderList(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String input_id = request.getParameter("order_pop_user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");

		if (column == null) {
			column = "USER_NAME";
		}

		if (sort == null) {
			sort = "asc";
		}

		if (cur_page == null) {
			cur_page = "1";
		}

		char last_cur = cur_page.charAt(cur_page.length() - 1);

		int page_cnt = 20;
		// if(Integer.parseInt(cur_page)<10){
		page_cnt = Integer.parseInt(cur_page) * 2;
		// }
		cur_page = String.valueOf(page_cnt);

		// map.put("cur_page",cur_page+"0");
		map.put("cur_page", cur_page + "0");

		if (user_name != null) {
			if (user_name.trim().length() > 0) {
				map.put("user_name", user_name);
			} else {
				map.put("user_name", null);
			}
		} else {
			map.put("user_name", null);
		}
		if (user_id != null) {
			if (user_id.length() > 0) {
				map.put("user_id", user_id);
			} else {
				map.put("user_id", null);
			}
		} else {
			map.put("user_id", null);
		}

		if (column != null) {
			if (column.equals("user_id")) {
				map.put("column", "U.USER_ID" + " " + sort);
			} else {
				map.put("column", column + " " + sort);
			}
		} else {
			map.put("column", null);
		}

		if (column != null) {
			map.put("sort", sort);
		} else {
			map.put("sort", "ASC");
		}

		return forexDAO.selectOrderList(map);

	}

	@Override
	public List<Map<String, Object>> selectUserAccount(Map<String, Object> map) throws Exception {
		return forexDAO.selectUserAccount(map);

	}

	@Override
	public Map<String, Object> receive_bank(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		arr = forexDAO.receive_bank(map);

		if (arr.size() > 0) {
			resultMap.put("receive_bank", arr);
		} else {
			resultMap.put("receive_bank", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receivefx(Map<String, Object> map, HttpServletRequest request) throws Exception {

		// long time = System.currentTimeMillis()+60000;
		long time = curtime() + 60000;
		long cur_time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		String now_time = dayTime.format(new Date(time));
		String now_hh = hh.format(new Date(cur_time));
		map.put("comp_time", now_time);
		map.put("hh", now_hh);

		// forexDAO.updateHitCnt(map);
		// http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDKRW%22)&format=json&env=store://datatables.org/alltableswithkeys&callback=
		String user_id = request.getParameter("user_id");

		String option = request.getParameter("option");
		// String replaceoption = option.substring(0,3)+"_"+option.substring(3,
		// 6);
		// String url =
		// "https://api-fxtrade.oanda.com/v1/candles?instrument="+replaceoption+"&count=1&candleFormat=midpoint&granularity=D&dailyAlignment=0&alignmentTimezone=Asia%2FSeoul";

		/*
		 * String url =
		 * "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"+
		 * option+
		 * "%22)&format=json&env=store://datatables.org/alltableswithkeys&callback=";
		 * 
		 * String returnfx = ""; returnfx = urlCall(url);
		 * 
		 * 
		 * JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
		 * JSONObject fxdata_query = (JSONObject) jsonObject.get("query");
		 * String json_results = fxdata_query.get("results").toString();
		 * JSONObject jsonObject_results = (JSONObject) JSONValue
		 * .parse(json_results); JSONObject fxdata_rate = (JSONObject)
		 * jsonObject_results.get("rate"); String rate_id =
		 * fxdata_rate.get("id").toString(); String rate_name =
		 * fxdata_rate.get("Name").toString(); String rate_time =
		 * fxdata_rate.get("Time").toString(); String rate_date =
		 * fxdata_rate.get("Date").toString(); String rate_bid =
		 * fxdata_rate.get("Bid").toString(); String rate_ask =
		 * fxdata_rate.get("Ask").toString(); String rate_rate =
		 * fxdata_rate.get("Rate").toString();
		 */
		map.put("option", option);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("user_id", user_id);
		} else {
			resultMap.put("rate", null);
			resultMap.put("user_tot_amount", null);
			resultMap.put("user_practice_amount", null);
			resultMap.put("callpct", null);
			resultMap.put("putpct", null);
			resultMap.put("map", null);

			resultMap.put("rate_id", null);
			resultMap.put("rate_name", null);
			resultMap.put("rate_time", null);
			resultMap.put("rate_date", null);
			resultMap.put("rate_bid", null);
			resultMap.put("rate_ask", null);
			resultMap.put("rate_rate", null);

			resultMap.put("successYN", "N");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("result_cd", "01");
			return resultMap;
		}

		if (forexDAO.duplicate_user_id(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 아이디 입니다.");

			resultMap.put("rate", null);
			resultMap.put("user_tot_amount", null);
			resultMap.put("user_practice_amount", null);
			resultMap.put("callpct", null);
			resultMap.put("putpct", null);
			resultMap.put("map", null);

			resultMap.put("rate_id", null);
			resultMap.put("rate_name", null);
			resultMap.put("rate_time", null);
			resultMap.put("rate_date", null);
			resultMap.put("rate_bid", null);
			resultMap.put("rate_ask", null);
			resultMap.put("rate_rate", null);

			return resultMap;
		}

		Map<String, Object> rateInfoMap = forexDAO.selectCommonRate(map);

		if (rateInfoMap == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "43");
			resultMap.put("text", "지원하지 않는 종목 입니다.");

			
			resultMap.put("rate", null);
			resultMap.put("user_tot_amount", null);
			resultMap.put("user_practice_amount", null);
			resultMap.put("callpct", null);
			resultMap.put("putpct", null);
			resultMap.put("map", null);

			resultMap.put("rate_id", null);
			resultMap.put("rate_name", null);
			resultMap.put("rate_time", null);
			resultMap.put("rate_date", null);
			resultMap.put("rate_bid", null);
			resultMap.put("rate_ask", null);
			resultMap.put("rate_rate", null);

			return resultMap;
		}

		Map<String, Object> callputMap = forexDAO.selectCallPut_pct(map);
		BigDecimal rate = null;
		BigDecimal user_tot_amount = new BigDecimal("0");
		BigDecimal user_practice_amount = new BigDecimal("0");
		BigDecimal callcnt = new BigDecimal("0");
		BigDecimal putcnt = new BigDecimal("0");
		BigDecimal allcnt = new BigDecimal("0");
		BigDecimal callpct = new BigDecimal("0");
		BigDecimal putpct = new BigDecimal("0");
		try {
			if (callputMap != null) {
				callcnt = new BigDecimal(callputMap.get("CALLCNT").toString());
				putcnt = new BigDecimal(callputMap.get("PUTCNT").toString());
				allcnt = new BigDecimal(callputMap.get("ALLCNT").toString());
				callpct = callcnt.divide(allcnt, 3, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
				putpct = putcnt.divide(allcnt, 3, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
			} else {
				callcnt = new BigDecimal("0");
				putcnt = new BigDecimal("0");
				allcnt = new BigDecimal("0");
			}
		} catch (ArithmeticException e) {
			callpct = new BigDecimal("0");
			putpct = new BigDecimal("0");
		}
		if (rateInfoMap != null) {
			rate = (BigDecimal) rateInfoMap.get("RATE");
			user_tot_amount = (BigDecimal) rateInfoMap.get("TOT_AMOUNT");
			user_practice_amount = (BigDecimal) rateInfoMap.get("PRACTICE_AMOUNT");
		}
		
		Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);
		if (userBonusMap != null) {
			resultMap.put("mission_money", new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
			resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
			resultMap.put("processed_bonus", new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
		} else {
			resultMap.put("mission_money", null);
			resultMap.put("input_bonus", null);
			resultMap.put("processed_bonus", null);
		}

		resultMap.put("rate", rate);
		resultMap.put("user_tot_amount", user_tot_amount);
		resultMap.put("user_practice_amount", user_practice_amount);
		resultMap.put("callpct", callpct);
		resultMap.put("putpct", putpct);
		/*
		 * resultMap.put("map", returnfx);
		 * 
		 * 
		 * resultMap.put("rate_id", rate_id); resultMap.put("rate_name",
		 * rate_name); resultMap.put("rate_time", rate_time);
		 * resultMap.put("rate_date", rate_date); resultMap.put("rate_bid",
		 * rate_bid); resultMap.put("rate_ask", rate_ask);
		 * resultMap.put("rate_rate", rate_rate);
		 */
		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}
	
	@Override
	public Map<String, Object> receivefx03(Map<String, Object> map, HttpServletRequest request) throws Exception {

		long time = 0;
		
		long cur_time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		SimpleDateFormat mm = new SimpleDateFormat("mm");
		
		String now_hh = hh.format(new Date(cur_time));
		String now_mm = mm.format(new Date(cur_time));
		
		if(Integer.parseInt(now_mm)%2==0){
			time = curtime() + 1000*60*2;
		}else{
			time = curtime() + 1000*60;
		}
		
		
		String now_time = dayTime.format(new Date(time));
		
		map.put("comp_time", now_time);
		map.put("hh", now_hh);
		
		

		String user_id = request.getParameter("user_id");

		String option = request.getParameter("option");
		map.put("option", option);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("user_id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("result_cd", "01");
			return resultMap;
		}

		if (forexDAO.duplicate_user_id(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 아이디 입니다.");

			return resultMap;
		}

		List<Map<String, Object>> arr = new ArrayList();
		arr = forexDAO.receivefx03(map);
		
		if (arr.size() > 0) {
			resultMap.put("receivefx03", arr);
		} else {
			resultMap.put("receivefx03", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receivefx02(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		long time = curtime() + 60000;
		long cur_time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		String now_time = dayTime.format(new Date(time));
		String now_hh = hh.format(new Date(cur_time));
		map.put("comp_time", now_time);
		map.put("hh", now_hh);

		String user_id = request.getParameter("user_id");
		String option = request.getParameter("option");

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("user_id", user_id);
		} else {
			resultMap.put("rate", null);
			resultMap.put("user_tot_amount", null);
			resultMap.put("user_practice_amount", null);
			resultMap.put("callpct", null);
			resultMap.put("putpct", null);

			resultMap.put("successYN", "N");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("result_cd", "01");
			return resultMap;
		}

		if (forexDAO.duplicate_user_id(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 아이디 입니다.");

			resultMap.put("rate", null);
			resultMap.put("user_tot_amount", null);
			resultMap.put("user_practice_amount", null);
			resultMap.put("callpct", null);
			resultMap.put("putpct", null);

			return resultMap;
		}

		map.put("user_id", user_id);
		map.put("option", option);

		Map<String, Object> rateInfoMap = forexDAO.selectCommonRate(map);
		Map<String, Object> callputMap = forexDAO.selectCallPut_pct(map);

		BigDecimal rate = null;
		BigDecimal user_tot_amount = new BigDecimal("0");
		BigDecimal user_practice_amount = new BigDecimal("0");
		BigDecimal callcnt = new BigDecimal("0");
		BigDecimal putcnt = new BigDecimal("0");
		BigDecimal allcnt = new BigDecimal("0");
		BigDecimal callpct = new BigDecimal("0");
		BigDecimal putpct = new BigDecimal("0");
		try {
			if (callputMap != null) {
				callcnt = new BigDecimal(callputMap.get("CALLCNT").toString());
				putcnt = new BigDecimal(callputMap.get("PUTCNT").toString());
				allcnt = new BigDecimal(callputMap.get("ALLCNT").toString());
				callpct = callcnt.divide(allcnt, 3, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
				putpct = putcnt.divide(allcnt, 3, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
			} else {
				callcnt = new BigDecimal("0");
				putcnt = new BigDecimal("0");
				allcnt = new BigDecimal("0");
			}
		} catch (ArithmeticException e) {
			callpct = new BigDecimal("0");
			putpct = new BigDecimal("0");
		}
		if (rateInfoMap != null) {
			rate = (BigDecimal) rateInfoMap.get("RATE");
			user_tot_amount = (BigDecimal) rateInfoMap.get("TOT_AMOUNT");
			user_practice_amount = (BigDecimal) rateInfoMap.get("PRACTICE_AMOUNT");
		}

		resultMap.put("rate", rate);
		resultMap.put("user_tot_amount", user_tot_amount);
		resultMap.put("user_practice_amount", user_practice_amount);
		resultMap.put("callpct", callpct);
		resultMap.put("putpct", putpct);
		// resultMap.put("map", returnfx);
		// resultMap.put("rate_rate",rate_rate);

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> selectUserDetail(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// forexDAO.updateHitCnt(map);
		String user_id = request.getParameter("pop_user_id");
		map.put("user_id", user_id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = forexDAO.selectUserDetail(map);
		resultMap.put("map", tempMap);

		// List<Map<String,Object>> list = forexDAO.selectFileList(map);
		// resultMap.put("list", list);

		return resultMap;
	}

	@Override
	public Map<String, Object> selectUserDetail_pop(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		// forexDAO.updateHitCnt(map);
		String user_id = request.getParameter("pop_user_id");
		map.put("user_id", user_id);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = forexDAO.selectUserDetail_pop(map);
		resultMap.put("map", tempMap);

		return resultMap;
	}

	@Override
	public Map<String, Object> apply_refund_second(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String client_ip = clientIp();
		map.put("client_ip", client_ip);
		String user_id = null;
		String user_name = null;
		String phone_number = null;
		String confirm_number = null;
		String key = null;
		BigDecimal apply_money = null;

		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_time = dayTime.format(new Date(time));
		map.put("confirm_time", confirm_time);

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("result_cd", "01");
			return resultMap;
		}
		/*
		 * if(request.getParameter("user_name")!=null) { String input_name =
		 * request.getParameter("user_name"); user_name = new
		 * String(input_name.getBytes("iso-8859-1"), "utf-8"); //user_name =
		 * request.getParameter("user_name"); map.put("user_name", user_name);
		 * }else{ resultMap.put("successYN", "N"); resultMap.put("text",
		 * "user_name is null"); return resultMap; }
		 */

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");
			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("text", "전화번호를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("confirm_number") != null) {
			confirm_number = request.getParameter("confirm_number");
			map.put("confirm_number", confirm_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "26");
			resultMap.put("text", "인증코드가 없습니다.");
			return resultMap;
		}

		BigDecimal possible_money = new BigDecimal("0");

		if (forexDAO.select_possible_money(map) != null) {
			possible_money = new BigDecimal(forexDAO.select_possible_money(map).toString());
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "29");
			resultMap.put("text", "출금 가능금액이 없습니다.");
			return resultMap;
		}

		if (request.getParameter("apply_money") != null) {
			apply_money = new BigDecimal(request.getParameter("apply_money").toString());
			if (apply_money.compareTo(new BigDecimal("0")) != 1) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "30");
				resultMap.put("text", "요청금액은 0보다 커야 합니다.");
				return resultMap;
			}
			map.put("apply_money", apply_money);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "요청 금액을 입력하세요.");
			resultMap.put("result_cd", "31");
			return resultMap;
		}

		if (apply_money.compareTo(possible_money) > 0) {
			map.put("abnormal_cd", "32");
			map.put("abnormal_desc", "출금요청 금액이 출금가능 금액보다 큽니다.");
			forexDAO.insert_abnormal_his(map);
			resultMap.put("successYN", "N");
			resultMap.put("text", "출금요청 금액이 출금가능 금액보다 큽니다.");
			resultMap.put("result_cd", "32");
			return resultMap;
		}

		String account_number = null;

		if (forexDAO.select_account_number(map) != null) {
			account_number = forexDAO.select_account_number(map);
			map.put("account_number", account_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "조회되는 계좌정보가 없습니다.");
			resultMap.put("result_cd", "33");
			return resultMap;
		}

		// String term = request.getParameter("TERM");

		// long limit_time = time+1000*60*Integer.parseInt(term.toString());
		SimpleDateFormat input_time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String date_time = input_time.format(new Date(time));

		String actkey = user_id + apply_money + "refund" + date_time;
		map.put("actkey", actkey);

		if (forexDAO.select_confirm_number(map) != null && forexDAO.select_confirm_number(map).toString().equals("Y")) {

			// 인증성공처리
			try {
				forexDAO.update_confirm(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "27");
				resultMap.put("text", "update tb_phone_confirm Exception");
				return resultMap;
			}
			try {
				forexDAO.insert_refund(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "36");
				resultMap.put("text", "insert tb_refund Exception");
				return resultMap;
			}

			String minus_bonus = forexDAO.select_minus_bonus(map);
			BigDecimal dec_minus_bonus = new BigDecimal(minus_bonus);
			map.put("minus_bonus", dec_minus_bonus);

			try {
				map.put("actkey", "출금신청");
				forexDAO.update_amount_refund(map);
				forexDAO.update_amount_refund_admin(map);
				forexDAO.update_bonus_refund_c(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "44");
				resultMap.put("text", "update tb_user_amount Exception by refund" + e);
				return resultMap;
			}
			try {
				Config.receiver = dep_phone_number;
				Config.sender = "01084034999";
				Config.content = "[fx-eve] " + user_id + "님 출금요청 [" + map.get("apply_money") + "]원";
				sendsms();
			} catch (Exception e) {
				log.debug(e);
			}

			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "56");
			resultMap.put("text", "인증성공 출금신청 되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("D")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "99");
			resultMap.put("text", "인증시간이초과되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("N")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "98");
			resultMap.put("text", "인증번호가 틀렸습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "97");
			resultMap.put("text", "인증번호를 확인할수 없습니다. 다시 진행 하세요.");
			return resultMap;
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> join_second_confirm(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String client_ip = clientIp();
		map.put("client_ip", client_ip);
		String user_id = null;
		String user_name = null;
		String bank_cd = null;
		String account_number = null;
		String phone_number = null;
		String confirm_number = null;
		String key = null;

		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_time = dayTime.format(new Date(time));
		map.put("confirm_time", confirm_time);

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("user_name") != null) {
			String input_name = request.getParameter("user_name");
			user_name = new String(input_name.getBytes("iso-8859-1"), "utf-8");
			// user_name = request.getParameter("user_name");
			map.put("user_name", user_name);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "37");
			resultMap.put("text", "이름을 입력하세요");
			return resultMap;
		}

		if (request.getParameter("bank_cd") != null) {
			bank_cd = request.getParameter("bank_cd");
			map.put("bank_cd", bank_cd);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "38");
			resultMap.put("text", "은행코드를 선택하세요");
			return resultMap;
		}

		if (request.getParameter("account_number") != null) {
			account_number = request.getParameter("account_number");
			map.put("account_number", account_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "39");
			resultMap.put("text", "계좌번호를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");
			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("text", "전화번호를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("confirm_number") != null) {
			confirm_number = request.getParameter("confirm_number");
			map.put("confirm_number", confirm_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "26");
			resultMap.put("text", "인증코드가 없습니다.");
			return resultMap;
		}

		if (forexDAO.select_confirm_number(map) != null && forexDAO.select_confirm_number(map).toString().equals("Y")) {

			// 인증성공처리
			try {
				forexDAO.update_confirm(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "27");
				resultMap.put("text", e);
				return resultMap;
			}
			try {
				forexDAO.update_confirm_tb_user(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "28");
				resultMap.put("text", e);
				return resultMap;
			}

			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "55");
			resultMap.put("text", "인증성공 계좌 등록 되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("D")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "99");
			resultMap.put("text", "인증시간이초과되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("N")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "98");
			resultMap.put("text", "인증번호가 틀렸습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "97");
			resultMap.put("text", "인증번호를 확인할수 없습니다. 다시 진행 하세요.");
			return resultMap;
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> join_second(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

		String client_ip = clientIp();
		String user_id = null;
		String user_name = null;
		String bank_cd = null;
		String account_number = null;
		String phone_number = null;
		String key = null;

		map.put("client_ip", client_ip);

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("id", user_id);
			map.put("user_id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}
		/*
		 * if(request.getParameter("user_pwd")!=null) { user_pwd =
		 * request.getParameter("user_pwd"); map.put("pw", user_pwd); }else{
		 * resultMap.put("successYN", "N"); resultMap.put("confirm_number", "");
		 * resultMap.put("text", "패스워드를 입력하세요."); return resultMap; }
		 */
		if (forexDAO.loginFlag02(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 ID 입니다.");
			return resultMap;
		}
		Map<String, Object> tempMap = forexDAO.loginFlag02(map);

		String amount_yn = "N";

		if (forexDAO.amount_flag(map) != null) {
			amount_yn = "Y";
		}
		/*
		 * if((tempMap.get("UPDATEDATE")!=null&&tempMap.get("INSERTDATE")!=
		 * tempMap.get("UPDATEDATE"))||tempMap.get("UPDATEDATE")!=null){
		 * amount_yn="Y"; }
		 */

		if (amount_yn.equals("Y")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "40");
			resultMap.put("text", "등록된 계좌가 존재합니다.");
			return resultMap;
		}

		if (request.getParameter("user_name") != null) {
			String input_name = request.getParameter("user_name");
			user_name = new String(input_name.getBytes("iso-8859-1"), "utf-8");

			/*
			 * String [] charSet =
			 * {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"}; for
			 * (int i=0; i<charSet.length; i++) { for (int j=0;
			 * j<charSet.length; j++) { try { System.out.println(i+","+j+"[" +
			 * charSet[i] +"," + charSet[j] +"] = " + new
			 * String(user_name.getBytes(charSet[i]), charSet[j])); } catch
			 * (UnsupportedEncodingException e) { e.printStackTrace(); } } }
			 */
			map.put("user_name", user_name);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "37");
			resultMap.put("text", "이름을 입력하세요.");
			return resultMap;
		}

		if (request.getParameter("bank_cd") != null) {
			bank_cd = request.getParameter("bank_cd");
			map.put("bank_cd", bank_cd);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "38");
			resultMap.put("text", "은행코드를 선택하세요.");
			return resultMap;
		}

		if (request.getParameter("account_number") != null) {
			account_number = request.getParameter("account_number");

			account_number = account_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", account_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "41");
				resultMap.put("text", "계좌번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("account_number", account_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "39");
			resultMap.put("text", "계좌번호를 입력하세요.");
			return resultMap;
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");

			phone_number = phone_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", phone_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "08");
				resultMap.put("text", "전화번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("text", "전화번호를 입력하세요.");
			return resultMap;
		}

		if (tempMap.get("USER_ID") == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "존재하지 않는 ID 입니다.");
			return resultMap;
		}

		/*
		 * String loginyn = forexDAO.loginyn(map); if(loginyn==null){
		 * resultMap.put("successYN", "N"); resultMap.put("confirm_number", "");
		 * resultMap.put("text", "존재하지 않는 ID 입니다."); return resultMap; }
		 */
		/*
		 * if(loginyn.equals("1")){ resultMap.put("successYN", "N");
		 * resultMap.put("confirm_number", ""); resultMap.put("text",
		 * "패스워드가 틀립니다."); return resultMap; }else{
		 * 
		 * }
		 */
		Random random = new Random();
		int confirm_number1 = random.nextInt(10);
		int confirm_number2 = random.nextInt(10);
		int confirm_number3 = random.nextInt(10);
		int confirm_number4 = random.nextInt(10);

		String confirm_number = String.valueOf(confirm_number1) + String.valueOf(confirm_number2)
				+ String.valueOf(confirm_number3) + String.valueOf(confirm_number4);
		map.put("confirm_number", confirm_number);

		long time = curtime() + (1000 * 60 * 3);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_end_time = dayTime.format(new Date(time));
		map.put("confirm_end_time", confirm_end_time);

		try {
			forexDAO.insert_phone_confirm(map);
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "14");
			resultMap.put("text", e);

			return resultMap;
		}

		try {
			Config.receiver = phone_number;
			Config.sender = "01084034999";
			Config.content = "[fx-eve] 본인확인 인증번호[" + confirm_number + "]를 화면에 입력해주세요.";
			sendsms();
		} catch (Exception e) {
			log.debug(e);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "51");
		resultMap.put("text", "인증번호가 전송되었습니다.");

		return resultMap;

	}

	@Override
	public Map<String, Object> apply_deposit(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String user_id = null;
		BigDecimal apply_money = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("user_id", user_id);
			map.put("id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		List<Map<String, Object>> arr02 = new ArrayList();
		arr02 = forexDAO.deposit_info(map);

		if (request.getParameter("apply_deposit") != null) {
			String apply_deposit = request.getParameter("apply_deposit");
			String foryn = null;
			for (int i = 0; i < arr02.size(); i++) {
				// if(arr02.get(i).get("IDX").equals(apply_deposit)){
				if (new BigDecimal(apply_deposit).compareTo(new BigDecimal(arr02.get(i).get("IDX").toString())) == 0) {
					map.put("apply_deposit", apply_deposit);
					map.put("apply_money", new BigDecimal(arr02.get(i).get("DEPOSIT_MONEY").toString()));
					map.put("deposit_bonus", new BigDecimal(arr02.get(i).get("DEPOSIT_MONEY").toString()).multiply(
							new BigDecimal(arr02.get(i).get("BONUS_PER").toString()).multiply(new BigDecimal("0.01"))));
					foryn = "Y";
				}
			}
			if (foryn == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "60");
				resultMap.put("text", "요청금액코드가 올바르지 않습니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "31");
			resultMap.put("text", "요청금액을 입력하세요");
			return resultMap;
		}
		/*
		 * if(request.getParameter("apply_deposit")!=null){ String apply_deposit
		 * = request.getParameter("apply_deposit"); map.put("apply_deposit",
		 * apply_deposit); apply_money = forexDAO.select_deposit(map);
		 * 
		 * map.put("apply_money", apply_money); }else{
		 * resultMap.put("successYN", "N"); resultMap.put("result_cd", "31");
		 * resultMap.put("text", "요청금액을 입력하세요"); return resultMap; }
		 */
		String account_number = forexDAO.select_account_number(map);
		if (account_number != null) {
			account_number = forexDAO.select_account_number(map);
			map.put("account_number", account_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "33");
			resultMap.put("text", "조회되는 계좌정보가 없습니다.");
			return resultMap;
		}

		BigDecimal deposit_bonus = null;
		// deposit_bonus = apply_money.multiply(new BigDecimal("0.1"));
		// map.put("deposit_bonus",deposit_bonus);

		try {
			forexDAO.insert_deposit(map);
			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "57");
			resultMap.put("text", "입금요청 되었습니다.");

			try {
				Config.receiver = dep_phone_number;
				Config.sender = "01084034999";
				Config.content = "[fx-eve] " + user_id + "님 입금요청 [" + map.get("apply_money") + "]원";
				sendsms();
			} catch (Exception e) {
				log.debug(e);
			}

			return resultMap;
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "42");
			resultMap.put("text", e);
			return resultMap;
		}

	}

	@Override
	public Map<String, Object> apply_refund(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String user_id = null;
		String user_name = null;
		BigDecimal apply_money = null;
		String phone_number = null;
		String key = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("id", user_id);
			map.put("user_id", user_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}
		/*
		 * if(request.getParameter("user_name")!=null){ user_name =
		 * request.getParameter("user_name").toString(); map.put("user_name",
		 * user_name); }else{ resultMap.put("successYN", "N");
		 * resultMap.put("confirm_number", ""); resultMap.put("text",
		 * "name is null"); return resultMap; }
		 */
		if (request.getParameter("key") != null) {
			key = request.getParameter("key").toString();
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number").toString();
			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}

		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "전화번호를 입력하세요");
			resultMap.put("result_cd", "07");
			return resultMap;
		}

		BigDecimal possible_money = new BigDecimal("0");

		if (forexDAO.select_possible_money(map) != null) {
			possible_money = new BigDecimal(forexDAO.select_possible_money(map).toString());
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "출금 가능금액이 없습니다.");
			resultMap.put("result_cd", "29");
			return resultMap;
		}

		if (request.getParameter("apply_money") != null) {
			apply_money = new BigDecimal(request.getParameter("apply_money").toString());
			if (apply_money.compareTo(new BigDecimal("0")) != 1) {
				resultMap.put("successYN", "N");
				resultMap.put("text", "요청금액은 0보다 커야 합니다.");
				resultMap.put("result_cd", "30");
				return resultMap;
			}
			map.put("apply_money", apply_money);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "요청 금액을 입력하세요");
			resultMap.put("result_cd", "31");
			return resultMap;
		}

		if (apply_money.compareTo(possible_money) > 0) {
			map.put("abnormal_cd", "32");
			map.put("abnormal_desc", "출금요청 금액이 출금가능 금액보다 큽니다.");
			map.put("client_ip", clientIp());
			forexDAO.insert_abnormal_his(map);

			resultMap.put("successYN", "N");
			resultMap.put("text", "출금요청 금액이 출금가능 금액보다 큽니다.");
			resultMap.put("result_cd", "32");
			return resultMap;
		}

		String account_number = null;
		if (forexDAO.select_account_number(map) != null) {
			account_number = forexDAO.select_account_number(map);
			map.put("account_number", account_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("text", "조회되는 계좌정보가 없습니다.");
			resultMap.put("result_cd", "33");
			return resultMap;
		}

		Random random = new Random();
		int confirm_number1 = random.nextInt(10);
		int confirm_number2 = random.nextInt(10);
		int confirm_number3 = random.nextInt(10);
		int confirm_number4 = random.nextInt(10);

		String confirm_number = String.valueOf(confirm_number1) + String.valueOf(confirm_number2)
				+ String.valueOf(confirm_number3) + String.valueOf(confirm_number4);
		map.put("confirm_number", confirm_number);

		long time = curtime() + (1000 * 60 * 3);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_end_time = dayTime.format(new Date(time));
		map.put("confirm_end_time", confirm_end_time);

		try {
			forexDAO.insert_phone_confirm(map);
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("text", e);
			resultMap.put("result_cd", "14");
			return resultMap;
		}

		try {
			Config.receiver = phone_number;
			Config.sender = "01084034999";
			Config.content = "[fx-eve] 본인확인 인증번호[" + confirm_number + "]를 화면에 입력해주세요.";
			sendsms();

			resultMap.put("successYN", "Y");
			resultMap.put("text", "인증번호가 전송 되었습니다.");
			resultMap.put("result_cd", "51");

			return resultMap;
		} catch (Exception e) {
			log.debug(e);

			resultMap.put("successYN", "Y");
			resultMap.put("text", e);
			resultMap.put("result_cd", "34");
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> join_first_confirm(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String client_ip = clientIp();
		String user_id = null;
		String user_pwd = null;
		String recommend_id = null;
		String phone_number = null;
		String confirm_number = null;
		String key = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
		}
		if (request.getParameter("user_pwd") != null) {
			user_pwd = request.getParameter("user_pwd");
		}
		if (request.getParameter("recommend_id") != null) {
			recommend_id = request.getParameter("recommend_id");
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");
		}

		if (request.getParameter("confirm_number") != null) {
			confirm_number = request.getParameter("confirm_number");
		}

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		map.put("user_id", user_id);
		map.put("user_pwd", user_pwd);
		map.put("recommend_id", recommend_id);
		map.put("client_ip", client_ip);
		map.put("phone_number", phone_number);
		map.put("confirm_number", confirm_number);

		if (forexDAO.duplicate_user_id(map) != null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "03");
			resultMap.put("text", "이미존재하는 아이디 입니다.");

			return resultMap;
		}

		if (user_id != null) {
			if (user_id.length() > 10 && user_id.length() < 3) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "02");
				resultMap.put("text", "아이디는 3 ~ 10 자리 입니다.");

				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");

			return resultMap;
		}

		if (user_pwd != null) {
			boolean bool1 = Pattern.matches("(.*[A-Za-z].*)", user_pwd);
			boolean bool2 = Pattern.matches("(.*[0-9].*)", user_pwd);

			if (bool1 && bool2) {
				if (user_pwd.length() < 8 || user_pwd.length() > 13) {
					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "05");
					resultMap.put("text", "패스워드는 8 ~ 13 자리 입니다.");

					return resultMap;
				}
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "06");
				resultMap.put("text", "패스워드는 알파벳,숫자 조합입니다.");

				return resultMap;
			}

		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "04");
			resultMap.put("text", "패스워드를 입력하세요");

			return resultMap;
		}

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");

			phone_number = phone_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", phone_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "08");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "전화번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("phone_number", phone_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "전화번호를 입력하세요.");
			return resultMap;
		}

		if (forexDAO.duplicate_phone_number(map) != null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "09");
			resultMap.put("text", "이미 가입된 전화번호 입니다.");

			return resultMap;
		}
		/*
		 * if(recommend_id!=null){ String recommend = null;
		 * if(forexDAO.select_recommend(map)!=null){ recommend =
		 * forexDAO.select_recommend(map); }else{ resultMap.put("successYN",
		 * "N"); resultMap.put("result_cd", "07"); resultMap.put("text",
		 * "존재하지 않는 추천인 아이디 입니다.");
		 * 
		 * return resultMap; }
		 * 
		 * }else{ resultMap.put("successYN", "N"); resultMap.put("result_cd",
		 * "10"); resultMap.put("text", "추천인 아이디를 입력하세요");
		 * 
		 * return resultMap; }
		 */
		if (recommend_id != null) {
			String recommend = forexDAO.select_recommend02(map);
			if (recommend != null) {
				map.put("recommend", recommend);
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "11");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "존재하지 않는 추천인 아이디 입니다.");

				return resultMap;
			}

		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "10");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "추천인 아이디가 없습니다.");

			return resultMap;
		}

		if (request.getParameter("user_pwd") != null) {
			String encrypt_pw = "";
			encrypt_pw = forexDAO.encrypt_pw(map).toString();

			map.put("password", encrypt_pw);
		}

		if (forexDAO.select_confirm_number(map) != null && forexDAO.select_confirm_number(map).toString().equals("Y")) {

			// 인증성공처리
			try {
				forexDAO.join_first(map);
				// resultMap.put("successYN", "Y");
				// resultMap.put("text", "가입성공");

				// return resultMap;
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "12");
				resultMap.put("text", e);

				return resultMap;
			}

			try {
				forexDAO.join_first_amount(map);
				forexDAO.join_first_sp_recom(map);
				resultMap.put("successYN", "Y");
				resultMap.put("result_cd", "50");
				resultMap.put("text", "회원가입 되었습니다.");

				return resultMap;
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "13");
				resultMap.put("text", e);

				return resultMap;
			}

		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("D")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "99");
			resultMap.put("text", "인증시간이초과되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("N")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "98");
			resultMap.put("text", "인증번호가 틀렸습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "97");
			resultMap.put("text", "인증번호를 확인할수 없습니다. 다시 진행 하세요.");
			return resultMap;
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> join_first(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String client_ip = clientIp();
		String user_id = null;
		String user_pwd = null;
		String recommend_id = null;
		String phone_number = null;
		String key = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
		}
		if (request.getParameter("user_pwd") != null) {
			user_pwd = request.getParameter("user_pwd");
		}
		// if(request.getParameter("user_pwd02")!=null){
		// user_pwd02 = request.getParameter("user_pwd02");
		// }
		if (request.getParameter("recommend_id") != null) {
			recommend_id = request.getParameter("recommend_id");
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");
		}

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
		}

		map.put("user_id", user_id);
		map.put("id", user_id);
		map.put("user_pwd", user_pwd);
		map.put("recommend_id", recommend_id);
		map.put("client_ip", client_ip);
		map.put("phone_number", phone_number);
		map.put("keyvalue", key);

		if (forexDAO.duplicate_user_id(map) != null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "03");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "이미 존재하는 아이디 입니다.");

			return resultMap;
		}

		if (user_id != null) {
			if (user_id.length() > 10 && user_id.length() < 3) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "02");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "아이디는 3 ~ 10 자리 입니다.");

				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "아이디를 입력하세요");

			return resultMap;
		}

		if (user_pwd != null) {
			// Pattern p = Pattern.compile("([a-zA-Z0-9])|([0-9a-zA-Z])");
			// Matcher m = p.matcher(user_pwd);
			// log.debug(m.find());
			// if( m.find()){
			// if(Pattern.matches("/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9])$/",
			// user_pwd)){
			// /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}/

			boolean bool1 = Pattern.matches("(.*[A-Za-z].*)", user_pwd);
			boolean bool2 = Pattern.matches("(.*[0-9].*)", user_pwd);

			if (bool1 && bool2) {
				if (user_pwd.length() < 8 || user_pwd.length() > 13) {
					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "05");
					resultMap.put("confirm_number", "");
					resultMap.put("text", "패스워드는 8 ~ 13 자리 입니다.");

					return resultMap;
				}
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "06");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "패스워드는 알파벳,숫자 조합입니다.");

				return resultMap;
			}

		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "04");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "패스워드를 입력하세요");

			return resultMap;
		}

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");

			phone_number = phone_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", phone_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "08");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "전화번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("phone_number", phone_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "전화번호를 입력하세요.");
			return resultMap;
		}

		if (forexDAO.duplicate_phone_number(map) != null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "09");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "이미 존재하는 전화번호 입니다.");

			return resultMap;
		}
		/*
		 * if(recommend_id!=null){ String recommend = null;
		 * if(forexDAO.select_recommend(map)!=null){ recommend =
		 * forexDAO.select_recommend(map); }else{ resultMap.put("successYN",
		 * "N"); resultMap.put("result_cd", "11");
		 * resultMap.put("confirm_number", ""); resultMap.put("text",
		 * "존재하지 않는 추천인 아이디 입니다.");
		 * 
		 * return resultMap; }
		 * 
		 * }else{ resultMap.put("successYN", "N"); resultMap.put("result_cd",
		 * "10"); resultMap.put("confirm_number", ""); resultMap.put("text",
		 * "추천인 아이디가 없습니다.");
		 * 
		 * return resultMap; }
		 */

		if (recommend_id != null) {
			String recommend = forexDAO.select_recommend02(map);
			if (recommend != null) {
				map.put("recommend", recommend);
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "11");
				resultMap.put("confirm_number", "");
				resultMap.put("text", "존재하지 않는 추천인 아이디 입니다.");

				return resultMap;
			}

		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "10");
			resultMap.put("confirm_number", "");
			resultMap.put("text", "추천인 아이디가 없습니다.");

			return resultMap;
		}

		Random random = new Random();
		int confirm_number1 = random.nextInt(10);
		int confirm_number2 = random.nextInt(10);
		int confirm_number3 = random.nextInt(10);
		int confirm_number4 = random.nextInt(10);

		String confirm_number = String.valueOf(confirm_number1) + String.valueOf(confirm_number2)
				+ String.valueOf(confirm_number3) + String.valueOf(confirm_number4);
		map.put("confirm_number", confirm_number);

		long time = curtime() + (1000 * 60 * 3);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_end_time = dayTime.format(new Date(time));
		map.put("confirm_end_time", confirm_end_time);

		try {
			forexDAO.insert_phone_confirm(map);
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "14");
			resultMap.put("confirm_number", "");
			resultMap.put("text", e);

			return resultMap;
		}

		try {
			Config.receiver = phone_number;
			Config.sender = "01084034999";
			Config.content = "[fx-eve] 본인확인 인증번호[" + confirm_number + "]를 화면에 입력해주세요.";
			sendsms();
		} catch (Exception e) {
			log.debug(e);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "51");
		resultMap.put("confirm_number", confirm_number);
		resultMap.put("text", "인증번호가 전송 되었습니다.");

		// Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",user_email.trim());
		// Pattern.matches("[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*",user_name)
		// Pattern.matches("^[0-9]*$",user_phone_number)

		/*
		 * if(request.getParameter("user_pwd")!=null){ String encrypt_pw = "";
		 * encrypt_pw = forexDAO.encrypt_pw(map).toString();
		 * 
		 * map.put("password", encrypt_pw); }
		 * 
		 * try{ forexDAO.join_first(map); //resultMap.put("successYN", "Y");
		 * //resultMap.put("text", "가입성공");
		 * 
		 * //return resultMap; }catch(Exception e){ resultMap.put("successYN",
		 * "N"); resultMap.put("result_cd", "09"); resultMap.put("text", e);
		 * 
		 * return resultMap; }
		 * 
		 * try{ forexDAO.join_first_amount(map); resultMap.put("successYN",
		 * "Y"); resultMap.put("result_cd", "Y"); resultMap.put("text", "가입성공");
		 * 
		 * return resultMap; }catch(Exception e){ resultMap.put("successYN",
		 * "N"); resultMap.put("result_cd", "10"); resultMap.put("text", e);
		 * 
		 * return resultMap; }
		 */
		return resultMap;
	}

	@Override
	public Map<String, Object> loginFlag(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, Object>> arr = new ArrayList();

		// long time = System.currentTimeMillis();
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		SimpleDateFormat mmdd = new SimpleDateFormat("MM-dd");
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		SimpleDateFormat oc_time = new SimpleDateFormat("HH:mm:ss");
		String now_time = dayTime.format(new Date(time));
		String now_hh = hh.format(new Date(time));
		map.put("hh", now_hh);

		// 개장, 폐장
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 0 1 2 3 4 5 6
		// final String[] week = { "SUN", "MON", "TUE", "WED", "THU", "FRI",
		// "SAT" };
		final int[] week = { 0, 1, 2, 3, 4, 5, 6 };

		int yoil = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
		String wolil = mmdd.format(new Date(time));
		String il = dd.format(new Date(time));
		String cur_time = oc_time.format(new Date(time));
		java.util.Date cur_time02 = oc_time.parse(cur_time);
		int wolil02 = Integer.parseInt(wolil.replace("-", ""));

		List<Map<String, Object>> oc_arr = new ArrayList();
		oc_arr = forexDAO.select_openclose(map);

		if (oc_arr != null) {
			for (int i = 0; i < oc_arr.size(); i++) {
				if ("W".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= yoil
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= yoil) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "01");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

				if ("Y".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= wolil02
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= wolil02) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "01");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

				if ("M".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= wolil02
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= wolil02) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "01");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

			}
		}

		String input_id = request.getParameter("id");
		String input_pw = request.getParameter("pw");

		if (input_id != null) {
			map.put("id", input_id);
			map.put("user_id", input_id);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("login", "01");
			return resultMap;
		}

		if (input_pw != null) {
			map.put("pw", input_pw);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "04");
			resultMap.put("text", "패스워드를 입력하세요");
			resultMap.put("login", "01");
			return resultMap;
		}

		String loginyn = forexDAO.loginyn(map);

		Map<String, Object> loginMap = new HashMap<String, Object>();
		Map<String, Object> loginInfo = new HashMap<String, Object>();
		Map<String, Object> loginLastInfo = new HashMap<String, Object>();
		Map<String, Object> loginLastInfo_n = new HashMap<String, Object>();
		Map<String, Object> tempMap = forexDAO.loginFlag(map);
		// List<Map<String,Object>> list = sampleDAO.loginFlag(map);

		if (tempMap != null) {
			// log.debug(tempMap.get("USER_PWD"));
			String out_pwd = (String) tempMap.get("USER_PWD");
			int login_fail_cnt = Integer.parseInt(tempMap.get("LOGIN_FAIL_CNT").toString());
			String del_yn = (String) tempMap.get("DEL_YN");
			if (del_yn.equals("Y")) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "65");
				resultMap.put("text", "탈퇴 회원입니다.");
				resultMap.put("login", "65");
				return resultMap;
			}
			
			if((String)tempMap.get("TELBLOCK")!=null ||(String)tempMap.get("IPBLOCK")!=null){			
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "65");
				resultMap.put("text", "차단된 사용자 입니다.");
				resultMap.put("login", "65");
				return resultMap;
			}

			
			String amount_yn = "N";

			if (forexDAO.amount_flag(map) != null) {
				amount_yn = "Y";
			}
			// if((tempMap.get("UPDATEDATE")!=null&&tempMap.get("INSERTDATE")!=tempMap.get("UPDATEDATE"))||tempMap.get("UPDATEDATE")!=null){
			// amount_yn="Y";
			// }

			Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);
			if (userBonusMap != null) {
				resultMap.put("mission_money", new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
				resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
				resultMap.put("processed_bonus", new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
			} else {
				resultMap.put("mission_money", null);
				resultMap.put("input_bonus", null);
				resultMap.put("processed_bonus", null);
			}

			if (login_fail_cnt < 5) {
				// if (input_pw.equals(out_pwd)) {
				if (loginyn.equals("1")) {
					forexDAO.update_loginSuccess(map);
					loginMap = forexDAO.loginData(map);
					loginInfo = forexDAO.loginInfo(map);
					loginLastInfo = forexDAO.loginLastInfo(map);
					loginLastInfo_n = forexDAO.loginLastInfo_n(map);
					// arr = forexDAO.decomplete_bet(map);
					// if(arr.size()>0){
					// resultMap.put("decomplete_list", arr);
					// }else{
					// resultMap.put("decomplete_list", null);
					// }
					if (loginMap != null) {
						resultMap.put("map", loginMap);
					} else {
						resultMap.put("map", loginInfo);
					}

					if (loginLastInfo != null) {
						resultMap.put("last_map", loginLastInfo);
					} else {
						resultMap.put("last_map", loginLastInfo_n);
					}
					map.put("now_time", now_time);
					resultMap.put("successYN", "Y");
					resultMap.put("result_cd", "52");
					resultMap.put("text", "로그인 성공");
					resultMap.put("login", "Y");
					resultMap.put("amount_yn", amount_yn);
					resultMap.put("recommend_id", tempMap.get("RECOMMEND_ID").toString());
					if (tempMap.get("USER_BANK_CD") != null) {
						resultMap.put("bank_cd", tempMap.get("USER_BANK_CD").toString());
					} else {
						resultMap.put("bank_cd", "");
					}
					if (tempMap.get("USER_BANK_NM") != null) {
						resultMap.put("bank_nm", tempMap.get("USER_BANK_NM").toString());
					} else {
						resultMap.put("bank_nm", "");
					}
					if (tempMap.get("USER_BANK_NM") != null) {
						resultMap.put("bank_number", tempMap.get("USER_BANK_NUMBER").toString());
					} else {
						resultMap.put("bank_number", "");
					}
					if (tempMap.get("USER_PHONE_NUMBER") != null) {
						resultMap.put("phone_number", tempMap.get("USER_PHONE_NUMBER").toString());
					} else {
						resultMap.put("phone_number", "");
					}
					if (tempMap.get("USER_NAME") != null) {
						resultMap.put("user_name", tempMap.get("USER_NAME").toString());
					} else {
						resultMap.put("user_name", "");
					}

					try {
						forexDAO.insert_loginHis(map);
					} catch (Exception e) {
						resultMap.put("successYN", "Y");
						resultMap.put("result_cd", "19");
						resultMap.put("text", e);
						resultMap.put("login", "01");

					}

				} else {
					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "15");
					resultMap.put("text", "패스워드가 틀렸습니다.");
					resultMap.put("login", "01");
					try {
						forexDAO.update_fail_cnt(map);
					} catch (Exception e) {
						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "16");
						resultMap.put("text", e);
						resultMap.put("login", "01");
					}
				}
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "17");
				resultMap.put("text", "패스워드가 5회 이상 틀렸습니다. 패스워드를 변경 하세요.");
				resultMap.put("login", "02");
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 ID 입니다.");
			resultMap.put("login", "03");
		}

		// resultMap.put("map", tempMap);

		return resultMap;
	}
/*
	@Override
	public Map<String, Object> cron_order_result(Map<String, Object> map, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, Object>> arr = new ArrayList();
		// long time = System.currentTimeMillis();
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00.000", Locale.KOREA);

		String cur_time = dayTime.format(new Date(time));
		map.put("comp_time", cur_time);

		arr = forexDAO.cron_order_result(map);
		if (arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				String actkey = (String) arr.get(i).get("ACTKEY");
				String event = (String) arr.get(i).get("EVENT");
				BigDecimal start_rate = (BigDecimal) arr.get(i).get("START_RATE");
				String callput = (String) arr.get(i).get("CALLPUT");
				String user_id = (String) arr.get(i).get("USER_ID");
				BigDecimal ext_rate = (BigDecimal) arr.get(i).get("EXT_RATE");
				BigDecimal invest_money = (BigDecimal) arr.get(i).get("INVEST_MONEY");

				String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"
						+ event + "%22)&format=json&env=store://datatables.org/alltableswithkeys&callback=";
				String returnfx = "";
				returnfx = urlCall(url);

				JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
				JSONObject fxdata_query = (JSONObject) jsonObject.get("query");
				String json_results = fxdata_query.get("results").toString();
				JSONObject jsonObject_results = (JSONObject) JSONValue.parse(json_results);
				JSONObject fxdata_rate = (JSONObject) jsonObject_results.get("rate");

				String rate_id = fxdata_rate.get("id").toString();
				String rate_name = fxdata_rate.get("Name").toString();
				String rate_time = fxdata_rate.get("Time").toString();
				String rate_date = fxdata_rate.get("Date").toString();
				String rate_bid = fxdata_rate.get("Bid").toString();
				String rate_ask = fxdata_rate.get("Ask").toString();
				String rate_rate = fxdata_rate.get("Rate").toString();

				map.put("actkey", actkey);
				map.put("end_rate", rate_rate);
				if (callput.equals("CALL") && (start_rate.compareTo(new BigDecimal(rate_rate))) < 0) {
					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					BigDecimal user_tot_amount = null;
					BigDecimal user_last_amount = null;
					BigDecimal user_possible_amount = null;
					BigDecimal result_fee = null;
					BigDecimal result_user_tot_amount = null;
					BigDecimal result_user_last_amount = null;
					BigDecimal result_user_possible_amount = null;

					user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
					user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
					user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

					result_fee = invest_money.multiply(ext_rate);
					result_user_tot_amount = user_tot_amount.add(result_fee);
					result_user_last_amount = user_tot_amount;
					result_user_possible_amount = user_possible_amount.add(result_fee);

					map.put("result_fee", result_fee);
					map.put("result_user_tot_amount", result_user_tot_amount);
					map.put("result_user_last_amount", result_user_last_amount);
					map.put("result_user_possible_amount", result_user_possible_amount);

				} else {
					map.put("invest_result", "LOSE");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					BigDecimal user_tot_amount = null;
					BigDecimal user_last_amount = null;
					BigDecimal user_possible_amount = null;
					BigDecimal result_fee = null;
					BigDecimal result_user_tot_amount = null;
					BigDecimal result_user_last_amount = null;
					BigDecimal result_user_possible_amount = null;

					user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
					user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
					user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

					result_fee = new BigDecimal("0");
					result_user_tot_amount = user_tot_amount.add(result_fee);
					result_user_last_amount = user_tot_amount;
					result_user_possible_amount = user_possible_amount.add(result_fee);

					map.put("result_fee", result_fee);
					map.put("result_user_tot_amount", result_user_tot_amount);
					map.put("result_user_last_amount", result_user_last_amount);
					map.put("result_user_possible_amount", result_user_possible_amount);
				}

				if (callput.equals("PUT") && (start_rate.compareTo(new BigDecimal(rate_rate))) > 0) {
					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					BigDecimal user_tot_amount = null;
					BigDecimal user_last_amount = null;
					BigDecimal user_possible_amount = null;
					BigDecimal result_fee = null;
					BigDecimal result_user_tot_amount = null;
					BigDecimal result_user_last_amount = null;
					BigDecimal result_user_possible_amount = null;

					user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
					user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
					user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

					result_fee = invest_money.multiply(ext_rate);
					result_user_tot_amount = user_tot_amount.add(result_fee);
					result_user_last_amount = user_tot_amount;
					result_user_possible_amount = user_possible_amount.add(result_fee);

					map.put("result_fee", result_fee);
					map.put("result_user_tot_amount", result_user_tot_amount);
					map.put("result_user_last_amount", result_user_last_amount);
					map.put("result_user_possible_amount", result_user_possible_amount);

				} else {
					map.put("invest_result", "LOSE");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					BigDecimal user_tot_amount = null;
					BigDecimal user_last_amount = null;
					BigDecimal user_possible_amount = null;
					BigDecimal result_fee = null;
					BigDecimal result_user_tot_amount = null;
					BigDecimal result_user_last_amount = null;
					BigDecimal result_user_possible_amount = null;

					user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
					user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
					user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

					result_fee = new BigDecimal("0");
					result_user_tot_amount = user_tot_amount.add(result_fee);
					result_user_last_amount = user_tot_amount;
					result_user_possible_amount = user_possible_amount.add(result_fee);

					map.put("result_fee", result_fee);
					map.put("result_user_tot_amount", result_user_tot_amount);
					map.put("result_user_last_amount", result_user_last_amount);
					map.put("result_user_possible_amount", result_user_possible_amount);
				}
				forexDAO.cron_order_result_proc(map);
				forexDAO.cron_user_amount(map);
			}
		}

		return resultMap;
	}
*/
/*
	@Override
	public Map<String, Object> order_result02(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> myorderMap = new HashMap<String, Object>();

		String actkey = request.getParameter("actkey");
		if (actkey == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "48");
			resultMap.put("text", "actkey가 없습니다.");
			return resultMap;
		}

		map.put("actkey", actkey);
		myorderMap = forexDAO.select_myorder(map);

		String comp_time = "";
		String user_id = "";
		String event = "";
		BigDecimal start_rate = null;
		String callput = "";
		BigDecimal invest_money = null;
		BigDecimal ext_rate = null;
		String game_type = "";

		if (myorderMap == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "49");
			resultMap.put("text", "조회되는 주문건이 없습니다.");
			return resultMap;
		}

		comp_time = myorderMap.get("COMP_TIME").toString();
		user_id = myorderMap.get("USER_ID").toString();
		event = myorderMap.get("EVENT").toString();
		start_rate = (BigDecimal) myorderMap.get("START_RATE");
		callput = myorderMap.get("CALLPUT").toString();
		invest_money = (BigDecimal) myorderMap.get("INVEST_MONEY");
		ext_rate = (BigDecimal) myorderMap.get("EXT_RATE");
		game_type = myorderMap.get("GAME_TYPE").toString();

		resultMap.put("user_id", user_id);
		resultMap.put("event", event);
		resultMap.put("start_rate", start_rate);
		resultMap.put("invest_money", invest_money);
		resultMap.put("callput", callput);
		resultMap.put("ext_rate", ext_rate);
		resultMap.put("game_type", game_type);

		map.put("comp_time", comp_time);
		map.put("user_id", user_id);
		map.put("event", event);
		map.put("start_rate", start_rate);
		map.put("callput", callput);
		map.put("invest_money", invest_money);
		map.put("ext_rate", ext_rate);
		map.put("game_type", game_type);

		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		SimpleDateFormat timeformat02 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		SimpleDateFormat yyyymmddformat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		SimpleDateFormat yyyyformat = new SimpleDateFormat("yyyy", Locale.KOREA);
		SimpleDateFormat mmformat = new SimpleDateFormat("MM", Locale.KOREA);
		SimpleDateFormat ddformat = new SimpleDateFormat("dd", Locale.KOREA);
		SimpleDateFormat hhformat = new SimpleDateFormat("HH", Locale.KOREA);
		SimpleDateFormat miformat = new SimpleDateFormat("mm", Locale.KOREA);
		SimpleDateFormat ssformat = new SimpleDateFormat("ss", Locale.KOREA);

		java.util.Date to = timeformat.parse(comp_time);

		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(to);
		cal3.add(Calendar.MINUTE, -1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(to);
		// cal.add(Calendar.MINUTE, 1);
		String mi = miformat.format(cal3.getTime());
		String mi2 = miformat.format(cal.getTime());

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(to);
		cal2.add(Calendar.HOUR, -9);

		String hh2 = hhformat.format(cal.getTime());

		String yyyymmdd = yyyymmddformat.format(to);
		String yyyy = yyyyformat.format(to);
		String mm = mmformat.format(to);
		String dd = ddformat.format(to);
		String hh = hhformat.format(to);
		// String mi = miformat.format(to);
		String ss = ssformat.format(to);

		String returnfx = "";
		String replaceoption = event.substring(0, 3) + "_" + event.substring(3, 6);
		// String url =
		// "https://api-fxtrade.oanda.com/v1/candles?instrument="+replaceoption+"&count=1&candleFormat=midpoint&granularity=D&dailyAlignment=0&alignmentTimezone=Asia%2FSeoul";
		String url = "https://api-fxtrade.oanda.com/v1/candles?instrument=" + replaceoption + "&start=" + yyyymmdd + "T"
				+ hh + "%3A" + mi + "%3A00Z&end=" + yyyymmdd + "T" + hh2 + "%3A" + mi2
				+ "%3A00Z&candleFormat=midpoint&decimal_places=6&granularity=M1";
		returnfx = urlCall(url);
		if (returnfx.length() == 0) {
			resultMap.put("invest_result", null);
			resultMap.put("result_fee", null);
			resultMap.put("result_user_tot_amount", null);
			resultMap.put("result_user_last_amount", null);
			resultMap.put("result_user_possible_amount", null);
			resultMap.put("result_user_practice_amount", null);

			resultMap.put("successYN", "N");
			resultMap.put("text", "잘못된 요청입니다.");
			resultMap.put("result_cd", "59");
			return resultMap;
		}
		JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
		JSONArray jsonArray = (JSONArray) jsonObject.get("candles");

		JSONObject jobject = null;
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				jobject = (JSONObject) jsonArray.get(i);
				// BigDecimal fx_rate = new
				// BigDecimal(jobject.get("openMid").toString());
				BigDecimal fx_rate = new BigDecimal(jobject.get("closeMid").toString());
				String fx_time = jobject.get("time").toString();
				fx_time = fx_time.replace("T", " ");
				java.util.Date fxto = timeformat.parse(fx_time);
				String fxyyyymmddhhmiss = timeformat02.format(fxto);

				java.util.Date xxx = timeformat.parse(comp_time);
				java.util.Date xxxx = timeformat
						.parse((yyyy + "-" + mm + "-" + dd + " " + hh2 + ":" + mi + ":" + "00"));

				map.put("end_rate", fx_rate);
				resultMap.put("end_rate", fx_rate);
				if (xxx.compareTo(xxxx) < 0 || !jobject.get("complete").toString().equals("true")) {
					// if(Integer.parseInt(fxyyyymmddhhmiss)<Integer.parseInt(yyyy+mm+dd+hh2+mi+"00")){
					resultMap.put("invest_result", null);
					resultMap.put("result_fee", null);
					resultMap.put("result_user_tot_amount", null);
					resultMap.put("result_user_last_amount", null);
					resultMap.put("result_user_possible_amount", null);
					resultMap.put("result_user_practice_amount", null);

					resultMap.put("successYN", "N");
					resultMap.put("text", "결과가 나오기 전입니다.");
					resultMap.put("result_cd", "결과가 나오기 전입니다.");

					return resultMap;
				}

				if (callput.equals("CALL") && (start_rate.compareTo(fx_rate)) == 0) {
					map.put("invest_result", "DRAW");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(invest_money);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "DRAW");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYN", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");

					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "DRAW");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYN", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}

				} else if (callput.equals("CALL") && (start_rate.compareTo(fx_rate)) < 0) {
					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "WIN");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "WIN");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}

				} else if (callput.equals("CALL") && (start_rate.compareTo(fx_rate)) > 0) {
					map.put("invest_result", "LOSE");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "LOSE");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
//						 * result_fee = invest_money.multiply(ext_rate).divide(
//						 * new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "LOSE");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}
				}

				if (callput.equals("PUT") && (start_rate.compareTo(fx_rate)) == 0) {
					map.put("invest_result", "DRAW");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);
					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						// result_fee = invest_money.multiply(ext_rate);
						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(invest_money);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);
						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "DRAW");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");

					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(invest_money);

						map.put("result_fee", invest_money);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "DRAW");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}
				} else if (callput.equals("PUT") && (start_rate.compareTo(fx_rate)) > 0) {

					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);
					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						// result_fee = invest_money.multiply(ext_rate);
						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "WIN");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					} else if (callput.equals("PUT") && (start_rate.compareTo(fx_rate)) > 0) {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "WIN");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}
				} else {
					map.put("invest_result", "LOSE");
					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;
						BigDecimal result_user_practice_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
						result_user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);
						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);

						resultMap.put("invest_result", "LOSE");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
						result_user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						result_user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						result_user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");
//						 * result_fee = invest_money.multiply(ext_rate).divide(
//						 * new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);

						resultMap.put("invest_result", "LOSE");
						resultMap.put("result_fee", result_fee);
						resultMap.put("result_user_tot_amount", result_user_tot_amount);
						resultMap.put("result_user_last_amount", result_user_last_amount);
						resultMap.put("result_user_possible_amount", result_user_possible_amount);
						resultMap.put("result_user_practice_amount", result_user_practice_amount);

						resultMap.put("successYn", "Y");
						resultMap.put("text", "성공");
						resultMap.put("result_cd", "00");
					}
				}
			}

		}

		return resultMap;
	}
*/
	@Override
	public Map<String, Object> cron_order_result_all02(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		long time = curtime();
		SimpleDateFormat logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00.000", Locale.KOREA);
		// "yyyy-MM-dd 20:00:00.000", Locale.KOREA);

		String cur_time = dayTime.format(new Date(time));
		map.put("comp_time", cur_time);

		SimpleDateFormat minute = new SimpleDateFormat("mm");
		String limit_mm = minute.format(new Date(time));
		int limit_int = Integer.parseInt(limit_mm) % 2;

		if (limit_int != 0) {
			return resultMap;
		}

		arr = forexDAO.cron_order_result02(map);
		
		if (arr != null) {
			
			try{
				forexDAO.cron_order_result02_update1(map);
			}catch(Exception e){
				log.debug(e);			
			}
			
			for (int i = 0; i < arr.size(); i++) {
				String actkey = (String) arr.get(i).get("ACTKEY");
				String event = (String) arr.get(i).get("EVENT");
				BigDecimal start_rate = (BigDecimal) arr.get(i).get("START_RATE");
				String callput = (String) arr.get(i).get("CALLPUT");
				String game_type = (String) arr.get(i).get("GAME_TYPE");
				String user_id = (String) arr.get(i).get("USER_ID");
				BigDecimal ext_rate = (BigDecimal) arr.get(i).get("EXT_RATE");
				BigDecimal invest_money = (BigDecimal) arr.get(i).get("INVEST_MONEY");
				String comp_time = arr.get(i).get("COMP_TIME").toString();
				String status = arr.get(i).get("STATUS").toString();

				map.put("actkey", actkey);

				SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				SimpleDateFormat timeformat02 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
				SimpleDateFormat yyyymmddformat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
				SimpleDateFormat yyyyformat = new SimpleDateFormat("yyyy", Locale.KOREA);
				SimpleDateFormat mmformat = new SimpleDateFormat("MM", Locale.KOREA);
				SimpleDateFormat ddformat = new SimpleDateFormat("dd", Locale.KOREA);
				SimpleDateFormat hhformat = new SimpleDateFormat("HH", Locale.KOREA);
				SimpleDateFormat miformat = new SimpleDateFormat("mm", Locale.KOREA);
				SimpleDateFormat ssformat = new SimpleDateFormat("ss", Locale.KOREA);

				java.util.Date to = timeformat.parse(comp_time);
				
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(to);
				cal4.add(Calendar.MINUTE, -5);
				cal4.add(Calendar.HOUR, -9);

				Calendar cal = Calendar.getInstance();
				cal.setTime(to);
				
				String yyyymmdd1 = yyyymmddformat.format(cal4.getTime());
				String yyyymmdd2 = yyyymmddformat.format(cal.getTime());

				String mi = miformat.format(cal4.getTime());
				String mi2 = miformat.format(cal.getTime());

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(to);
				cal2.add(Calendar.HOUR, -9);

				String hh2 = hhformat.format(cal2.getTime());
				String hh = hhformat.format(cal4.getTime());

				String yyyymmdd = yyyymmddformat.format(to);
				String yyyy = yyyyformat.format(to);
				String mm = mmformat.format(to);
				String dd = ddformat.format(to);
				String ss = ssformat.format(to);
				
				String returnfx = "";
				String replaceoption = event.substring(0, 3) + "_" + event.substring(3, 6);
				
				// String url =
				// "https://api-fxtrade.oanda.com/v1/candles?instrument="+replaceoption+"&count=1&candleFormat=midpoint&granularity=D&dailyAlignment=0&alignmentTimezone=Asia%2FSeoul";
				String url = "https://api-fxtrade.oanda.com/v1/candles?instrument=" + replaceoption + "&start="
						+ yyyymmdd1 + "T" + hh + "%3A" + mi + "%3A00Z&end=" + yyyymmdd2 + "T" + hh2 + "%3A" + mi2
						+ "%3A05Z&candleFormat=midpoint&decimal_places=6&granularity=S5";
				returnfx = urlCall(url);

				JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
				JSONArray jsonArray = null;
				if (jsonObject != null) {
					jsonArray = (JSONArray) jsonObject.get("candles");
				}
				JSONObject jobject = null;
				if (jsonArray != null) {
					// for(int j=0;j<jsonArray.size();j++){
					jobject = (JSONObject) jsonArray.get(jsonArray.size()-1);

					BigDecimal rate_rate = new BigDecimal(jobject.get("closeMid").toString());
					String fx_time = jobject.get("time").toString();
					fx_time = fx_time.replace("T", " ");
					java.util.Date fxto = timeformat.parse(fx_time);
					String fxyyyymmddhhmiss = timeformat02.format(fxto);

					java.util.Date xxx = timeformat.parse(comp_time);
					java.util.Date xxxx = timeformat
							.parse((yyyy + "-" + mm + "-" + dd + " " + hh2 + ":" + mi + ":" + "00"));

					map.put("end_rate", rate_rate);

					Calendar cal3 = Calendar.getInstance();
					cal3.setTime(fxto);
					cal3.add(Calendar.HOUR, 9);
//					if (xxx.compareTo(cal3.getTime()) <= 0 && jobject.get("complete").toString().equals("true")) {
					if (jobject.get("complete").toString().equals("true")) {
												// if(true){
						if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) == 0) {
							map.put("invest_result", "DRAW");

							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);

							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								result_fee = new BigDecimal("0");
								result_user_tot_amount = user_tot_amount.add(invest_money);
								result_user_last_amount = user_tot_amount;
//								result_user_possible_amount = user_possible_amount.add(invest_money);
								result_user_possible_amount = user_possible_amount.add(invest_money);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);

								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);
							} else {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

								result_fee = new BigDecimal("0");
								result_user_practice_amount = user_practice_amount.add(invest_money);

								map.put("result_fee", result_fee);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
							
							try{
								forexDAO.cron_order_result02_update2(map);
							}catch(Exception e){
								log.debug(e);			
							}

						} else if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) < 0) {
							map.put("invest_result", "WIN");

							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);

							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								result_fee = invest_money.add(invest_money.multiply(ext_rate)
										.divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP));
								result_user_tot_amount = user_tot_amount.add(result_fee);
								result_user_last_amount = user_tot_amount;
								result_user_possible_amount = user_possible_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);

								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);

								BigDecimal result_money = new BigDecimal("0").subtract(result_fee).add(invest_money);
								map.put("result_money", result_money);
								forexDAO.cron_result_master(map);
							} else {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

								result_fee = invest_money.add(invest_money.multiply(ext_rate)
										.divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP));
								result_user_practice_amount = user_practice_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
							
							try{
								forexDAO.cron_order_result02_update2(map);
							}catch(Exception e){
								log.debug(e);			
							}

						} else if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) > 0) {
							map.put("invest_result", "LOSE");

							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);

							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								result_fee = new BigDecimal("0");
								result_user_tot_amount = user_tot_amount.add(result_fee);
								result_user_last_amount = user_tot_amount;
								result_user_possible_amount = user_possible_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);

								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);

								BigDecimal result_money = new BigDecimal("0").add(invest_money);
								map.put("result_money", result_money);
								forexDAO.cron_result_master(map);
							} else {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
								/*
								 * result_fee =
								 * invest_money.multiply(ext_rate).divide( new
								 * BigDecimal(100), 3,
								 * BigDecimal.ROUND_HALF_UP);
								 */
								result_fee = new BigDecimal("0");
								result_user_practice_amount = user_practice_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
							
							try{
								forexDAO.cron_order_result02_update2(map);
							}catch(Exception e){
								log.debug(e);			
							}
						}

						if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) == 0) {
							map.put("invest_result", "DRAW");

							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);
							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								// result_fee = invest_money.multiply(ext_rate);
								result_fee = new BigDecimal("0");
								result_user_tot_amount = user_tot_amount.add(invest_money);
								result_user_last_amount = user_tot_amount;
								result_user_possible_amount = user_possible_amount.add(invest_money);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);
								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);
							} else {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

								result_fee = new BigDecimal("0");
								result_user_practice_amount = user_practice_amount.add(invest_money);

								map.put("result_fee", invest_money);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
							
							try{
								forexDAO.cron_order_result02_update2(map);
							}catch(Exception e){
								log.debug(e);			
							}
						} else if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) > 0) {
							map.put("invest_result", "WIN");

							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);
							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								// result_fee = invest_money.multiply(ext_rate);

								result_fee = invest_money.add(invest_money.multiply(ext_rate)
										.divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP));
								result_user_tot_amount = user_tot_amount.add(result_fee);
								result_user_last_amount = user_tot_amount;
								result_user_possible_amount = user_possible_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);

								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);

								BigDecimal result_money = new BigDecimal("0").subtract(result_fee).add(invest_money);
								map.put("result_money", result_money);
								forexDAO.cron_result_master(map);

							} else if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) > 0) {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

								result_fee = invest_money.add(invest_money.multiply(ext_rate)
										.divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP));
								result_user_practice_amount = user_practice_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
							
							try{
								forexDAO.cron_order_result02_update2(map);
							}catch(Exception e){
								log.debug(e);			
							}
						} else if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) < 0) {
							map.put("invest_result", "LOSE");
							map.put("user_id", user_id);
							Map<String, Object> user_info = new HashMap<String, Object>();
							user_info = forexDAO.selectUserDetail(map);

							if (game_type.equals("R")) {
								BigDecimal user_tot_amount = null;
								BigDecimal user_last_amount = null;
								BigDecimal user_possible_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_tot_amount = null;
								BigDecimal result_user_last_amount = null;
								BigDecimal result_user_possible_amount = null;

								user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
								user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
								user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

								result_fee = new BigDecimal("0");
								result_user_tot_amount = user_tot_amount.add(result_fee);
								result_user_last_amount = user_tot_amount;
								result_user_possible_amount = user_possible_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_tot_amount", result_user_tot_amount);
								map.put("result_user_last_amount", result_user_last_amount);
								map.put("result_user_possible_amount", result_user_possible_amount);
								forexDAO.cron_order_result_proc(map);
								map.put("last_act", "주문결과지급");
								forexDAO.cron_user_amount(map);

								BigDecimal result_money = new BigDecimal("0").add(invest_money);
								map.put("result_money", result_money);
								forexDAO.cron_result_master(map);
							} else {
								BigDecimal user_practice_amount = null;
								BigDecimal result_fee = null;
								BigDecimal result_user_practice_amount = null;

								user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
								/*
								 * result_fee =
								 * invest_money.multiply(ext_rate).divide( new
								 * BigDecimal(100), 3,
								 * BigDecimal.ROUND_HALF_UP);
								 */
								result_fee = new BigDecimal("0");
								result_user_practice_amount = user_practice_amount.add(result_fee);

								map.put("result_fee", result_fee);
								map.put("result_user_practice_amount", result_user_practice_amount);

								forexDAO.cron_order_result_proc_practice(map);
								map.put("last_act", "연습주문결과지급");
								forexDAO.cron_user_amount_practice(map);
							}
						}
						
						try{
							forexDAO.cron_order_result02_update2(map);
						}catch(Exception e){
							log.debug(e);			
						}
					}else{
						try{
							forexDAO.cron_order_result02_update2(map);
						}catch(Exception e){
							log.debug(e);			
						}
					}
					// }
				}
			}
		}

		return resultMap;
	}
/*
	@Override
	public Map<String, Object> cron_order_result_all(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> fxdata = new HashMap<String, Object>();
		Map<String, Object> fxdata_insertdate = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();
		// long time = System.currentTimeMillis();
		long time = curtime();

		SimpleDateFormat logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);

		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00.000", Locale.KOREA);
		// SimpleDateFormat dayTime = new
		// SimpleDateFormat("yyyy-MM-dd 20:38:00.000",Locale.KOREA);

		String cur_time = dayTime.format(new Date(time));
		map.put("comp_time", cur_time);
		fxdata_insertdate = forexDAO.cron_fxdata_insertdate(map);
		if (fxdata_insertdate == null) {
			String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%27GBPAUD%27,%20%27EURJPY%27,%20%27AUDJPY%27,%20%27AUDUSD%27,%20%27EURAUD%27,%20%27USDCAD%27,%20%27EURUSD%27,%20%27GBPUSD%27,%20%27USDJPY%27)&format=json&env=store://datatables.org/alltableswithkeys";
			String returnfx = "";
			returnfx = urlCall(url);

			JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
			JSONObject fxdata_query = (JSONObject) jsonObject.get("query");
			String json_results = fxdata_query.get("results").toString();
			JSONObject jsonObject_results = (JSONObject) JSONValue.parse(json_results);
			JSONArray fxdata_rate = (JSONArray) jsonObject_results.get("rate");
			// List<String> fxdata_list = new ArrayList<String>();
			for (int j = 0; j < fxdata_rate.size(); j++) {
				JSONObject jobject = (JSONObject) fxdata_rate.get(j);

				String id = (String) jobject.get("id");
				String name = (String) jobject.get("Name");
				BigDecimal rate = new BigDecimal((String) jobject.get("Rate"));

				map.put("id", id);
				map.put("name", name);
				map.put("rate", rate);

				forexDAO.insert_cron_fxdata(map);
			}
		}
		arr = forexDAO.cron_order_result(map);
		if (arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				String actkey = (String) arr.get(i).get("ACTKEY");
				String event = (String) arr.get(i).get("EVENT");
				BigDecimal start_rate = (BigDecimal) arr.get(i).get("START_RATE");
				String callput = (String) arr.get(i).get("CALLPUT");
				String game_type = (String) arr.get(i).get("GAME_TYPE");
				String user_id = (String) arr.get(i).get("USER_ID");
				BigDecimal ext_rate = (BigDecimal) arr.get(i).get("EXT_RATE");
				BigDecimal invest_money = (BigDecimal) arr.get(i).get("INVEST_MONEY");

				map.put("event", event);
				map.put("actkey", actkey);
				fxdata = forexDAO.cron_fxdata_id(map);

				BigDecimal rate_rate = (BigDecimal) fxdata.get("RATE");
				map.put("end_rate", rate_rate);

				if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) == 0) {
					map.put("invest_result", "DRAW");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(invest_money);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}

				} else if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) < 0) {
					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}

				} else if (callput.equals("CALL") && (start_rate.compareTo(rate_rate)) > 0) {
					map.put("invest_result", "LOSE");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
//						 * result_fee = invest_money.multiply(ext_rate).divide(
//						 * new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}
				}

				if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) == 0) {
					map.put("invest_result", "DRAW");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);
					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						// result_fee = invest_money.multiply(ext_rate);
						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(invest_money);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(invest_money);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);
						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(invest_money);

						map.put("result_fee", invest_money);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}
				} else if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) > 0) {
					map.put("invest_result", "WIN");

					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);
					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						// result_fee = invest_money.multiply(ext_rate);
						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);

						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else if (callput.equals("PUT") && (start_rate.compareTo(rate_rate)) < 0) {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");

						result_fee = invest_money.multiply(ext_rate).divide(new BigDecimal(100), 3,
								BigDecimal.ROUND_HALF_UP);
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}
				} else {
					map.put("invest_result", "LOSE");
					map.put("user_id", user_id);
					Map<String, Object> user_info = new HashMap<String, Object>();
					user_info = forexDAO.selectUserDetail(map);

					if (game_type.equals("R")) {
						BigDecimal user_tot_amount = null;
						BigDecimal user_last_amount = null;
						BigDecimal user_possible_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_tot_amount = null;
						BigDecimal result_user_last_amount = null;
						BigDecimal result_user_possible_amount = null;

						user_tot_amount = (BigDecimal) user_info.get("TOT_AMOUNT");
						user_last_amount = (BigDecimal) user_info.get("LAST_AMOUNT");
						user_possible_amount = (BigDecimal) user_info.get("POSSIBLE_AMOUNT");

						result_fee = new BigDecimal("0");
						result_user_tot_amount = user_tot_amount.add(result_fee);
						result_user_last_amount = user_tot_amount;
						result_user_possible_amount = user_possible_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_tot_amount", result_user_tot_amount);
						map.put("result_user_last_amount", result_user_last_amount);
						map.put("result_user_possible_amount", result_user_possible_amount);
						forexDAO.cron_order_result_proc(map);
						forexDAO.cron_user_amount(map);
					} else {
						BigDecimal user_practice_amount = null;
						BigDecimal result_fee = null;
						BigDecimal result_user_practice_amount = null;

						user_practice_amount = (BigDecimal) user_info.get("PRACTICE_AMOUNT");
//						 * result_fee = invest_money.multiply(ext_rate).divide(
//						 * new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
						result_fee = new BigDecimal("0");
						result_user_practice_amount = user_practice_amount.add(result_fee);

						map.put("result_fee", result_fee);
						map.put("result_user_practice_amount", result_user_practice_amount);

						forexDAO.cron_order_result_proc_practice(map);
						forexDAO.cron_user_amount_practice(map);
					}
				}
			}
		}

		return resultMap;
	}
*/
	@Override
	public Map<String, Object> extRate_receive(Map<String, Object> map, HttpServletRequest request) throws Exception {

		long time = curtime();
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		String now_hh = hh.format(new Date(time));
		map.put("hh", now_hh);
		
		

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();
		
		String user_id = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		arr = forexDAO.extRate_receive(map);
		resultMap.put("list", arr);

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> decomplete_list(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		SimpleDateFormat input_time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		SimpleDateFormat minute_time = new SimpleDateFormat("yyyyMMddHHmm");
		String str_time = dayTime.format(new Date(time));
		String date_time = input_time.format(new Date(time));
		java.util.Date curtime = dayTime.parse(str_time);
		map.put("cur_time", curtime);

		String user_id = request.getParameter("id");
		map.put("id", user_id);

		arr = forexDAO.decomplete_bet(map);

		if (arr.size() > 0) {
			resultMap.put("decomplete_list", arr);

		} else {
			resultMap.put("decomplete_list", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> decomplete_deposit(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();
		List<Map<String, Object>> arr02 = new ArrayList();

		String user_id = request.getParameter("user_id");
		map.put("user_id", user_id);

		arr = forexDAO.decomplete_deposit(map);

		if (arr.size() > 0) {
			resultMap.put("decomplete_deposit", "Y");
		} else {
			resultMap.put("decomplete_deposit", "N");
		}

		arr02 = forexDAO.deposit_info(map);

		if (arr02.size() > 0) {
			resultMap.put("deposit_info", arr02);
		} else {
			resultMap.put("deposit_info", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> result_receive(Map<String, Object> map, HttpServletRequest request) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String user_comp_time = request.getParameter("COMP_TIME");
		String user_id = request.getParameter("USER_ID");
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		String mm = user_comp_time.substring(0, 2);
		String dd = user_comp_time.substring(3, 5);
		String yyyy = user_comp_time.substring(6, 10);
		String hh = user_comp_time.substring(11, 13);
		String mi = user_comp_time.substring(14, 16);
		String ss = user_comp_time.substring(17, 19);

		String yyyymmddhhmiss = yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss;

		java.util.Date c_time = dayTime.parse(yyyymmddhhmiss);

		// map.put("c_time", c_time);
		map.put("c_time", yyyymmddhhmiss);
		map.put("user_id", user_id);

		List<Map<String, Object>> arr = new ArrayList();
		Map<String, Object> result_chk = new HashMap<String, Object>();
		List<Map<String, Object>> result_arr = new ArrayList();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		JSONArray list = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONObject receiveobj = new JSONObject();
		// list.add(obj);
		// receiveobj.put("bet_info",list);
		result_chk = forexDAO.result_receive_chk(map);
		arr = forexDAO.result_receive(map);

		if (Integer.parseInt(result_chk.get("CNT").toString()) < 1) {
			for (int i = 0; i < arr.size(); i++) {
				String g_type = (String) arr.get(i).get("GAME_TYPE");
				if (g_type.equals("R")) {
					String result_fee = arr.get(i).get("RESULT_FEE").toString();
					String event = (String) arr.get(i).get("EVENT");
					String start_rate = arr.get(i).get("START_RATE").toString();
					String end_rate = arr.get(i).get("END_RATE").toString();
					String userid = (String) arr.get(i).get("USER_ID");
					String callput = (String) arr.get(i).get("CALLPUT");
					String order_time = dateFormat.format(arr.get(i).get("ORDER_TIME"));
					String ext_rate = arr.get(i).get("EXT_RATE").toString();
					String status = (String) arr.get(i).get("STATUS");
					String insertdate = dateFormat.format(arr.get(i).get("INSERTDATE"));
					String option_type = (String) arr.get(i).get("OPTION_TYPE");
					String comp_time = dateFormat.format(arr.get(i).get("COMP_TIME"));
					String invest_money = arr.get(i).get("INVEST_MONEY").toString();
					String tot_amount = arr.get(i).get("TOT_AMOUNT").toString();

					obj.put("result_fee", result_fee);
					obj.put("event", event);
					obj.put("start_rate", start_rate);
					obj.put("end_rate", end_rate);
					obj.put("user_id", userid);
					obj.put("callput", callput);
					obj.put("order_time", order_time);
					obj.put("ext_rate", ext_rate);
					obj.put("status", status);
					obj.put("insertdate", insertdate);
					obj.put("option_type", option_type);
					obj.put("comp_time", comp_time);
					obj.put("invest_money", invest_money);
					obj.put("tot_amount", tot_amount);
					// list.add(obj);
				} else {
					String result_fee = arr.get(i).get("RESULT_FEE").toString();
					String event = (String) arr.get(i).get("EVENT");
					String start_rate = arr.get(i).get("START_RATE").toString();
					String end_rate = arr.get(i).get("END_RATE").toString();
					String userid = (String) arr.get(i).get("USER_ID");
					String callput = (String) arr.get(i).get("CALLPUT");
					String order_time = dateFormat.format(arr.get(i).get("ORDER_TIME"));
					String ext_rate = arr.get(i).get("EXT_RATE").toString();
					String status = (String) arr.get(i).get("STATUS");
					String insertdate = dateFormat.format(arr.get(i).get("INSERTDATE"));
					String option_type = (String) arr.get(i).get("OPTION_TYPE");
					String comp_time = dateFormat.format(arr.get(i).get("COMP_TIME"));
					String invest_money = arr.get(i).get("INVEST_MONEY").toString();
					String practice_amount = arr.get(i).get("PRACTICE_AMOUNT").toString();

					obj.put("result_fee", result_fee);
					obj.put("event", event);
					obj.put("start_rate", start_rate);
					obj.put("end_rate", end_rate);
					obj.put("user_id", userid);
					obj.put("callput", callput);
					obj.put("order_time", order_time);
					obj.put("ext_rate", ext_rate);
					obj.put("status", status);
					obj.put("insertdate", insertdate);
					obj.put("option_type", option_type);
					obj.put("comp_time", comp_time);
					obj.put("invest_money", invest_money);
					obj.put("practice_amount", practice_amount);

					//
				}

			}

			resultMap.put("list", arr);

			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "00");
			resultMap.put("text", "success");
		} else {
			resultMap.put("list", null);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "20");
			resultMap.put("text", "결과가 없습니다.");
		}

		// result_arr.add(resultMap);
		return resultMap;
		// return forexDAO.result_receive(map);

		/*
		 * Map<String, Object> resultMap = new HashMap<String,Object>();
		 * //Map<String, Object> return_result= new HashMap<String,Object>();
		 * List<Map<String,Object>> arr = new ArrayList();
		 * 
		 * String user_comp_time = request.getParameter("COMP_TIME");
		 * SimpleDateFormat dayTime = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA); String mm =
		 * user_comp_time.substring(0, 2); String dd =
		 * user_comp_time.substring(3, 5); String yyyy =
		 * user_comp_time.substring(6, 10); String hh =
		 * user_comp_time.substring(11, 13); String mi =
		 * user_comp_time.substring(14, 16); String ss =
		 * user_comp_time.substring(17, 19);
		 * 
		 * String yyyymmddhhmiss = yyyy+mm+dd+hh+mi+ss;
		 * 
		 * java.util.Date c_time = dayTime.parse(yyyymmddhhmiss);
		 * 
		 * map.put("c_time", c_time);
		 * 
		 * arr = forexDAO.return_result(map);
		 * 
		 * 
		 * return resultMap;
		 */
	}

	/*
	@Override
	public Map<String, Object> order_final(Map<String, Object> map, HttpServletRequest request) throws Exception {

		String order_key = request.getParameter("order_key");
		BigDecimal order_rate = null;
		BigDecimal cur_rate = null;
		BigDecimal order_ext_rate = null;
		BigDecimal order_invest_money = null;
		BigDecimal order_result_fee = null;
		BigDecimal user_tot_amount = null;
		BigDecimal user_last_amount = null;
		BigDecimal update_tot_amount = null;

		map.put("order_key", order_key);

		String order_event = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> my_order = new HashMap<String, Object>();
		Map<String, Object> return_result = new HashMap<String, Object>();
		my_order = forexDAO.my_order(map);

		String returnfx = "";
		// Map<String, Object> tempMap = forexDAO.selectUserDetail(map);

		if (my_order != null) {
			order_event = my_order.get("EVENT").toString();
			order_rate = (BigDecimal) my_order.get("START_RATE");
			order_ext_rate = (BigDecimal) my_order.get("EXT_RATE");
			order_invest_money = (BigDecimal) my_order.get("INVEST_MONEY");
			user_tot_amount = (BigDecimal) my_order.get("TOT_AMOUNT");
			user_last_amount = (BigDecimal) my_order.get("LAST_AMOUNT");
			String order_callput = my_order.get("CALLPUT").toString();
			String user_id = my_order.get("USER_ID").toString();

			String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"
					+ order_event + "%22)&format=json&env=store://datatables.org/alltableswithkeys&callback=";
			returnfx = urlCall(url);

			JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
			JSONObject fxdata_query = (JSONObject) jsonObject.get("query");
			String json_results = fxdata_query.get("results").toString();
			JSONObject jsonObject_results = (JSONObject) JSONValue.parse(json_results);
			JSONObject fxdata_rate = (JSONObject) jsonObject_results.get("rate");
			String rate = fxdata_rate.get("Rate").toString();
			cur_rate = new BigDecimal(rate);

			map.put("last_amount", user_tot_amount); // last_amount ������Ʈ
														// ��ġ�� �� ���� �����ݾ�
			// map.put("user_last_amount", user_last_amount); //tb_user_amount
			// ��
			map.put("end_rate", cur_rate);
			map.put("user_id", user_id);

			int comparison = cur_rate.compareTo(order_rate);

			// order_result_fee = order_invest_money.multiply(order_ext_rate);
			// log.debug(order_result_fee);
			//

			if ((order_callput.equals("CALL") && comparison == 1)
					|| (order_callput.equals("PUT") && comparison == -1)) {
				// win
				order_result_fee = order_invest_money.multiply(order_ext_rate);
				update_tot_amount = user_tot_amount.add(order_result_fee);

				map.put("game_result", "WIN");
				map.put("game_result_fee", order_result_fee);
				map.put("update_tot_amount", update_tot_amount);
				map.put("update_last_amount", user_tot_amount);

				// tb_user_Bet ������Ʈ
				forexDAO.update_user_order_final_win(map);
				// tb_user_amount ������Ʈ
				forexDAO.update_user_amount_win(map);

				return_result = forexDAO.return_result(map);

				BigDecimal final_result_fee = new BigDecimal(return_result.get("RESULT_FEE").toString());
				BigDecimal final_tot_amount = new BigDecimal(return_result.get("TOT_AMOUNT").toString());

				resultMap.put("final_result_fee", final_result_fee);
				resultMap.put("final_tot_amount", final_tot_amount);

			} else if ((order_callput.equals("CALL") && comparison == -1)
					|| (order_callput.equals("PUT") && comparison == 1)) {
				// lose
				map.put("game_result", "LOSE");
				forexDAO.update_user_order_final_lose(map);

				resultMap.put("final_result_fee", 0);
				resultMap.put("final_tot_amount", user_tot_amount);

			} else if ((order_callput.equals("CALL") && comparison == 0)
					|| (order_callput.equals("PUT") && comparison == 0)) {
				// lose
				map.put("game_result", "DRAW");
				// forexDAO.update_user_order_final_lose(map);
			}

			// resultMap.put("map", returnfx);
		}
		return resultMap;
	}
*/
	/*
	@Override
	public Map<String, Object> order_money(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// long time = System.currentTimeMillis();
		long time = curtime();
		// String term = request.getParameter("TERM");

		// long limit_time = time+1000*60*Integer.parseInt(term.toString());
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		SimpleDateFormat input_time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		SimpleDateFormat minute_time = new SimpleDateFormat("yyyyMMddHHmm");
		String str_time = dayTime.format(new Date(time));
		String date_time = input_time.format(new Date(time));
		// String input_comp_time = dayTime.format(new Date(comp_time));

		String user_id = request.getParameter("USER_ID");
		String order_money = request.getParameter("ORDER_MONEY");
		String callput = request.getParameter("CALLPUT");
		String event = request.getParameter("EVENT");
		String option_type = request.getParameter("OPTION_TYPE");
		String game_type = request.getParameter("GAME_TYPE");

//		 * String user_comp_time = request.getParameter("COMP_TIME"); String mm
//		 * = user_comp_time.substring(0, 2); String dd =
//		 * user_comp_time.substring(3, 5); String yyyy =
//		 * user_comp_time.substring(6, 10); String hh =
//		 * user_comp_time.substring(11, 13); String mi =
//		 * user_comp_time.substring(14, 16); String ss =
//		 * user_comp_time.substring(17, 19);
		String user_comp_time = request.getParameter("COMP_TIME");
		String mm = user_comp_time.substring(0, 2);
		String dd = user_comp_time.substring(3, 5);
		String yyyy = user_comp_time.substring(6, 10);
		String hh = user_comp_time.substring(11, 13);
		String mi = user_comp_time.substring(14, 16);
		String ss = user_comp_time.substring(17, 19);

		String yyyymmddhhmiss = yyyy + mm + dd + hh + mi + ss;
		map.put("option", event);
		map.put("user_id", user_id);
		map.put("hh", hh);

		java.util.Date c_time = input_time.parse(yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss);
		long limit_time = c_time.getTime() - 10000;
		long user_c_time = c_time.getTime();
		String l_time = dayTime.format(limit_time);
		String uc_time = dayTime.format(user_c_time);

		java.util.Date input_current_time = dayTime.parse(str_time);
		java.util.Date input_comp_time = dayTime.parse(uc_time);

		map.put("comp_time", input_comp_time);

		java.util.Date compare_limit_time = dayTime.parse(l_time);
		java.util.Date compare_current_time = dayTime.parse(str_time);

		int result_time_compare = compare_current_time.compareTo(compare_limit_time);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> moneyMap = new HashMap<String, Object>();

		Map<String, Object> rateInfoMap = forexDAO.selectCommonRate(map);
		int rate_ctr = Integer.parseInt(rateInfoMap.get("RATE_CTR").toString());
		BigDecimal min_invest = (BigDecimal) rateInfoMap.get("MIN_INVEST");
		BigDecimal max_invest = (BigDecimal) rateInfoMap.get("MAX_INVEST");

		String limit_status = rateInfoMap.get("LIMIT_STATUS").toString();
		int limit_cnt = Integer.parseInt(rateInfoMap.get("LIMIT_CNT").toString());
		int limit_pct = Integer.parseInt(rateInfoMap.get("LIMIT_PCT").toString());
		BigDecimal limit_invest = (BigDecimal) rateInfoMap.get("LIMIT_INVEST");
		int emergent_rate = Integer.parseInt(rateInfoMap.get("EMERGENT_RATE").toString());
		int comp_cnt = Integer.parseInt(rateInfoMap.get("COMP_CNT").toString());

		if (limit_cnt <= comp_cnt) {
			resultMap.put("order_YN", "N");
			resultMap.put("alert", "4");
			resultMap.put("alert_txt", "주문건수가 최대 입니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "45");
			resultMap.put("text", "주문건수가 최대 입니다.");
			return resultMap;
		}

		Map<String, Object> orderHisMap = forexDAO.selectOrderHis(map);

		BigDecimal callinv = (BigDecimal) orderHisMap.get("CALLINV");
		BigDecimal putinv = (BigDecimal) orderHisMap.get("PUTINV");
		BigDecimal totinv = (BigDecimal) orderHisMap.get("TOTINV");

		if (totinv.compareTo(limit_invest) >= 0) {
			if (callput.equals("CALL") && ((callinv.divide(totinv)).multiply(new BigDecimal("100"))
					.compareTo(new BigDecimal(limit_pct)) >= 0)) {
				resultMap.put("order_YN", "N");
				resultMap.put("alert", "5");
				resultMap.put("alert_txt", "CALL 가능 건수가 없습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "46");
				resultMap.put("text", "CALL 가능 건수가 없습니다.");
				return resultMap;
			}

			if (callput.equals("PUT") && ((putinv.divide(totinv)).multiply(new BigDecimal("100"))
					.compareTo(new BigDecimal(limit_pct)) >= 0)) {
				resultMap.put("order_YN", "N");
				resultMap.put("alert", "6");
				resultMap.put("alert_txt", "PUT 가능 건수가 없습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "47");
				resultMap.put("text", "PUT 가능 건수가 없습니다.");
				return resultMap;
			}
		}

		BigDecimal total = (BigDecimal) rateInfoMap.get("TOTAL");
		BigDecimal level_1 = (BigDecimal) rateInfoMap.get("LEVEL1");
		BigDecimal level_2 = (BigDecimal) rateInfoMap.get("LEVEL2");
		BigDecimal level_3 = (BigDecimal) rateInfoMap.get("LEVEL3");
		BigDecimal level_4 = (BigDecimal) rateInfoMap.get("LEVEL4");
		BigDecimal level_5 = (BigDecimal) rateInfoMap.get("LEVEL5");
		BigDecimal level_6 = (BigDecimal) rateInfoMap.get("LEVEL6");
		BigDecimal level_7 = (BigDecimal) rateInfoMap.get("LEVEL7");
		BigDecimal level_8 = (BigDecimal) rateInfoMap.get("LEVEL8");
		BigDecimal level_9 = (BigDecimal) rateInfoMap.get("LEVEL9");
		BigDecimal level_10 = (BigDecimal) rateInfoMap.get("LEVEL10");

		String recommend_id = (String) rateInfoMap.get("RECOMMEND_ID");
		String recommend_level = (String) rateInfoMap.get("RECOMMEND_LEVEL");
		String user_level = (String) rateInfoMap.get("USER_LEVEL");

		if (new BigDecimal(order_money).compareTo(min_invest) < 0
				|| new BigDecimal(order_money).compareTo(max_invest) > 0) {
			resultMap.put("order_YN", "N");
			resultMap.put("alert", "3");
			resultMap.put("alert_txt", "주문가능금액은 " + min_invest.toString() + " ~ " + max_invest.toString() + "입니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "21");
			resultMap.put("text", "주문가능금액 범위 밖입니다.");
		} else {
			if (result_time_compare < 0) {
				String actkey = user_id + order_money + event + callput + date_time;

				String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"
						+ event + "%22)&format=json&env=store://datatables.org/alltableswithkeys&callback=";
				String returnfx = "";
				returnfx = urlCall(url);

				JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
				JSONObject fxdata_query = (JSONObject) jsonObject.get("query");
				String json_results = fxdata_query.get("results").toString();
				JSONObject jsonObject_results = (JSONObject) JSONValue.parse(json_results);
				JSONObject fxdata_rate = (JSONObject) jsonObject_results.get("rate");

				String rate_id = fxdata_rate.get("id").toString();
				String rate_name = fxdata_rate.get("Name").toString();
				String rate_time = fxdata_rate.get("Time").toString();
				String rate_date = fxdata_rate.get("Date").toString();
				String rate_bid = fxdata_rate.get("Bid").toString();
				String rate_ask = fxdata_rate.get("Ask").toString();
				String rate_rate = fxdata_rate.get("Rate").toString();
				// int fxrate =
				// Integer.parseInt(fxdata_rate.get("Rate").toString())+rate_ctr;
				// String rate_rate = String.valueOf(fxrate);

				BigDecimal x_order_money = new BigDecimal(order_money);
				BigDecimal z_order_money = x_order_money.multiply(total).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level1_money = x_order_money.multiply(level_1).multiply(new BigDecimal("0.01"));
				BigDecimal z_level2_money = x_order_money.multiply(level_2).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level3_money = x_order_money.multiply(level_3).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level4_money = x_order_money.multiply(level_4).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level5_money = x_order_money.multiply(level_5).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level6_money = x_order_money.multiply(level_6).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level7_money = x_order_money.multiply(level_7).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level8_money = x_order_money.multiply(level_8).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level9_money = x_order_money.multiply(level_9).multiply(new BigDecimal("0.01"));
				;
				BigDecimal z_level10_money = x_order_money.multiply(level_10).multiply(new BigDecimal("0.01"));
				;

				moneyMap.put("total", z_order_money);
				moneyMap.put("1", z_level1_money);
				moneyMap.put("2", z_level2_money);
				moneyMap.put("3", z_level3_money);
				moneyMap.put("4", z_level4_money);
				moneyMap.put("5", z_level5_money);
				moneyMap.put("6", z_level6_money);
				moneyMap.put("7", z_level7_money);
				moneyMap.put("8", z_level8_money);
				moneyMap.put("9", z_level9_money);
				moneyMap.put("10", z_level10_money);

				map.put("actkey", actkey);
				map.put("user_id", user_id);
				map.put("invest_money", x_order_money.subtract(z_order_money));
				map.put("callput", callput);
				map.put("event", event);
				map.put("order_time", input_current_time);
				// map.put("comp_time", input_comp_time);
				map.put("comp_time", input_comp_time);
				map.put("option_type", option_type);
				map.put("game_type", game_type);
				map.put("start_rate", rate_rate);
				map.put("updatedate", input_current_time);
				// ------------------------//

				// Map<String, Object> tempMap = forexDAO.selectRate(map);
				BigDecimal rate = (BigDecimal) rateInfoMap.get("RATE");
				if(emergent_rate>0){
					map.put("ext_rate", emergent_rate);
				}else{
					map.put("ext_rate", rate);
				}

				// -------------------------//

				Map<String, Object> userMap = forexDAO.selectUserDetail(map);
				BigDecimal tot_amount = (BigDecimal) userMap.get("TOT_AMOUNT");
				BigDecimal practice_amount = (BigDecimal) userMap.get("PRACTICE_AMOUNT");
				BigDecimal possible_amount = new BigDecimal(0);
				BigDecimal possible_money = new BigDecimal(0);

				if (game_type.equals("R")) {
					if (userMap.get("USER_NAME") == null) {
						resultMap.put("order_YN", "N");
						resultMap.put("alert", "7");
						resultMap.put("alert_txt", "계좌 등록 후 주문 가능합니다.");

						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "58");
						resultMap.put("text", "계좌 등록 후 주문 가능합니다.");

						return resultMap;
					}

					map.put("possible_amount", 0);
					// possible_money = possible_amount.subtract(new
					// BigDecimal(order_money));
					if (userMap.get("POSSIBLE_AMOUNT") != null) {
						possible_amount = (BigDecimal) userMap.get("POSSIBLE_AMOUNT");
						possible_money = possible_amount.subtract(new BigDecimal(order_money));
						if (possible_money.compareTo(new BigDecimal("0")) < 0) {
							map.put("possible_money", 0);
						} else {
							map.put("possible_money", possible_money);
						}

					}

					map.put("last_amount", tot_amount);

					// ------------------------//

					// �ֹ� �� �ݾ� - �ֹ��ݾ� ===> �Ѻ����ݾ�
					BigDecimal cur_money = tot_amount.subtract(new BigDecimal(order_money));
					map.put("tot_amount", cur_money);

					if (tot_amount.compareTo(new BigDecimal(order_money)) == -1) {

						map.put("abnormal_cd", "22");
						map.put("abnormal_desc", "주문금액이 보유 그램보다 큼");
						map.put("client_ip", clientIp());
						forexDAO.insert_abnormal_his(map);

						resultMap.put("order_YN", "N");
						resultMap.put("alert", "1");
						resultMap.put("alert_txt", "주문금액이 보유 그램보다 큼");

						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "22");
						resultMap.put("text", "주문금액이 보유 금액보다 큽니다.");

					} else {
						map.put("commission_gubun", "point");
						// user_amount �ֹ����� �ݾ� ������Ʈ
						forexDAO.update_last_amount(map);
						// user_bet insert
						forexDAO.insert_user_order(map);
						// -----------------------//

						BigDecimal update_money = null;
						String update_id = null;
						if (user_level != null) {
							update_money = (BigDecimal) moneyMap.get(user_level);
							update_id = user_id;
							map.put("update_money", update_money);
							map.put("update_id", update_id);
							map.put("update_level", user_level);
							if (update_money.compareTo(new BigDecimal("0")) > 0) {
								forexDAO.update_commission(map);
								forexDAO.insert_commission_his(map);
							}
						}

						if (recommend_level != null) {
							update_money = (BigDecimal) moneyMap.get(recommend_level);
							update_id = recommend_id;
							map.put("update_money", update_money);
							map.put("update_id", recommend_id);
							map.put("update_level", recommend_level);
							if (update_money.compareTo(new BigDecimal("0")) > 0) {
								forexDAO.update_commission(map);
								forexDAO.insert_commission_his(map);
							}

							for (int y = Integer.parseInt(recommend_level) - 1; y > 0; y--) {
								map.put("level_id", recommend_id);
								update_id = forexDAO.select_level_id(map);
								if (update_id != null) {
									map.put("update_id", update_id);
									map.put("update_money", (BigDecimal) moneyMap
											.get(String.valueOf(Integer.parseInt(recommend_level) - 1)));
									map.put("update_level", y);
									if (update_money.compareTo(new BigDecimal("0")) > 0) {
										forexDAO.update_commission(map);
										forexDAO.insert_commission_his(map);
									}
								}
								recommend_id = update_id;

							}
						}

						if (forexDAO.select_user_bonus(map) != null) {

							List<Map<String, Object>> arr = new ArrayList();
							arr = forexDAO.select_user_bonus(map);
							BigDecimal jumun_money = x_order_money;
							for (int i = 0; i < arr.size(); i++) {
								if (jumun_money.compareTo(new BigDecimal("0")) > 0) {
									int idx = (Integer) arr.get(i).get("IDX");
									map.put("idx", idx);
									BigDecimal mission = new BigDecimal(arr.get(i).get("MISSION_MONEY").toString()); // 3000
									BigDecimal chun_money = new BigDecimal(
											arr.get(i).get("PROCESSED_BONUS").toString()); // 0
									BigDecimal chul_money = mission.subtract(chun_money); // 3000-0

									if (chul_money.compareTo(jumun_money) >= 0) {
										map.put("chul_money", jumun_money);
										jumun_money = new BigDecimal("0");
									} else {
										map.put("chul_money", chul_money);
										jumun_money = jumun_money.subtract(chul_money);
									}

									forexDAO.update_bonus(map);
								}
							}

						}

						Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);
						if (userBonusMap != null) {
							resultMap.put("mission_money",
									new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
							resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
							resultMap.put("processed_bonus",
									new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
						} else {
							resultMap.put("mission_money", null);
							resultMap.put("input_bonus", null);
							resultMap.put("processed_bonus", null);
						}

						resultMap.put("order_YN", "Y");
						resultMap.put("alert", "0");
						resultMap.put("alert_txt", "주문성공");
						resultMap.put("tot_amount", cur_money);
						resultMap.put("practice_amount", practice_amount);
						resultMap.put("actkey", actkey);
						// resultMap.put("order_time", date_time);
						resultMap.put("order_time", str_time);
						resultMap.put("comp_time", user_comp_time);
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "Y");
						resultMap.put("result_cd", "53");
						resultMap.put("text", "주문성공");

					}
				} else if (game_type.equals("P")) {

					BigDecimal cur_money = practice_amount.subtract(new BigDecimal(order_money));
					map.put("practice_amount", cur_money);

					if (practice_amount.compareTo(new BigDecimal(order_money)) == -1) {

						map.put("abnormal_cd", "22");
						map.put("abnormal_desc", "주문금액이 보유 그램보다 큼");
						map.put("client_ip", clientIp());
						forexDAO.insert_abnormal_his(map);

						resultMap.put("order_YN", "N");
						resultMap.put("alert", "1");
						resultMap.put("alert_txt", "주문금액이 보유 그램보다 큼");
						resultMap.put("practice_amount", "");
						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "22");
						resultMap.put("text", "주문금액이 보유 금액보다 큽니다.");

					} else {
						// user_bet insert
						forexDAO.update_last_practice_amount(map);
						forexDAO.insert_user_order_practice(map);
						// -----------------------//

						resultMap.put("order_YN", "Y");
						resultMap.put("alert", "0");
						resultMap.put("alert_txt", "주문성공");
						resultMap.put("practice_amount", cur_money);
						resultMap.put("tot_amount", tot_amount);
						resultMap.put("actkey", actkey);
						// resultMap.put("order_time", date_time);
						resultMap.put("order_time", str_time);
						resultMap.put("comp_time", user_comp_time);
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "Y");
						resultMap.put("result_cd", "53");
						resultMap.put("text", "주문성공");

					}
				} else {
					resultMap.put("order_YN", "N");
					resultMap.put("alert", "3");
					resultMap.put("alert_txt", "game_type 이 정의 되지 않음");
					resultMap.put("practice_amount", "");
					resultMap.put("tot_amount", "");
					resultMap.put("actkey", "");
					resultMap.put("order_time", "");
					resultMap.put("comp_time", "");
					resultMap.put("game_type", game_type);

					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "23");
					resultMap.put("text", "game type 이 정의 되지 않았습니다.");
				}
			} else {

				resultMap.put("order_YN", "N");
				resultMap.put("alert", "2");
				resultMap.put("alert_txt", "주문가능한시간이 지났습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "24");
				resultMap.put("text", "주문가능한시간이 지났습니다.");
			}
		}

		return resultMap;
	}
*/
	@Override
	public Map<String, Object> order_money02(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String version = version();
		String client_version = null;
		
		if(request.getParameter("version")!=null){
			client_version = request.getParameter("version").toString();
		}
		
		log.debug("-------------------------------");;
		log.debug(version);
		log.debug(client_version);
		
		if(client_version==null||Integer.parseInt(version)>Integer.parseInt(client_version)){
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "68");
			resultMap.put("text", "버전 업데이트가 있습니다.\n종료후 재시작 하시기 바랍니다.");
			resultMap.put("login", "68");
			return resultMap;
		}
		
		String user_id = request.getParameter("USER_ID");
		map.put("user_id", user_id);
		map.put("id", user_id);
		map.put("pw", null);
		
		Map<String, Object> tempMap = forexDAO.loginFlag(map);
		if (tempMap != null) {					
			if((String)tempMap.get("TELBLOCK")!=null ||(String)tempMap.get("IPBLOCK")!=null){			
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "65");
				resultMap.put("text", "차단된 사용자 입니다.");
				resultMap.put("login", "65");
				return resultMap;
			}
		}
		
		// long time = System.currentTimeMillis();
		long time = curtime();
		String user_comp_time = request.getParameter("COMP_TIME");
		int ct_yoil = getDateDay(user_comp_time, "MM/dd/yyyy hh:mm:ss");
		int ct_wolil = Integer.parseInt(user_comp_time.substring(0, 5).replace("/", ""));
		int ct_il = Integer.parseInt(user_comp_time.substring(3, 5));

		// String term = request.getParameter("TERM");

		SimpleDateFormat mmdd = new SimpleDateFormat("MM-dd");
		SimpleDateFormat oc_dd = new SimpleDateFormat("dd");
		SimpleDateFormat oc_time = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat oc_mmtime = new SimpleDateFormat("mm");

		// 개장, 폐장
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 0 1 2 3 4 5 6
		// final String[] week = { "SUN", "MON", "TUE", "WED", "THU", "FRI",
		// "SAT" };
		final int[] week = { 0, 1, 2, 3, 4, 5, 6 };

		int yoil = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
		String wolil = mmdd.format(new Date(time));
		String il = oc_dd.format(new Date(time));
		String cur_time = oc_time.format(new Date(time));
		String ct_time = user_comp_time.substring(11, 19);
		java.util.Date cur_time02 = oc_time.parse(cur_time);
		java.util.Date ct_time02 = oc_time.parse(ct_time);
		int wolil02 = Integer.parseInt(wolil.replace("-", ""));
		int il02 = Integer.parseInt(il);

		String ct_mmtime = user_comp_time.substring(14, 16);
		String cur_mmtime = cur_time.substring(3, 5);
		if (Integer.parseInt(ct_mmtime) - Integer.parseInt(cur_mmtime) > 2) {

			map.put("abnormal_cd", "66");
			map.put("abnormal_desc", "잘못된 주문완료 시간입니다.");
			map.put("client_ip", clientIp());
			forexDAO.insert_abnormal_his(map);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "66");
			resultMap.put("text", "잘못된 주문완료 시간입니다.");
			resultMap.put("login", "66");
			return resultMap;
		}

		List<Map<String, Object>> oc_arr = new ArrayList();
		oc_arr = forexDAO.select_openclose(map);

		if (oc_arr != null) {
			for (int i = 0; i < oc_arr.size(); i++) {
				if ("W".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= yoil
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= yoil) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "61");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

				if ("Y".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= wolil02
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= wolil02) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "61");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

				if ("M".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= il02
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= il02) {
						if (cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& cur_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "61");
							resultMap.put("text", "폐장시간입니다.");
							resultMap.put("login", "61");
							return resultMap;
						}
					}
				}

			}
		}

		if (oc_arr != null) {
			for (int i = 0; i < oc_arr.size(); i++) {
				if ("W".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= ct_yoil
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= ct_yoil) {
						if (ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "63");
							resultMap.put("text", "선택된 완료 시간은 폐장후입니다.");
							resultMap.put("login", "62");
							return resultMap;
						}
					}
				}

				if ("Y".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= ct_wolil
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= ct_wolil) {
						if (ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "63");
							resultMap.put("text", "선택된 완료 시간은 폐장후입니다.");
							resultMap.put("login", "63");
							return resultMap;
						}
					}
				}

				if ("M".equals(oc_arr.get(i).get("TURM").toString())) {
					if (Integer.parseInt(oc_arr.get(i).get("STARTDAYCODE").toString()) <= ct_il
							&& Integer.parseInt(oc_arr.get(i).get("ENDDAYCODE").toString()) >= ct_il) {
						if (ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("STARTTIME").toString())) > 0
								&& ct_time02.compareTo(oc_time.parse(oc_arr.get(i).get("ENDTIME").toString())) < 0) {
							resultMap.put("successYN", "N");
							resultMap.put("result_cd", "63");
							resultMap.put("text", "선택된 완료 시간은 폐장후입니다.");
							resultMap.put("login", "63");
							return resultMap;
						}
					}
				}

			}
		}

		// long limit_time = time+1000*60*Integer.parseInt(term.toString());
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		SimpleDateFormat input_time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		SimpleDateFormat input_time2 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);
		SimpleDateFormat minute_time = new SimpleDateFormat("yyyyMMddHHmm");
		SimpleDateFormat minute = new SimpleDateFormat("mm");
		String str_time = dayTime.format(new Date(time));
		String date_time = input_time2.format(new Date(time));
		String limit_mm = minute.format(new Date(time));

		int limit_int = Integer.parseInt(limit_mm) % 2;
		// String input_comp_time = dayTime.format(new Date(comp_time));

		// String user_id = request.getParameter("USER_ID");
		String order_money = request.getParameter("ORDER_MONEY");
		String callput = request.getParameter("CALLPUT");
		String event = request.getParameter("EVENT");
		String option_type = request.getParameter("OPTION_TYPE");
		String game_type = request.getParameter("GAME_TYPE");

		String mm = user_comp_time.substring(0, 2);
		String dd = user_comp_time.substring(3, 5);
		String yyyy = user_comp_time.substring(6, 10);
		String hh = user_comp_time.substring(11, 13);
		String mi = user_comp_time.substring(14, 16);
		String ss = user_comp_time.substring(17, 19);

		String yyyymmddhhmiss = yyyy + mm + dd + hh + mi + ss;
		map.put("option", event);
		map.put("user_id", user_id);
		map.put("hh", hh);

		map.put("bet_turn", option_type + yyyy + mm + dd + hh + mi);

		java.util.Date c_time = input_time.parse(yyyymmddhhmiss);
		long limit_time = c_time.getTime() - 10000 * 6;
		long user_c_time = c_time.getTime();
		String l_time = dayTime.format(limit_time);
		String uc_time = dayTime.format(user_c_time);

		java.util.Date input_current_time = dayTime.parse(str_time);
		java.util.Date input_comp_time = dayTime.parse(uc_time);

		map.put("comp_time", input_comp_time);

		java.util.Date compare_limit_time = dayTime.parse(l_time);
		java.util.Date compare_current_time = dayTime.parse(str_time);
		// java.util.Date compare_user_comp_time =
		// dayTime.parse(yyyymmddhhmiss);

		// int result_time_compare =
		// compare_current_time.compareTo(compare_limit_time);
		log.debug("order time================");
		log.debug(compare_current_time);
		log.debug(c_time);
		int result_time_compare = compare_current_time.compareTo(c_time);

		Map<String, Object> moneyMap = new HashMap<String, Object>();

		Map<String, Object> rateInfoMap = forexDAO.selectCommonRate(map);
		int rate_ctr = Integer.parseInt(rateInfoMap.get("RATE_CTR").toString());
		BigDecimal min_invest = (BigDecimal) rateInfoMap.get("MIN_INVEST");
		BigDecimal max_invest = (BigDecimal) rateInfoMap.get("MAX_INVEST");

		String limit_status = rateInfoMap.get("LIMIT_STATUS").toString();
		int limit_cnt = Integer.parseInt(rateInfoMap.get("LIMIT_CNT").toString());
		int limit_pct = Integer.parseInt(rateInfoMap.get("LIMIT_PCT").toString());
		BigDecimal limit_invest = (BigDecimal) rateInfoMap.get("LIMIT_INVEST");
		int emergent_rate = Integer.parseInt(rateInfoMap.get("EMERGENT_RATE").toString());
		int comp_cnt = Integer.parseInt(rateInfoMap.get("COMP_CNT").toString());
		String rate_status = rateInfoMap.get("RATE_STATUS").toString();

		if (rate_status.equals("Y")) {
			resultMap.put("order_YN", "N");
			resultMap.put("alert", "62");
			resultMap.put("alert_txt", "현재 주문 불가능한 종목입니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "62");
			resultMap.put("text", "현재 주문 불가능한 종목입니다.");
			return resultMap;
		}

		if (limit_cnt <= comp_cnt && limit_status.equals("Y")) {
			resultMap.put("order_YN", "N");
			resultMap.put("alert", "4");
			resultMap.put("alert_txt", "주문건수가 최대 입니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "45");
			resultMap.put("text", "주문건수가 최대 입니다.");
			return resultMap;
		}

		Map<String, Object> orderHisMap = forexDAO.selectOrderHis(map);

		BigDecimal callinv = (BigDecimal) orderHisMap.get("CALLINV");
		BigDecimal putinv = (BigDecimal) orderHisMap.get("PUTINV");
		BigDecimal totinv = (BigDecimal) orderHisMap.get("TOTINV");

		if (totinv.compareTo(limit_invest) >= 0 && limit_status.equals("Y")) {
			if (callput.equals("CALL") && ((callinv.divide(totinv)).multiply(new BigDecimal("100"))
					.compareTo(new BigDecimal(limit_pct)) >= 0)) {
				resultMap.put("order_YN", "N");
				resultMap.put("alert", "5");
				resultMap.put("alert_txt", "CALL 가능 건수가 없습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "46");
				resultMap.put("text", "CALL 가능 건수가 없습니다.");
				return resultMap;
			}

			if (callput.equals("PUT") && ((putinv.divide(totinv)).multiply(new BigDecimal("100"))
					.compareTo(new BigDecimal(limit_pct)) >= 0)) {
				resultMap.put("order_YN", "N");
				resultMap.put("alert", "6");
				resultMap.put("alert_txt", "PUT 가능 건수가 없습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "47");
				resultMap.put("text", "PUT 가능 건수가 없습니다.");
				return resultMap;
			}
		}

		BigDecimal total = (BigDecimal) rateInfoMap.get("TOTAL");
		BigDecimal level_1 = (BigDecimal) rateInfoMap.get("LEVEL1");
		BigDecimal level_2 = (BigDecimal) rateInfoMap.get("LEVEL2");
		BigDecimal level_3 = (BigDecimal) rateInfoMap.get("LEVEL3");
		BigDecimal level_4 = (BigDecimal) rateInfoMap.get("LEVEL4");
		BigDecimal level_5 = (BigDecimal) rateInfoMap.get("LEVEL5");
		BigDecimal level_6 = (BigDecimal) rateInfoMap.get("LEVEL6");
		BigDecimal level_7 = (BigDecimal) rateInfoMap.get("LEVEL7");
		BigDecimal level_8 = (BigDecimal) rateInfoMap.get("LEVEL8");
		BigDecimal level_9 = (BigDecimal) rateInfoMap.get("LEVEL9");
		BigDecimal level_10 = (BigDecimal) rateInfoMap.get("LEVEL10");

		String recommend_id = (String) rateInfoMap.get("RECOMMEND_ID");
		String recommend_level = (String) rateInfoMap.get("RECOMMEND_LEVEL");
		String user_level = (String) rateInfoMap.get("USER_LEVEL");

		if (new BigDecimal(order_money).compareTo(new BigDecimal("0")) < 0) {

			map.put("abnormal_cd", "3");
			map.put("abnormal_desc", "주문금액이 없습니다.");
			map.put("client_ip", clientIp());
			forexDAO.insert_abnormal_his(map);

			resultMap.put("order_YN", "N");
			resultMap.put("alert", "3");
			resultMap.put("alert_txt", "주문금액이 없습니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "21");
			resultMap.put("text", "주문금액이 없습니다.");
		}

		if (new BigDecimal(order_money).compareTo(min_invest) < 0
				|| new BigDecimal(order_money).compareTo(max_invest) > 0 && limit_status.equals("Y")) {
			resultMap.put("order_YN", "N");
			resultMap.put("alert", "3");
			resultMap.put("alert_txt", "주문가능금액은 " + min_invest.toString() + " ~ " + max_invest.toString() + "입니다.");
			resultMap.put("practice_amount", "");
			resultMap.put("tot_amount", "");
			resultMap.put("actkey", "");
			resultMap.put("order_time", "");
			resultMap.put("comp_time", "");
			resultMap.put("game_type", game_type);

			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "21");
			resultMap.put("text", "주문가능금액 범위 밖입니다.");
		} else {
			if (result_time_compare < 0 && limit_int == 0) {
				String actkey = user_id + order_money + event + callput + date_time;

				String returnfx = "";
				String replaceoption = event.substring(0, 3) + "_" + event.substring(3, 6);
				String url = "https://api-fxtrade.oanda.com/v1/candles?instrument=" + replaceoption
						+ "&count=1&candleFormat=midpoint&granularity=D&dailyAlignment=0&alignmentTimezone=Asia%2FSeoul";

				returnfx = urlCall(url);

				JSONObject jsonObject = (JSONObject) JSONValue.parse(returnfx);
				JSONArray jsonArray = (JSONArray) jsonObject.get("candles");

				JSONObject jobject = (JSONObject) jsonArray.get(0);
				String fx_rate = jobject.get("closeMid").toString();
				String fx_time = jobject.get("time").toString();

				BigDecimal x_order_money = new BigDecimal(order_money);
				BigDecimal z_order_money = x_order_money.multiply(total).multiply(new BigDecimal("0.01"));
				;

				BigDecimal z_level1_money = null;
				BigDecimal z_level2_money = null;
				BigDecimal z_level3_money = null;
				BigDecimal z_level4_money = null;
				BigDecimal z_level5_money = null;
				BigDecimal z_level6_money = null;
				BigDecimal z_level7_money = null;
				BigDecimal z_level8_money = null;
				BigDecimal z_level9_money = null;
				BigDecimal z_level10_money = null;

				Map<String, Object> limitcomMap = forexDAO.selectLimitCom(map);
				BigDecimal totdep = new BigDecimal("0");
				if (limitcomMap != null) {
					totdep = (BigDecimal) limitcomMap.get("TOTDEP");
				}
				BigDecimal totcom = new BigDecimal("0");
				if (limitcomMap != null) {
					totcom = (BigDecimal) limitcomMap.get("TOTCOM");
				}
				BigDecimal limitcom = totdep.subtract(totcom);

				BigDecimal z_totorder_money = null;
				
				log.debug("zorder_limitcom");
				log.debug(z_totorder_money);
				log.debug(limitcom);
				
				log.debug("xordermoney");
				log.debug(x_order_money);
				if (z_order_money.compareTo(limitcom) <= 0) {

					z_level1_money = x_order_money.multiply(level_1).multiply(new BigDecimal("0.01"));
					z_level2_money = x_order_money.multiply(level_2).multiply(new BigDecimal("0.01"));
					z_level3_money = x_order_money.multiply(level_3).multiply(new BigDecimal("0.01"));
					z_level4_money = x_order_money.multiply(level_4).multiply(new BigDecimal("0.01"));
					z_level5_money = x_order_money.multiply(level_5).multiply(new BigDecimal("0.01"));
					z_level6_money = x_order_money.multiply(level_6).multiply(new BigDecimal("0.01"));
					z_level7_money = x_order_money.multiply(level_7).multiply(new BigDecimal("0.01"));
					z_level8_money = x_order_money.multiply(level_8).multiply(new BigDecimal("0.01"));
					z_level9_money = x_order_money.multiply(level_9).multiply(new BigDecimal("0.01"));
					z_level10_money = x_order_money.multiply(level_10).multiply(new BigDecimal("0.01"));
					;
					log.debug("z_level_money  1");
					log.debug(z_level1_money);
					log.debug(z_level2_money);
					log.debug(z_level3_money);
					log.debug(z_level4_money);
					log.debug(z_level5_money);
					log.debug(z_level6_money);
					log.debug(z_level7_money);
					log.debug(z_level8_money);
					log.debug(z_level9_money);
					log.debug(z_level10_money);
					z_totorder_money = x_order_money.add(z_order_money);
					map.put("totorder_money", z_totorder_money);

					moneyMap.put("total", z_order_money);
				} else {
					// z_level1_money = limitcom.multiply(level_1).multiply(new
					// BigDecimal("100").divide(total));
					// z_level2_money = limitcom.multiply(level_2).multiply(new
					// BigDecimal("100").divide(total));
					// z_level3_money = limitcom.multiply(level_3).multiply(new
					// BigDecimal("100").divide(total));
					// z_level4_money = limitcom.multiply(level_4).multiply(new
					// BigDecimal("100").divide(total));
					// z_level5_money = limitcom.multiply(level_5).multiply(new
					// BigDecimal("100").divide(total));
					// z_level6_money = limitcom.multiply(level_6).multiply(new
					// BigDecimal("100").divide(total));
					// z_level7_money = limitcom.multiply(level_7).multiply(new
					// BigDecimal("100").divide(total));
					// z_level8_money = limitcom.multiply(level_8).multiply(new
					// BigDecimal("100").divide(total));
					// z_level9_money = limitcom.multiply(level_9).multiply(new
					// BigDecimal("100").divide(total));
					// z_level10_money =
					// limitcom.multiply(level_10).multiply(new
					// BigDecimal("100").divide(total));

					z_level1_money = limitcom.multiply(level_1).multiply(new BigDecimal("0.2"));
					z_level2_money = limitcom.multiply(level_2).multiply(new BigDecimal("0.2"));
					z_level3_money = limitcom.multiply(level_3).multiply(new BigDecimal("0.2"));
					z_level4_money = limitcom.multiply(level_4).multiply(new BigDecimal("0.2"));
					z_level5_money = limitcom.multiply(level_5).multiply(new BigDecimal("0.2"));
					z_level6_money = limitcom.multiply(level_6).multiply(new BigDecimal("0.2"));
					z_level7_money = limitcom.multiply(level_7).multiply(new BigDecimal("0.2"));
					z_level8_money = limitcom.multiply(level_8).multiply(new BigDecimal("0.2"));
					z_level9_money = limitcom.multiply(level_9).multiply(new BigDecimal("0.2"));
					z_level10_money = limitcom.multiply(level_10).multiply(new BigDecimal("0.2"));
					
					log.debug("z_level_money  2");
					log.debug(z_level1_money);
					log.debug(z_level2_money);
					log.debug(z_level3_money);
					log.debug(z_level4_money);
					log.debug(z_level5_money);
					log.debug(z_level6_money);
					log.debug(z_level7_money);
					log.debug(z_level8_money);
					log.debug(z_level9_money);
					log.debug(z_level10_money);

					z_totorder_money = x_order_money.add(limitcom);
					map.put("totorder_money", z_totorder_money);

					moneyMap.put("total", limitcom);
				}

				moneyMap.put("1", z_level1_money);
				moneyMap.put("2", z_level2_money);
				moneyMap.put("3", z_level3_money);
				moneyMap.put("4", z_level4_money);
				moneyMap.put("5", z_level5_money);
				moneyMap.put("6", z_level6_money);
				moneyMap.put("7", z_level7_money);
				moneyMap.put("8", z_level8_money);
				moneyMap.put("9", z_level9_money);
				moneyMap.put("10", z_level10_money);

				map.put("actkey", actkey);
				map.put("user_id", user_id);
				// map.put("invest_money",
				// x_order_money.subtract(z_order_money));
				map.put("invest_money", x_order_money);
				map.put("callput", callput);
				map.put("event", event);
				map.put("order_time", input_current_time);
				// map.put("comp_time", input_comp_time);
				map.put("comp_time", input_comp_time);
				map.put("option_type", option_type);
				map.put("game_type", game_type);
				map.put("start_rate", fx_rate);
				map.put("updatedate", input_current_time);
				// ------------------------//

				// Map<String, Object> tempMap = forexDAO.selectRate(map);
				BigDecimal rate = (BigDecimal) rateInfoMap.get("RATE");
				
				if(emergent_rate>0){
					map.put("ext_rate", emergent_rate);
				}else{
					map.put("ext_rate", rate);
				}
				

				// -------------------------//
				resultMap.put("start_rate", fx_rate);

				Map<String, Object> userMap = forexDAO.selectUserDetail(map);
				BigDecimal tot_amount = (BigDecimal) userMap.get("TOT_AMOUNT");
				BigDecimal practice_amount = (BigDecimal) userMap.get("PRACTICE_AMOUNT");
				BigDecimal possible_amount = new BigDecimal(0);
				BigDecimal possible_money = new BigDecimal(0);
				if (game_type.equals("R")) {
					if (userMap.get("USER_NAME") == null) {
						resultMap.put("order_YN", "N");
						resultMap.put("alert", "7");
						resultMap.put("alert_txt", "계좌 등록 후 주문 가능합니다.");

						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "58");
						resultMap.put("text", "계좌 등록 후 주문 가능합니다.");

						return resultMap;
					}

					map.put("possible_amount", 0);
					// possible_money = possible_amount.subtract(new
					// BigDecimal(order_money));
					if (userMap.get("POSSIBLE_AMOUNT") != null) {
						possible_amount = (BigDecimal) userMap.get("POSSIBLE_AMOUNT");
						// possible_money = possible_amount.subtract(new
						// BigDecimal(order_money));
						possible_money = possible_amount.subtract(z_totorder_money);
						if (possible_money.compareTo(new BigDecimal("0")) < 0) {
							map.put("possible_money", 0);
						} else {
							map.put("possible_money", possible_money);
						}

					}

					map.put("last_amount", tot_amount);

					// ------------------------//

					// �ֹ� �� �ݾ� - �ֹ��ݾ� ===> �Ѻ����ݾ�
					// BigDecimal cur_money = tot_amount.subtract(new
					// BigDecimal(order_money));
					BigDecimal cur_money = tot_amount.subtract(z_totorder_money);
					map.put("tot_amount", cur_money);

					// if (tot_amount.compareTo(new BigDecimal(order_money)) ==
					// -1) {
					if (tot_amount.compareTo(z_totorder_money) < 0) {

						map.put("abnormal_cd", "22");
						map.put("abnormal_desc", "주문금액이 보유 그램보다 큼");
						map.put("client_ip", clientIp());
						forexDAO.insert_abnormal_his(map);

						resultMap.put("order_YN", "N");
						resultMap.put("alert", "1");
						resultMap.put("alert_txt", "주문금액이 보유 그램보다 큼");

						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "22");
						resultMap.put("text", "주문금액이 보유 금액보다 큽니다.");

					} else {
						
						if (possible_amount.compareTo(z_totorder_money) <= 0) {
							
							BigDecimal return_bonus = z_totorder_money.subtract(possible_amount);
							map.put("return_bonus", return_bonus);
							forexDAO.update_return_bonus(map);
						}
						// user_amount �ֹ����� �ݾ� ������Ʈ
						map.put("commission_gubun", "point");
						map.put("last_act", "주문");
						forexDAO.update_last_amount(map);
						// user_bet insert
						forexDAO.insert_user_order02(map);
						// -----------------------//

						BigDecimal update_money_tmp = null;
						BigDecimal update_money_tmp2 = null;
						BigDecimal update_money_tmp3 = null;
						BigDecimal update_money = null;
						int update_money_tocom = 0;
						String update_id = null;
						
						log.debug("====================point1");
						if (user_level != null) {
							log.debug("====================point2");
							log.debug("====================user_level : "+user_level);
							update_money_tmp = (BigDecimal) moneyMap.get(user_level);
							update_money_tmp2 = update_money_tmp.multiply(new BigDecimal("0.033"));
							// update_money_tmp3 = update_money_tmp2.round(new
							// MathContext(1, RoundingMode.UP));
							update_money_tmp3 = new BigDecimal(Math.ceil(update_money_tmp2.doubleValue()));
							update_money_tocom = update_money_tmp3.intValue();
							update_money = update_money_tmp.subtract(new BigDecimal(update_money_tocom));
							log.debug("====================point3");
							log.debug("====================update_money : "+update_money);
							update_id = user_id;
							map.put("update_money", update_money);
							map.put("update_id", update_id);
							map.put("update_level", user_level);
							map.put("update_money_tocom", update_money_tocom);

							if (update_money.compareTo(new BigDecimal("0")) > 0) {
								
								log.debug("====================point4");
								
								
								map.put("last_act", "tax");
								map.put("commission_gubun", "tax");
								forexDAO.update_commission_tocom(map);
								forexDAO.insert_commission_his_tocom(map);

								map.put("last_act", "수당");
								map.put("commission_gubun", "point");
								forexDAO.update_commission(map);
								forexDAO.insert_commission_his(map);
							}
						}

						log.debug("====================point5");
						log.debug("====================recommend_level : "+recommend_level);
						if (recommend_level != null && !recommend_level.equals("0")) {
							update_money_tmp = (BigDecimal) moneyMap.get(recommend_level);
							log.debug("====================point6");
							log.debug("====================update_money : "+update_money_tmp);
							// update_money_tocom =
							// update_money_tmp.multiply(new
							// BigDecimal("0.033")).ROUND_UP;
							// update_money = update_money_tmp.subtract(new
							// BigDecimal(update_money_tocom));

							update_money_tmp2 = update_money_tmp.multiply(new BigDecimal("0.033"));
							// update_money_tmp3 = update_money_tmp2.round(new
							// MathContext(1, RoundingMode.UP));
							update_money_tmp3 = new BigDecimal(Math.ceil(update_money_tmp2.doubleValue()));
							update_money_tocom = update_money_tmp3.intValue();
							update_money = update_money_tmp.subtract(new BigDecimal(update_money_tocom));
							update_id = user_id;

							map.put("update_money", update_money);
							map.put("update_id", update_id);
							map.put("update_level", user_level);
							map.put("update_money_tocom", update_money_tocom);

							update_id = recommend_id;

							map.put("update_money", update_money);
							map.put("update_id", recommend_id);
							map.put("update_level", recommend_level);
							map.put("update_money_tocom", update_money_tocom);

							if (update_money != null) {
								if (update_money.compareTo(new BigDecimal("0")) > 0) {

									map.put("last_act", "tax");
									map.put("commission_gubun", "tax");
									forexDAO.update_commission_tocom(map);
									forexDAO.insert_commission_his_tocom(map);

									map.put("last_act", "수당");
									map.put("commission_gubun", "point");
									forexDAO.update_commission(map);
									forexDAO.insert_commission_his(map);
								}
							}
							for (int y = Integer.parseInt(recommend_level) - 1; y > 0; y--) {
								map.put("level_id", recommend_id);
								map.put("level_level", y);
								// update_money_tmp =
								// (BigDecimal)moneyMap.get(String.valueOf(Integer.parseInt(recommend_level)-1));
								update_money_tmp = (BigDecimal) moneyMap.get(String.valueOf(y));
								// update_money_tmp2 =
								// update_money_tmp.multiply(new
								// BigDecimal("0.033"))
								// update_money_tocom =
								// update_money_tmp.multiply(new
								// BigDecimal("0.033")).ROUND_UP;
								// update_money = update_money_tmp.subtract(new
								// BigDecimal(update_money_tocom));

								update_money_tmp2 = update_money_tmp.multiply(new BigDecimal("0.033"));
								update_money_tmp3 = new BigDecimal(Math.ceil(update_money_tmp2.doubleValue()));
								// update_money_tmp3 =
								// update_money_tmp2.round(new MathContext(1,
								// RoundingMode.UP));
								update_money_tocom = update_money_tmp3.intValue();
								update_money = update_money_tmp.subtract(new BigDecimal(update_money_tocom));
								update_id = user_id;
								map.put("update_money", update_money);
								map.put("update_id", update_id);
								map.put("update_level", user_level);
								map.put("update_money_tocom", update_money_tocom);
								
								//levelid : royal updateid = sch updatelevel 10
								
								update_id = forexDAO.select_level_id(map);

								if (update_id != null) {
									map.put("update_id", update_id);
									map.put("update_money", update_money);
									map.put("update_level", y - 1);
									map.put("update_money_tocom", update_money_tocom);

									if (update_money != null) {
										if (update_money.compareTo(new BigDecimal("0")) > 0) {

											map.put("last_act", "tax");
											map.put("commission_gubun", "tax");
											forexDAO.update_commission_tocom(map);
											forexDAO.insert_commission_his_tocom(map);

											map.put("last_act", "수당");
											map.put("commission_gubun", "point");
											forexDAO.update_commission(map);
											forexDAO.insert_commission_his(map);
										}
									}
								}
								if(update_id!=null){
									recommend_id = update_id;
								}
							}
						}

						if (forexDAO.select_user_bonus(map) != null) {
							List<Map<String, Object>> arr = new ArrayList();
							arr = forexDAO.select_user_bonus(map);
							BigDecimal jumun_money = x_order_money;
							for (int i = 0; i < arr.size(); i++) {
								if (jumun_money.compareTo(new BigDecimal("0")) > 0) {
									int idx = (Integer) arr.get(i).get("IDX");
									map.put("idx", idx);
									BigDecimal mission = new BigDecimal(arr.get(i).get("MISSION_MONEY").toString()); // 3000
									BigDecimal chun_money = new BigDecimal(
											arr.get(i).get("PROCESSED_BONUS").toString()); // 0
									BigDecimal chul_money = mission.subtract(chun_money); // 3000-0

									if (chul_money.compareTo(jumun_money) >= 0) {
										map.put("chul_money", jumun_money);
										jumun_money = new BigDecimal("0");
									} else {
										map.put("chul_money", chul_money);
										jumun_money = jumun_money.subtract(chul_money);
									}

									forexDAO.update_bonus(map);
								}
							}

						}

						Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);
						if (userBonusMap != null) {
							resultMap.put("mission_money",
									new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
							resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
							resultMap.put("processed_bonus",
									new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
						} else {
							resultMap.put("mission_money", null);
							resultMap.put("input_bonus", null);
							resultMap.put("processed_bonus", null);
						}

						resultMap.put("order_YN", "Y");
						resultMap.put("alert", "0");
						resultMap.put("alert_txt", "주문성공");
						resultMap.put("tot_amount", cur_money);
						resultMap.put("practice_amount", practice_amount);
						resultMap.put("actkey", actkey);
						// resultMap.put("order_time", date_time);
						resultMap.put("order_time", str_time);
						resultMap.put("comp_time", user_comp_time);
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "Y");
						resultMap.put("result_cd", "53");
						resultMap.put("text", "주문성공");

					}
				} else if (game_type.equals("P")) {

					BigDecimal cur_money = practice_amount.subtract(new BigDecimal(order_money));
					map.put("practice_amount", cur_money);

					if (practice_amount.compareTo(new BigDecimal(order_money)) == -1) {

						map.put("abnormal_cd", "22");
						map.put("abnormal_desc", "주문금액이 보유 그램보다 큼");
						map.put("client_ip", clientIp());
						forexDAO.insert_abnormal_his(map);

						resultMap.put("order_YN", "N");
						resultMap.put("alert", "1");
						resultMap.put("alert_txt", "주문금액이 보유 그램보다 큼");
						resultMap.put("practice_amount", "");
						resultMap.put("tot_amount", "");
						resultMap.put("actkey", "");
						resultMap.put("order_time", "");
						resultMap.put("comp_time", "");
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "N");
						resultMap.put("result_cd", "22");
						resultMap.put("text", "주문금액이 보유 금액보다 큽니다.");

					} else {
						// user_bet insert
						map.put("last_act", "연습 주문");
						forexDAO.update_last_practice_amount(map);
						forexDAO.insert_user_order_practice02(map);
						// -----------------------//
						
						Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);
						if (userBonusMap != null) {
							resultMap.put("mission_money",
									new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
							resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
							resultMap.put("processed_bonus",
									new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
						} else {
							resultMap.put("mission_money", null);
							resultMap.put("input_bonus", null);
							resultMap.put("processed_bonus", null);
						}

						resultMap.put("order_YN", "Y");
						resultMap.put("alert", "0");
						resultMap.put("alert_txt", "주문성공");
						resultMap.put("practice_amount", cur_money);
						resultMap.put("tot_amount", tot_amount);
						resultMap.put("actkey", actkey);
						// resultMap.put("order_time", date_time);
						resultMap.put("order_time", str_time);
						resultMap.put("comp_time", user_comp_time);
						resultMap.put("game_type", game_type);

						resultMap.put("successYN", "Y");
						resultMap.put("result_cd", "53");
						resultMap.put("text", "주문성공");

					}
				} else {
					resultMap.put("order_YN", "N");
					resultMap.put("alert", "3");
					resultMap.put("alert_txt", "game_type 이 정의 되지 않음");
					resultMap.put("practice_amount", "");
					resultMap.put("tot_amount", "");
					resultMap.put("actkey", "");
					resultMap.put("order_time", "");
					resultMap.put("comp_time", "");
					resultMap.put("game_type", game_type);

					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "23");
					resultMap.put("text", "game type 이 정의 되지 않았습니다.");
				}
			} else {

				resultMap.put("order_YN", "N");
				resultMap.put("alert", "2");
				resultMap.put("alert_txt", "주문가능한시간이 지났습니다.");
				resultMap.put("practice_amount", "");
				resultMap.put("tot_amount", "");
				resultMap.put("actkey", "");
				resultMap.put("order_time", "");
				resultMap.put("comp_time", "");
				resultMap.put("game_type", game_type);

				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "24");
				resultMap.put("text", "주문가능한시간이 지났습니다.");
			}
		}

		return resultMap;
	}

	@Override
	public void batch_fxdata_backup(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// long time = System.currentTimeMillis();
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		String str_time = dayTime.format(new Date(time));

		java.util.Date c_time = dayTime.parse(str_time);
		long limit_time = c_time.getTime() - 100000;
		String cur_time = dayTime.format(limit_time);

		try {
			map.put("cur_time", str_time);
			forexDAO.batch_fxdata_backup(map);
			forexDAO.batch_delete_fxdata(map);
		} catch (Exception e) {
		}

	}

	@Override
	public Map<String, Object> receive_myorder(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		String user_id = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		arr = forexDAO.receive_myorder(map);

		if (arr.size() > 0) {
			resultMap.put("receive_myorder", arr);
		} else {
			resultMap.put("receive_myorder", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receive_mydecomplete_order(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		String user_id = null;
		String event = null;
		
		long cur_time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);		
		String now_time = dayTime.format(cur_time);
		
		String cur_mm = now_time.substring(14, 16);
		map.put("cur_mm", cur_mm);

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("event") != null) {
			event = request.getParameter("event");
			map.put("event", event);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "종목을 입력하세요");
			return resultMap;
		}

		arr = forexDAO.receive_mydecomplete_order(map);

		if (arr.size() > 0) {
			resultMap.put("receive_mydecomplete_order", arr);
		} else {
			resultMap.put("receive_mydecomplete_order", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receive_deposit_his(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		String user_id = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		arr = forexDAO.receive_deposit_his(map);

		if (arr.size() > 0) {
			resultMap.put("receive_deposit_his", arr);
		} else {
			resultMap.put("receive_deposit_his", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receive_client_notice(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> noticeMap = new HashMap<String, Object>();		
		List<Map<String, Object>> arr = new ArrayList();

		long cur_time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
		String now_time = dayTime.format(cur_time);
		map.put("now_date", now_time);
//		arr = forexDAO.receive_client_notice(map);
		noticeMap = forexDAO.receive_client_notice(map);
		
		String s_date = null; 
		String e_date = null; 
		String content = null;
		if(noticeMap!=null){
			s_date = noticeMap.get("S_DATE").toString();
			e_date = noticeMap.get("E_DATE").toString();
			content = noticeMap.get("CONTENTS").toString();
			
			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "00");
			resultMap.put("text", "success");
			
			resultMap.put("s_date", s_date);
			resultMap.put("e_date", e_date);
			resultMap.put("content", content);
		}else{
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "67");
			resultMap.put("text", "공지사항이 없습니다.");
			
			resultMap.put("s_date", s_date);
			resultMap.put("e_date", e_date);
			resultMap.put("content", content);
			
		}
/*
		if (arr.size() > 0) {
			resultMap.put("receive_client_notice", arr);
			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "00");
			resultMap.put("text", "success");
		} else {
			resultMap.put("receive_client_notice", null);
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "67");
			resultMap.put("text", "공지사항이 없습니다.");
		}
*/
		return resultMap;
	}

	@Override
	public Map<String, Object> cron_update_refill_practice(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// long cur_time = curtime();
		// SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
		// String now_time = dayTime.format(cur_time);
		// map.put("now_date", now_time);
		forexDAO.cron_update_refill_practice(map);

		// resultMap.put("receive_client_notice", null);
		// resultMap.put("successYN", "Y");
		// resultMap.put("result_cd","67");
		// resultMap.put("text","공지사항이 없습니다.");

		return resultMap;
	}

	@Override
	public Map<String, Object> receive_refund_his(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		String user_id = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		arr = forexDAO.receive_refund_his(map);

		if (arr.size() > 0) {
			resultMap.put("receive_refund_his", arr);
		} else {
			resultMap.put("receive_refund_his", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> change_pw(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		String user_id = null;
		String change_pw = null;
		String phone_number = null;
		String key = null;

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("change_pw") != null) {
			change_pw = request.getParameter("change_pw");
			map.put("change_pw", change_pw);

			boolean bool1 = Pattern.matches("(.*[A-Za-z].*)", change_pw);
			boolean bool2 = Pattern.matches("(.*[0-9].*)", change_pw);

			if (bool1 && bool2) {
				if (change_pw.length() < 8 || change_pw.length() > 13) {
					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "05");
					resultMap.put("text", "패스워드는 8 ~ 13 자리 입니다.");
					return resultMap;
				}
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "06");
				resultMap.put("text", "패스워드는 알파벳,숫자 조합입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "24");
			resultMap.put("text", "변경할 패스워드를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");

			phone_number = phone_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", phone_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "08");
				resultMap.put("text", "전화번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("text", "전화번호를 입력하세요.");
			return resultMap;
		}

		Random random = new Random();
		int confirm_number1 = random.nextInt(10);
		int confirm_number2 = random.nextInt(10);
		int confirm_number3 = random.nextInt(10);
		int confirm_number4 = random.nextInt(10);

		String confirm_number = String.valueOf(confirm_number1) + String.valueOf(confirm_number2)
				+ String.valueOf(confirm_number3) + String.valueOf(confirm_number4);
		map.put("confirm_number", confirm_number);

		long time = curtime() + (1000 * 60 * 3);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String confirm_end_time = dayTime.format(new Date(time));
		map.put("confirm_end_time", confirm_end_time);

		try {
			forexDAO.insert_phone_confirm(map);
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "14");
			resultMap.put("text", e);

			return resultMap;
		}

		try {
			Config.receiver = phone_number;
			Config.sender = "01084034999";
			Config.content = "[fx-eve] 본인확인 인증번호[" + confirm_number + "]를 화면에 입력해주세요.";
			sendsms();
		} catch (Exception e) {
			log.debug(e);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "51");
		resultMap.put("text", "인증번호전송완료");

		return resultMap;
	}
	
	@Override
	public Map<String, Object> change_name(Map<String, Object> map, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String user_id = "";
		String user_name = "";
		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}
		
		if (request.getParameter("user_name") != null) {
			user_name = request.getParameter("user_name");
			boolean val = user_name.matches("^[가-힣a-zA-Z]*$");			
				
			if(val!=true){
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "68");
				resultMap.put("text", "한글,영문만 입력 가능합니다.");
				return resultMap;
			}
			
			map.put("user_name", user_name);
		}else{
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "37");
			resultMap.put("text", "이름을 입력하세요");
			return resultMap;
		}
		
		try {
			forexDAO.update_user_name(map);
			
			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "59");
			resultMap.put("text", "이름이 변경되었습니다.");
			
		} catch (Exception e) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "28");
			resultMap.put("text", e);
			return resultMap;
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> change_pw_confirm(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		String user_id = null;
		String change_pw = null;
		String phone_number = null;
		String confirm_number = null;
		String key = null;

		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			map.put("keyvalue", key);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "23");
			resultMap.put("text", "key 가 없습니다.");
			return resultMap;
		}

		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		if (request.getParameter("change_pw") != null) {
			change_pw = request.getParameter("change_pw");
			map.put("change_pw", change_pw);

			boolean bool1 = Pattern.matches("(.*[A-Za-z].*)", change_pw);
			boolean bool2 = Pattern.matches("(.*[0-9].*)", change_pw);

			if (bool1 && bool2) {
				if (change_pw.length() < 8 || change_pw.length() > 13) {
					resultMap.put("successYN", "N");
					resultMap.put("result_cd", "05");
					resultMap.put("text", "패스워드는 8 ~ 13 자리 입니다.");

					return resultMap;
				}
			} else {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "06");
				resultMap.put("text", "패스워드는 알파벳,숫자 조합입니다.");

				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "24");
			resultMap.put("text", "변경할 패스워드를 입력하세요.");
			return resultMap;
		}

		if (request.getParameter("phone_number") != null) {
			phone_number = request.getParameter("phone_number");

			phone_number = phone_number.replaceAll(match, " ").trim().replaceAll(" ", "");
			boolean bool = Pattern.matches("(.*[0-9].*)", phone_number);

			if (!bool) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "08");
				resultMap.put("text", "전화번호에 문자가 포함될수 없습니다.");
				return resultMap;
			}

			map.put("phone_number", phone_number);

			if (forexDAO.select_user_phone_number(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "25");
				resultMap.put("text", "가입된 전화번호와 입력된 전화번호가 다릅니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "07");
			resultMap.put("text", "전화번호를 입력하세요.");
			return resultMap;
		}

		if (request.getParameter("confirm_number") != null) {
			confirm_number = request.getParameter("confirm_number");
			map.put("confirm_number", confirm_number);
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "26");
			resultMap.put("text", "인증코드가 없습니다.");
			return resultMap;
		}

		if (forexDAO.select_confirm_number(map) != null && forexDAO.select_confirm_number(map).toString().equals("Y")) {

			// 인증성공처리
			try {
				forexDAO.update_confirm(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "27");
				resultMap.put("text", e);
				return resultMap;
			}
			try {
				forexDAO.update_user_pw(map);
			} catch (Exception e) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "28");
				resultMap.put("text", e);
				return resultMap;
			}

			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "54");
			resultMap.put("text", "인증성공 패스워드가 변경되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("D")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "99");
			resultMap.put("text", "인증시간이초과되었습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) != null
				&& forexDAO.select_confirm_number(map).toString().equals("N")) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "98");
			resultMap.put("text", "인증번호가 틀렸습니다.");
			return resultMap;
		} else if (forexDAO.select_confirm_number(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "97");
			resultMap.put("text", "인증번호를 확인할수 없습니다. 다시 진행 하세요.");
			return resultMap;
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deposit_info(Map<String, Object> map, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> arr = new ArrayList();

		String user_id = request.getParameter("id");
		map.put("id", user_id);

		if (request.getParameter("id") != null) {
			user_id = request.getParameter("user_id");
			map.put("user_id", user_id);
			map.put("id", user_id);
			if (forexDAO.duplicate_user_id(map) == null) {
				resultMap.put("successYN", "N");
				resultMap.put("result_cd", "18");
				resultMap.put("text", "존재하지 않는 ID 입니다.");
				return resultMap;
			}
		} else {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "01");
			resultMap.put("text", "아이디를 입력하세요");
			return resultMap;
		}

		arr = forexDAO.deposit_info(map);

		if (arr.size() > 0) {
			resultMap.put("deposit_info", arr);
		} else {
			resultMap.put("deposit_info", null);
		}

		resultMap.put("successYN", "Y");
		resultMap.put("result_cd", "00");
		resultMap.put("text", "success");

		return resultMap;
	}

	@Override
	public Map<String, Object> receive_mission_info(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		long time = curtime();
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		String now_hh = hh.format(new Date(time));
		map.put("hh", now_hh);

		String user_id = "";
		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			map.put("user_id", user_id);
		} else {
			resultMap.put("mission_money", null);
			resultMap.put("input_bonus", null);
			resultMap.put("processed_bonus", null);
			resultMap.put("possible_amount", null);
			resultMap.put("successYN", "N");
			resultMap.put("text", "아이디를 입력하세요");
			resultMap.put("result_cd", "01");
			return resultMap;
		}

		if (forexDAO.duplicate_user_id(map) == null) {
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "18");
			resultMap.put("text", "존재하지 않는 아이디 입니다.");

			resultMap.put("mission_money", null);
			resultMap.put("input_bonus", null);
			resultMap.put("processed_bonus", null);
			resultMap.put("possible_amount", null);

			return resultMap;
		}

		Map<String, Object> userBonusMap = forexDAO.select_user_bonus_sum(map);

		if (userBonusMap != null) {
			resultMap.put("mission_money", new BigDecimal(userBonusMap.get("MISSION_MONEY").toString()));
			resultMap.put("input_bonus", new BigDecimal(userBonusMap.get("INPUT_BONUS").toString()));
			resultMap.put("processed_bonus", new BigDecimal(userBonusMap.get("PROCESSED_BONUS").toString()));
			resultMap.put("possible_amount", new BigDecimal(userBonusMap.get("POSSIBLE_AMOUNT").toString()));
			resultMap.put("successYN", "Y");
			resultMap.put("result_cd", "00");
			resultMap.put("text", "success");
		} else {
			resultMap.put("mission_money", null);
			resultMap.put("input_bonus", null);
			resultMap.put("processed_bonus", null);
			resultMap.put("possible_amount", null);
			resultMap.put("successYN", "N");
			resultMap.put("result_cd", "64");
			resultMap.put("text", "출금가능금액이 없습니다.");
		}
		/*
		 * resultMap.put("successYN", "Y"); resultMap.put("result_cd", "00");
		 * resultMap.put("text", "success");
		 */

		return resultMap;
	}

	public String urlCall(String requesturl) {

		URL url = null;
		String requestMsg = "";
		String line = "";
		BufferedReader input = null;

		try {

			// Request
			url = new URL(requesturl);

			// Response
			input = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((line = input.readLine()) != null) {
				requestMsg += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestMsg;
	}

	public long curtime() {
		long time = 0;
		String ip = serverIp();
		if (ip.equals("192.168.1.111") || ip.equals("192.168.1.125") || ip.equals("192.168.52.1")
				|| ip.equals("127.0.1.1") || ip.equals("112.175.149.32")) {
			time = System.currentTimeMillis();
		} else if (ip.equals("161.202.229.83")) {
			time = System.currentTimeMillis() + (1000 * 60 * 60 * 16) - (1000 * 47);
		} else {
			time = System.currentTimeMillis();
		}

//		return time+29500; // 서버반영시 20170529
//		return time-30000; // 서버반영시 20170529
		// return time-53000; //서버반영시 20170522
		// return time-45000; //서버반영시
		 return time;
	}

	public String serverIp() {

		/*
		 * 인코딩 String [] charSet =
		 * {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"}; for (int
		 * i=0; i<charSet.length; i++) { for (int j=0; j<charSet.length; j++) {
		 * try { System.out.println(i+","+j+"[" + charSet[i] +"," + charSet[j]
		 * +"] = " + new String(user_name.getBytes(charSet[i]), charSet[j])); }
		 * catch (UnsupportedEncodingException e) { e.printStackTrace(); } } }
		 */
		String ip = "";

		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ip;
	}

	public String clientIp() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}

		return ip;
	}

	public void sendsms() {
		String hostname = "api.bluehouselab.com";
		String url = "https://" + hostname + "/smscenter/v1.0/sendsms";

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(hostname, 443, AuthScope.ANY_REALM),
				new UsernamePasswordCredentials(Config.appid, Config.apikey));

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		authCache.put(new HttpHost(hostname, 443, "https"), new BasicScheme());

		// Add AuthCache to the execution context
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);

		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json; charset=utf-8");
			String json = "{\"sender\":\"" + Config.sender + "\",\"receivers\":[\"" + Config.receiver
					+ "\"],\"content\":\"" + Config.content + "\"}";
			StringEntity se = new StringEntity(json, "UTF-8");
			httpPost.setEntity(se);

			HttpResponse httpResponse = client.execute(httpPost, context);
			System.out.println(httpResponse.getStatusLine().getStatusCode());

			InputStream inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = "";
				while ((line = bufferedReader.readLine()) != null)
					System.out.println(line);
				inputStream.close();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getLocalizedMessage());
		} finally {
			client.getConnectionManager().shutdown();
		}

	}

	public int getDateDay(String date, String dateType) throws Exception {

		int day = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		java.util.Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayNum) {
		case 1:
			day = 0;
			break;
		case 2:
			day = 1;
			break;
		case 3:
			day = 2;
			break;
		case 4:
			day = 3;
			break;
		case 5:
			day = 4;
			break;
		case 6:
			day = 5;
			break;
		case 7:
			day = 6;
			break;

		}

		return day;
	}

	public void sleeptime(int i) {
		try {
			Thread.sleep(1000);
			;
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String version(){
		String str = null;
		try {
            URL url = new URL("http://fxeve.com/app/version.txt");
            URLConnection urlConn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));           
            
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                return str;
            }
            
            in.close();
        }catch (UnknownHostException ue){
            System.out.println("파일이 없습니다.");
        }catch (IOException ie) {
        }
		return str;
    }
    /*
    public static String readContentFrom(String textFileName) throws IOException {
        BufferedReader bufferedTextFileReader = new BufferedReader(new FileReader(textFileName));
        StringBuilder contentReceiver = new StringBuilder();
        char[] buf = new char[1024];  
        while (bufferedTextFileReader.read(buf) > 0) {
            contentReceiver.append(buf);
        } 
 
        return contentReceiver.toString();
    } 
*/
	
	@Override
	public Map<String, Object> order_money(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> order_final(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> cron_order_result(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> order_result02(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> cron_order_result_all(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
