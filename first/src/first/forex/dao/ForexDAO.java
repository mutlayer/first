package first.forex.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("forexDAO")
public class ForexDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserList(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.selectUserList",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectOrderList(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.selectOrderList",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectDepositList(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.selectDepositList",
				map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserAccount(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList(
				"forex.selectUserAccount", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserDetail(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectUserDetail", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserDetail_pop(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectUserDetail_pop", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loginFlag(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginFlag", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> loginFlag02(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginFlag02", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectRate(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectRate", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectLimitCom(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectLimitCom", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loginData(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginData", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loginInfo(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginInfo", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loginLastInfo(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginLastInfo", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> loginLastInfo_n(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.loginLastInfo_n", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> my_order(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.my_order", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cron_order_result(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList(
				"forex.cron_order_result", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cron_order_result02(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList(
				"forex.cron_order_result02", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectCommonRate(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectCommonRate", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOrderHis(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectOrderHis", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectCallPut_pct(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.selectCallPut_pct", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> return_result(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.return_result", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> receive_client_notice(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.receive_client_notice",
				map);
	}

	public void insert_loginHis(Map<String, Object> map) throws Exception {
		insert("forex.insert_loginHis", map);
	}

	public void update_fail_cnt(Map<String, Object> map) throws Exception {
		update("forex.update_fail_cnt", map);
	}
	
	public void update_return_bonus(Map<String, Object> map) throws Exception {
		update("update_return_bonus", map);
	}
	
	public void update_amount_refund(Map<String, Object> map) throws Exception {
		update("forex.update_amount_refund", map);
	}
	
	public void update_amount_refund_admin(Map<String, Object> map) throws Exception {
		update("forex.update_amount_refund_admin", map);
	}
	
	public void update_bonus_refund_c(Map<String, Object> map) throws Exception {
		update("forex.update_bonus_refund_c", map);
	}

	public void update_loginSuccess(Map<String, Object> map) throws Exception {
		update("forex.update_loginSuccess", map);
	}

	public void user_order(Map<String, Object> map) throws Exception {
		insert("forex.user_order", map);
	}

	public void update_user_order_final_win(Map<String, Object> map)
			throws Exception {
		update("forex.update_user_order_final_win", map);
	}
	
	public void cron_result_master(Map<String, Object> map)
			throws Exception {
		update("forex.cron_result_master", map);
	}

	public void update_user_amount_win(Map<String, Object> map)
			throws Exception {
		update("forex.update_user_amount_win", map);
	}

	public void update_user_order_final_lose(Map<String, Object> map)
			throws Exception {
		update("forex.update_user_order_final_lose", map);
	}

	public void update_last_amount(Map<String, Object> map) throws Exception {
		update("forex.update_last_amount", map);
	}
	
	public void update_last_practice_amount(Map<String, Object> map) throws Exception {
		update("forex.update_last_practice_amount", map);
	}

	public void insert_user_order(Map<String, Object> map) throws Exception {
		update("forex.insert_user_order", map);
	}
	
	public void insert_user_order02(Map<String, Object> map) throws Exception {
		update("forex.insert_user_order02", map);
	}
	
	public void insert_user_order_practice(Map<String, Object> map)
			throws Exception {
		update("forex.insert_user_order_practice", map);
	}

	public void insert_user_order_practice02(Map<String, Object> map)
			throws Exception {
		update("forex.insert_user_order_practice02", map);
	}

	public void cron_order_result_proc(Map<String, Object> map)
			throws Exception {
		update("forex.cron_order_result_proc", map);
	}

	public void cron_order_result_proc_practice(Map<String, Object> map)
			throws Exception {
		update("forex.cron_order_result_proc_practice", map);
	}

	public void cron_user_amount(Map<String, Object> map) throws Exception {
		update("forex.cron_user_amount", map);
	}

	public void cron_user_amount_practice(Map<String, Object> map)
			throws Exception {
		update("forex.cron_user_amount_practice", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> result_receive(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.result_receive",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> decomplete_bet(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.decomplete_bet",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> decomplete_deposit(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.decomplete_deposit",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> extRate_receive(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.extRate_receive",
				map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> result_receive_chk(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.result_receive_chk", map);
	}

	public void insert_cron_fxdata(Map<String, Object> map) throws Exception {
		insert("forex.insert_cron_fxdata", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> cron_fxdata_id(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.cron_fxdata_id", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> cron_fxdata_insertdate(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.cron_fxdata_insertdate",
				map);
	}

	public void batch_fxdata_backup(Map<String, Object> map) throws Exception {
		insert("forex.batch_fxdata_backup", map);
	}

	public void batch_delete_fxdata(Map<String, Object> map) throws Exception {
		delete("forex.batch_delete_fxdata", map);
	}
	
	public void update_commission(Map<String, Object> map)
			throws Exception {
		update("forex.update_commission", map);
	}
	
	public void insert_commission_his(Map<String, Object> map) throws Exception {
		insert("forex.insert_commission_his", map);
	}
	
	public void insert_abnormal_his(Map<String, Object> map) throws Exception {
		insert("forex.insert_abnormal_his", map);
	}
	
	public void update_commission_tocom(Map<String, Object> map)
			throws Exception {
		update("forex.update_commission_tocom", map);
	}
	
	public void insert_commission_his_tocom(Map<String, Object> map) throws Exception {
		insert("forex.insert_commission_his_tocom", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_level_id(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.select_level_id", map);
	}
	
	@SuppressWarnings("unchecked")
	public String encrypt_pw(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.encrypt_pw", map);
	}
	
	public void join_first(Map<String, Object> map) throws Exception {
		insert("forex.join_first", map);
	}
	
	public void join_first_amount(Map<String, Object> map) throws Exception {
		insert("forex.join_first_amount", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_recommend(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.select_recommend", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_recommend02(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.select_recommend02", map);
	}
	
	@SuppressWarnings("unchecked")
	public String duplicate_user_id(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.duplicate_user_id", map);
	}
	
	@SuppressWarnings("unchecked")
	public String duplicate_phone_number(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.duplicate_phone_number", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_minus_bonus(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.select_minus_bonus", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_user_phone_number(Map<String, Object> map)
			throws Exception {
		return (String) selectOne("forex.select_user_phone_number", map);
	}
	
	@SuppressWarnings("unchecked")
	public String loginyn(Map<String, Object> map)
			throws Exception {
		return  (String)selectOne("forex.loginyn", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receive_bank(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receive_bank",
				map);
	}
	
	public void insert_phone_confirm(Map<String, Object> map) throws Exception {
		insert("forex.insert_phone_confirm", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_confirm_number(Map<String, Object> map)
			throws Exception {
		return  (String)selectOne("forex.select_confirm_number", map);
	}
	
	public void update_confirm(Map<String, Object> map) throws Exception {
		update("forex.update_confirm", map);
	}
	
	public void update_user_pw(Map<String, Object> map) throws Exception {
		update("forex.update_user_pw", map);
	}
	
	public void update_user_name(Map<String, Object> map) throws Exception {
		update("forex.update_user_name", map);
	}
	
	public void update_confirm_tb_user(Map<String, Object> map) throws Exception {
		update("forex.update_confirm_tb_user", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_account_number(Map<String, Object> map)
			throws Exception {
		return  (String)selectOne("forex.select_account_number", map);
	}
	
	public void insert_deposit(Map<String, Object> map) throws Exception {
		insert("forex.insert_deposit", map);
	}
	
	public void insert_refund(Map<String, Object> map) throws Exception {
		insert("forex.insert_refund", map);
	}
	
	@SuppressWarnings("unchecked")
	public String select_possible_money(Map<String, Object> map)
			throws Exception {
		return  (String)selectOne("forex.select_possible_money", map);
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal select_deposit(Map<String, Object> map)
			throws Exception {
		return  (BigDecimal)selectOne("forex.select_deposit", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> select_user_bonus(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.select_user_bonus",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> select_user_bonus_sum(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.select_user_bonus_sum", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receive_deposit_his(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receive_deposit_his",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receivefx03(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receivefx03",
				map);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receive_myorder(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receive_myorder",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receive_mydecomplete_order(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receive_mydecomplete_order",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receive_refund_his(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.receive_refund_his",
				map);
	}
	
	public void update_bonus(Map<String, Object> map) throws Exception {
		update("forex.update_bonus", map);
	}
	
	public void cron_update_refill_practice(Map<String, Object> map) throws Exception {
		update("forex.cron_update_refill_practice", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> deposit_info(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.deposit_info",
				map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> amount_flag(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.amount_flag", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> select_myorder(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.select_myorder", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> join_first_sp_recom(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("forex.join_first_sp_recom", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> select_openclose(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("forex.select_openclose",
				map);
	}
	
	public void cron_order_result02_update1(Map<String, Object> map) throws Exception {
		update("forex.cron_order_result02_update1", map);
	}
	public void cron_order_result02_update2(Map<String, Object> map) throws Exception {
		update("forex.cron_order_result02_update2", map);
	}
}

