package first.forex.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ForexService {

	List<Map<String, Object>> selectUserList(Map<String, Object> map,HttpServletRequest request)
			throws Exception;
	
	List<Map<String, Object>> selectOrderList(Map<String, Object> map,HttpServletRequest request)
			throws Exception;
	
	List<Map<String, Object>> selectOrderList_pop(Map<String, Object> map,HttpServletRequest request)
			throws Exception;
	
	List<Map<String, Object>> selectDepositList_pop(Map<String, Object> map,HttpServletRequest request)
			throws Exception;

	List<Map<String, Object>> selectUserAccount(Map<String, Object> map)
			throws Exception;

	Map<String, Object> selectUserDetail(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> selectUserDetail_pop(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> receivefx(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receivefx02(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> loginFlag(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> join_first(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> join_first_confirm(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> apply_deposit(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> apply_refund(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> apply_refund_second(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> join_second(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> change_pw(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> change_pw_confirm(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> change_name(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> join_second_confirm(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> order_money(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> order_money02(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> order_final(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> result_receive(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> decomplete_list(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> decomplete_deposit(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> extRate_receive(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> cron_order_result(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> order_result02(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	Map<String, Object> cron_order_result_all(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> cron_order_result_all02(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_bank(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_myorder(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_mydecomplete_order(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_deposit_his(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receivefx03(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_client_notice(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_refund_his(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> deposit_info(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> receive_mission_info(Map<String, Object> map,
			HttpServletRequest request) throws Exception;
	
	Map<String, Object> cron_update_refill_practice(Map<String, Object> map,
			HttpServletRequest request) throws Exception;

	void batch_fxdata_backup(Map<String, Object> map, HttpServletRequest request)
			throws Exception;
}
