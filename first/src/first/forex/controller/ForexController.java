package first.forex.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.forex.service.ForexService;
import first.sample.vo.LoginVO;



@Controller
public class ForexController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "forexService")
	private ForexService forexService;

	
	@RequestMapping(value = "/forex/receivetime.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receivetime(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		// 클라이언트로 전달될 서버타임
//		long time = System.currentTimeMillis()+(1000*60*60*16);
//		long time = curtime()-(1000*47);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/receivetime");

		mv.addObject("cur_time", str);
		
		return mv;
	}
	
	@RequestMapping(value = "/forex/receivefx.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receivefx(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		// 클라이언트로 전달될 서버타임
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		LoginVO loginvo = new LoginVO();
		ModelAndView mv = new ModelAndView("/forex/receivefx");
		Map<String, Object> map = forexService.receivefx(commandMap.getMap(),
				request);

		mv.addObject("map", map.get("map"));
		mv.addObject("rate", map.get("rate"));
		mv.addObject("user_tot_amount", map.get("user_tot_amount"));
		mv.addObject("user_practice_amount", map.get("user_practice_amount"));
		
		mv.addObject("rate_id", map.get("rate_id"));
		mv.addObject("rate_name", map.get("rate_name"));
		mv.addObject("rate_time", map.get("rate_time"));
		mv.addObject("rate_date", map.get("rate_date"));
		mv.addObject("rate_bid", map.get("rate_bid"));
		mv.addObject("rate_ask", map.get("rate_ask"));
		mv.addObject("rate_rate", map.get("rate_rate"));
		
		mv.addObject("callpct", map.get("callpct"));
		mv.addObject("putpct", map.get("putpct"));
		mv.addObject("cur_time", str);
		
		mv.addObject("mission_money", map.get("mission_money"));
		mv.addObject("input_bonus", map.get("input_bonus"));
		mv.addObject("processed_bonus", map.get("processed_bonus"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
	}
	
	@RequestMapping(value = "/forex/receivefx03.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receivefx03(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		/*
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));
*/
		ModelAndView mv = new ModelAndView("/forex/receivefx03");
		Map<String, Object> map = forexService.receivefx03(commandMap.getMap(),
				request);
		
//		mv.addObject("cur_time", str);
		mv.addObject("receivefx03", map.get("receivefx03"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
	}
	
	@RequestMapping(value = "/forex/receive_myorder.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receive_myorder(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/receive_myorder");
		Map<String, Object> map = forexService.receive_myorder(commandMap.getMap(),
				request);
		mv.addObject("receive_myorder", map.get("receive_myorder"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
		
	}
	
	@RequestMapping(value = "/forex/receive_mydecomplete_order.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receive_mydecomplete_order(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/receive_mydecomplete_order");
		Map<String, Object> map = forexService.receive_mydecomplete_order(commandMap.getMap(),
				request);
		mv.addObject("receive_mydecomplete_order", map.get("receive_mydecomplete_order"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
		
	}
	
	@RequestMapping(value = "/forex/receive_deposit_his.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receive_deposit_his(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/receive_deposit_his");
		Map<String, Object> map = forexService.receive_deposit_his(commandMap.getMap(),
				request);
		mv.addObject("receive_deposit_his", map.get("receive_deposit_his"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
		
	}
	
	@RequestMapping(value = "/forex/receive_client_notice.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receive_client_notice(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/receive_client_notice");
		Map<String, Object> map = forexService.receive_client_notice(commandMap.getMap(),
				request);
		mv.addObject("receive_client_notice", map.get("receive_client_notice"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		mv.addObject("s_date", map.get("s_date"));
		mv.addObject("e_date", map.get("e_date"));
		mv.addObject("content", map.get("content"));
		
		return mv;
		
	}
	
	@RequestMapping(value = "/forex/cron_update_refill_practice.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public void cron_update_refill_practice(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		//ModelAndView mv = new ModelAndView("/forex/receive_client_notice");
		Map<String, Object> map = forexService.cron_update_refill_practice(commandMap.getMap(),
				request);
	}
	
	@RequestMapping(value = "/forex/receive_refund_his.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receive_refund_his(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/receive_refund_his");
		Map<String, Object> map = forexService.receive_refund_his(commandMap.getMap(),
				request);
		mv.addObject("receive_refund_his", map.get("receive_refund_his"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		return mv;
		
	}
	
	@RequestMapping(value = "/forex/receivefx02.do")
	// 클라이언트 request 로 fx 데이터 호출하여 데이터 전달
	public ModelAndView receivefx02(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		// 클라이언트로 전달될 서버타임
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		LoginVO loginvo = new LoginVO();
		ModelAndView mv = new ModelAndView("/forex/receivefx02");
		Map<String, Object> map = forexService.receivefx02(commandMap.getMap(),
				request);

		mv.addObject("rate", map.get("rate"));
		mv.addObject("user_tot_amount", map.get("user_tot_amount"));
		mv.addObject("user_practice_amount", map.get("user_practice_amount"));
		mv.addObject("callpct", map.get("callpct"));
		mv.addObject("putpct", map.get("putpct"));
		mv.addObject("cur_time", str);
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}

	// 클라이언트 로그인 처리
	@RequestMapping(value = "/forex/login.do")
	public ModelAndView login(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/login");
		Map<String, Object> map = forexService.loginFlag(commandMap.getMap(),
				request);

		mv.addObject("map", map.get("login"));
		mv.addObject("currenTime", str);
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		mv.addObject("recommend_id", map.get("recommend_id"));
		mv.addObject("bank_cd", map.get("bank_cd"));
		mv.addObject("bank_nm", map.get("bank_nm"));
		mv.addObject("bank_number", map.get("bank_number"));
		mv.addObject("phone_number", map.get("phone_number"));
		mv.addObject("user_name", map.get("user_name"));
		
		
		
		if (map.get("login").equals("Y")) {
			
			mv.addObject("logindata", map.get("map"));
			mv.addObject("loginlastdata", map.get("last_map"));
			//mv.addObject("decomplete_list", map.get("decomplete_list"));
			mv.addObject("amount_yn", map.get("amount_yn"));
			
			mv.addObject("mission_money", map.get("mission_money"));
			mv.addObject("input_bonus", map.get("input_bonus"));
			mv.addObject("processed_bonus", map.get("processed_bonus"));
		}
		return mv;
	}
	
	@RequestMapping(value = "/forex/receive_mission_info.do")
	public ModelAndView receive_mission_info(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/receive_mission_info");
		Map<String, Object> map = forexService.receive_mission_info(commandMap.getMap(),
				request);
		System.out.println("=======deposit controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		mv.addObject("mission_money", map.get("mission_money"));
		mv.addObject("input_bonus", map.get("input_bonus"));
		mv.addObject("processed_bonus", map.get("processed_bonus"));
		mv.addObject("possible_amount", map.get("possible_amount"));
		return mv;
	}
	
	
	@RequestMapping(value = "/forex/apply_deposit.do")
	public ModelAndView apply_deposit(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/apply_deposit");
		Map<String, Object> map = forexService.apply_deposit(commandMap.getMap(),
				request);
		System.out.println("=======deposit controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	@RequestMapping(value = "/forex/apply_refund.do")
	public ModelAndView apply_refund(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/apply_refund");
		Map<String, Object> map = forexService.apply_refund(commandMap.getMap(),
				request);
		System.out.println("=======refund controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	@RequestMapping(value = "/forex/apply_refund_second.do")
	public ModelAndView apply_refund_second(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//		long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/apply_refund_second");
		Map<String, Object> map = forexService.apply_refund_second(commandMap.getMap(),
				request);
		System.out.println("=======refund second controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	//이름 변경
		@RequestMapping(value = "/forex/change_name.do")
		public ModelAndView change_name(CommandMap commandMap, HttpServletRequest request)
				throws Exception {
//						long time = System.currentTimeMillis()+(1000*60*60*16);
			ModelAndView mv = new ModelAndView("/forex/change_name");
			Map<String, Object> map = forexService.change_name(commandMap.getMap(),
					request);
			System.out.println("=======change_name=========");
			System.out.println(map.get("successYN"));

			mv.addObject("successYN", map.get("successYN"));
			mv.addObject("result_cd", map.get("result_cd"));
			mv.addObject("text", map.get("text"));
			return mv;
		}
	
	//패스워드 변경
	@RequestMapping(value = "/forex/change_pw_confirm.do")
	public ModelAndView change_pw_confirm(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//					long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/change_pw_confirm");
		Map<String, Object> map = forexService.change_pw_confirm(commandMap.getMap(),
				request);
		System.out.println("=======change_pw_confirm=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	//패스워드 변경
	@RequestMapping(value = "/forex/change_pw.do")
	public ModelAndView change_pw(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//						long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/change_pw");
		Map<String, Object> map = forexService.change_pw(commandMap.getMap(),
				request);
		System.out.println("=======change_pw=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	
	// 회원가입
	@RequestMapping(value = "/forex/join_first.do")
	public ModelAndView join(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//			long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/join_first");
		Map<String, Object> map = forexService.join_first(commandMap.getMap(),
				request);

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	@RequestMapping(value = "/forex/join_first_confirm.do")
	public ModelAndView join_first_confirm(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//			long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/join_first_confirm");
		Map<String, Object> map = forexService.join_first_confirm(commandMap.getMap(),
				request);
		System.out.println("=======join controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
		
		// 회원가입
		
	@RequestMapping(value = "/forex/join_second_confirm.do")
	public ModelAndView join_second_confirm(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//					long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/join_second_confirm");
		Map<String, Object> map = forexService.join_second_confirm(commandMap.getMap(),
				request);
		System.out.println("=======join second controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}
	
	@RequestMapping(value = "/forex/join_second.do")
	public ModelAndView join_second(CommandMap commandMap, HttpServletRequest request)
			throws Exception {
//					long time = System.currentTimeMillis()+(1000*60*60*16);
		long time = curtime();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));

		ModelAndView mv = new ModelAndView("/forex/join_second");
		Map<String, Object> map = forexService.join_second(commandMap.getMap(),
				request);
		System.out.println("=======join second controller=========");
		System.out.println(map.get("successYN"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		return mv;
	}

	// 사용자 목록 ( 사용하지 않음 )
	@RequestMapping(value = "/forex/userList.do")
	public ModelAndView selectUserList(CommandMap commandMap,HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/userList");

		String input_name = request.getParameter("user_name");
		String input_id = request.getParameter("user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");
		
		log.debug("log debug ================== " +cur_page);
		
		if(column==null){
			column ="USER_NAME"; 
		}
		
		if(sort==null){
			sort ="asc"; 
		}
		
		if(cur_page==null){
			cur_page ="1"; 
		}
		
		List<Map<String, Object>> list = forexService.selectUserList(commandMap
				.getMap(),request);
		mv.addObject("list", list);
		mv.addObject("input_name",input_name);
		mv.addObject("input_id",input_id);
		mv.addObject("column",column);
		mv.addObject("sort",sort);
		mv.addObject("cur_page",cur_page);

		return mv;
	}
	
	// 사용자 목록 ( 사용하지 않음 )
		@RequestMapping(value = "/forex/orderList.do")
		public ModelAndView selectOrderList(CommandMap commandMap,HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView("/forex/orderList");

			String input_name = request.getParameter("user_name");
			String input_id = request.getParameter("user_id");
			String column = request.getParameter("column");
			String sort = request.getParameter("sort");
			String cur_page = request.getParameter("cur_page");
			
			log.debug("log debug ================== " +cur_page);
			
			if(column==null){
				column ="USER_NAME"; 
			}
			
			if(sort==null){
				sort ="asc"; 
			}
			
			if(cur_page==null){
				cur_page ="1"; 
			}
			
			List<Map<String, Object>> list = forexService.selectOrderList(commandMap
					.getMap(),request);
			mv.addObject("list", list);
			mv.addObject("input_name",input_name);
			mv.addObject("input_id",input_id);
			mv.addObject("column",column);
			mv.addObject("sort",sort);
			mv.addObject("cur_page",cur_page);

			return mv;
		}
		
		@RequestMapping(value = "/forex/orderList_pop.do")
		public ModelAndView selectOrderList_pop(CommandMap commandMap,HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView("/forex/orderList_pop");
			
			String input_name = request.getParameter("user_name");
			String input_id = request.getParameter("order_pop_user_id");
			String column = request.getParameter("column");
			String sort = request.getParameter("sort");
			String cur_page = request.getParameter("cur_page");
			
			log.debug("log debug ================== " +cur_page);
			
			if(column==null){
				column ="USER_NAME"; 
			}
			
			if(sort==null){
				sort ="asc"; 
			}
			
			if(cur_page==null){
				cur_page ="1"; 
			}
			
			List<Map<String, Object>> list = forexService.selectOrderList_pop(commandMap
					.getMap(),request);
			mv.addObject("list", list);
			mv.addObject("input_name",input_name);
			mv.addObject("input_id",input_id);
			mv.addObject("column",column);
			mv.addObject("sort",sort);
			mv.addObject("cur_page",cur_page);

			return mv;
		}
		
		@RequestMapping(value = "/forex/depositList_pop.do")
		public ModelAndView selectDepositList_pop(CommandMap commandMap,HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView("/forex/depositList_pop");
			
			String input_name = request.getParameter("user_name");
			String input_id = request.getParameter("deposit_pop_user_id");
			String column = request.getParameter("column");
			String sort = request.getParameter("sort");
			String cur_page = request.getParameter("cur_page");
			
			log.debug("log debug ================== " +cur_page);
			
			if(column==null){
				column ="apply_date"; 
			}
			
			if(sort==null){
				sort ="desc"; 
			}
			
			if(cur_page==null){
				cur_page ="1"; 
			}
			
			List<Map<String, Object>> list = forexService.selectDepositList_pop(commandMap
					.getMap(),request);
			mv.addObject("list", list);
			mv.addObject("input_name",input_name);
			mv.addObject("input_id",input_id);
			mv.addObject("column",column);
			mv.addObject("sort",sort);
			mv.addObject("cur_page",cur_page);

			return mv;
		}

	// 계좌 정보 (사용하지 않음)
	@RequestMapping(value = "/forex/userAccount.do")
	public ModelAndView selectUserAccount(CommandMap commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/forex/userAccount");

		List<Map<String, Object>> list = forexService
				.selectUserAccount(commandMap.getMap());
		mv.addObject("list", list);

		return mv;
	}

	// 사용자 상세정보 (사용하지 않음)
	@RequestMapping(value = "/forex/user_detail_pop.do")
	public ModelAndView selectUserDetail(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/user_detail_pop");
		String user_id = request.getParameter("pop_user_id");
		
		String input_name = request.getParameter("pop_user_name");
		String input_id = request.getParameter("pop_user_id");
		String column = request.getParameter("column");
		String sort = request.getParameter("sort");
		String cur_page = request.getParameter("cur_page");
		
		log.debug("log debug ================== " +cur_page);
		
		if(column==null){
			column ="order_time"; 
		}
		
		if(sort==null){
			sort ="desc"; 
		}
		
		if(cur_page==null){
			cur_page ="1"; 
		}
		
		Map<String, Object> map = forexService.selectUserDetail_pop(
				commandMap.getMap(), request);
		mv.addObject("map", map.get("map"));
		
		mv.addObject("input_name",input_name);
		mv.addObject("input_id",input_id);
		mv.addObject("column",column);
		mv.addObject("sort",sort);
		mv.addObject("cur_page",cur_page);
		
		// mv.addObject("list", map.get("list"));

		return mv;
	}

	
	// 클라이언트 주문 처리
		@RequestMapping(value = "/forex/user_order02.do")
		public ModelAndView user_order02(CommandMap commandMap,
				HttpServletRequest request) throws Exception {

			ModelAndView mv = new ModelAndView("/forex/user_order02");

			Map<String, Object> map = forexService.order_money02(commandMap.getMap(),
					request);
			mv.addObject("game_type", map.get("game_type"));
			mv.addObject("order_YN", map.get("order_YN"));
			mv.addObject("alert", map.get("alert"));
			mv.addObject("alert_txt", map.get("alert_txt"));
			mv.addObject("tot_amount", map.get("tot_amount"));
			mv.addObject("practice_amount", map.get("practice_amount"));
			mv.addObject("actkey", map.get("actkey"));
			mv.addObject("order_time", map.get("order_time"));
			mv.addObject("comp_time", map.get("comp_time"));
			mv.addObject("start_rate", map.get("start_rate"));

			mv.addObject("successYN", map.get("successYN"));
			mv.addObject("result_cd", map.get("result_cd"));
			mv.addObject("text", map.get("text"));
			
			mv.addObject("mission_money", map.get("mission_money"));
			mv.addObject("input_bonus", map.get("input_bonus"));
			mv.addObject("processed_bonus", map.get("processed_bonus"));
			
			log.debug("---------------------------");
			log.debug("mission_money");
			log.debug(map.get("mission_money"));
			log.debug("input_bonus");
			log.debug(map.get("input_bonus"));
			log.debug("processed_bonus");
			log.debug(map.get("processed_bonus"));
			
			// mv.addObject("list", map.get("list"));

			return mv;
		}
	
	// 클라이언트 주문 처리
	@RequestMapping(value = "/forex/user_order.do")
	public ModelAndView user_order(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/user_order");

		Map<String, Object> map = forexService.order_money(commandMap.getMap(),
				request);
		mv.addObject("game_type", map.get("game_type"));
		mv.addObject("order_YN", map.get("order_YN"));
		mv.addObject("alert", map.get("alert"));
		mv.addObject("alert_txt", map.get("alert_txt"));
		mv.addObject("tot_amount", map.get("tot_amount"));
		mv.addObject("practice_amount", map.get("practice_amount"));
		mv.addObject("actkey", map.get("actkey"));
		mv.addObject("order_time", map.get("order_time"));
		mv.addObject("comp_time", map.get("comp_time"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));
		
		mv.addObject("mission_money", map.get("mission_money"));
		mv.addObject("input_bonus", map.get("input_bonus"));
		mv.addObject("processed_bonus", map.get("processed_bonus"));
		
		// mv.addObject("list", map.get("list"));

		return mv;
	}

	// 주문 결과 처리 초기 버전 (사용하지 않음)
	@RequestMapping(value = "/forex/user_order_final.do")
	public ModelAndView user_order_final(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/user_order_final");
		
		Map<String, Object> map = forexService.order_final(
				commandMap.getMap(), request);
		mv.addObject("list", map.get("list"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}

	// 주문 결과 프로세스 초기버전 (사용하지 않음)
	@RequestMapping(value = "/forex/cron_order_result.do")
	public void user_order_result(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		// String order_key = request.getParameter("order_key");
		Map<String, Object> map = forexService.cron_order_result(
				commandMap.getMap(), request);
	}
	
	@RequestMapping(value = "/forex/order_result02.do")
	public ModelAndView order_result02(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("/forex/order_result02");
		
		Map<String, Object> map = forexService.order_result02(
				commandMap.getMap(), request);
		//mv.addObject("list", map.get("list"));
		mv.addObject("user_id", map.get("user_id"));
		mv.addObject("event", map.get("event"));
		mv.addObject("start_rate", map.get("start_rate"));
		mv.addObject("end_rate", map.get("end_rate"));
		mv.addObject("invest_money", map.get("invest_money"));
		mv.addObject("callput", map.get("callput"));
		mv.addObject("ext_rate", map.get("ext_rate"));
		mv.addObject("game_type", map.get("game_type"));
		mv.addObject("invest_result", map.get("invest_result"));
		mv.addObject("result_fee", map.get("result_fee"));
		mv.addObject("result_user_tot_amount", map.get("result_user_tot_amount"));
		mv.addObject("result_user_last_amount", map.get("result_user_last_amount"));
		mv.addObject("result_user_possible_amount", map.get("result_user_possible_amount"));
		mv.addObject("result_user_practice_amount", map.get("result_user_practice_amount"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	

	// 크론탭을 이용한 주문결과 도출 프로세스
	@RequestMapping(value = "/forex/cron_order_result_all.do")
	public void user_order_result_all(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = forexService.cron_order_result_all(
				commandMap.getMap(), request);

	}
	
	// 크론탭을 이용한 주문결과 도출 프로세스
		@RequestMapping(value = "/forex/cron_order_result_all02.do")
		public void user_order_result_all02(CommandMap commandMap,
				HttpServletRequest request) throws Exception {

			Map<String, Object> map = forexService.cron_order_result_all02(
					commandMap.getMap(), request);

		}

	// @RequestMapping(value="/forex/result_receive.do" , method =
	// {RequestMethod.POST})
	// 클라이언트 요청시 주문결과가 도출된 데이터 전달
	@RequestMapping(value = "/forex/result_receive.do")
	public ModelAndView result_receive(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/result_receive");
		Map<String, Object> map = forexService.result_receive(
				commandMap.getMap(), request);
		mv.addObject("list", map.get("list"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	
	@RequestMapping(value = "/forex/decomplete_list.do")
	public ModelAndView decomplete_list(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/decomplete_list");
		Map<String, Object> map = forexService.decomplete_list(
				commandMap.getMap(), request);
		mv.addObject("decomplete_list", map.get("decomplete_list"));
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	
	@RequestMapping(value = "/forex/decomplete_deposit.do")
	public ModelAndView decomplete_deposit(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/decomplete_deposit");
		Map<String, Object> map = forexService.decomplete_deposit(
				commandMap.getMap(), request);
		mv.addObject("decomplete_deposit", map.get("decomplete_deposit"));
		mv.addObject("deposit_info", map.get("deposit_info"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	
	@RequestMapping(value = "/forex/extRate_receive.do")
	public ModelAndView extRate_receive(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/extRate_receive");
		Map<String, Object> map = forexService.extRate_receive(
				commandMap.getMap(), request);
		mv.addObject("list", map.get("list"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	
	@RequestMapping(value = "/forex/receive_bank.do")
	// 은행목록
	public ModelAndView receive_bank(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("/forex/receive_bank");
		Map<String, Object> map = forexService.receive_bank(
				commandMap.getMap(), request);
		mv.addObject("receive_bank", map.get("receive_bank"));

		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
		
	}

	// 크론탭을 이용하여 지정된 시간에 tb_fxdata Backup 프로세스
	@RequestMapping(value = "/forex/batch_fxdata_backup.do")
	public void batch_fxdata_backup(CommandMap commandMap,
			HttpServletRequest request) throws Exception {

		forexService.batch_fxdata_backup(commandMap.getMap(), request);

	}

	@RequestMapping(value = "/forex/deposit_info.do")
	public ModelAndView deposit_info(CommandMap commandMap,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/forex/deposit_info");
		Map<String, Object> map = forexService.deposit_info(
				commandMap.getMap(), request);
		mv.addObject("deposit_info", map.get("deposit_info"));
		
		mv.addObject("successYN", map.get("successYN"));
		mv.addObject("result_cd", map.get("result_cd"));
		mv.addObject("text", map.get("text"));

		return mv;
	}
	
	public long curtime(){
		long time = 0;
		String ip = serverIp();
		if(ip.equals("192.168.1.111")||ip.equals("192.168.1.125")||ip.equals("192.168.52.1")||ip.equals("127.0.1.1")||ip.equals("112.175.149.32")){
			time = System.currentTimeMillis();
			log.debug("time :"+ time);
		}else if(ip.equals("161.202.229.83")){
			time = System.currentTimeMillis()+(1000*60*60*16)-(1000*47);
		}else{
			time = System.currentTimeMillis();
		}
		 
//		return time+29500;	//서버반영시 20170529
//		return time-53000;	//서버반영시 20170522
//		return time-45000;	//서버반영시
		return time;
	}
	
	public String serverIp(){
		String ip = "";
		
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("-----------ip----------- " +ip);
		
		return ip;
	}

	public String home(Model model) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		model.addAttribute("clientIP", ip);
		return ip;
	}
	
	
}
