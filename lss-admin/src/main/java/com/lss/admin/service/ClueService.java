package com.lss.admin.service;

import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.ClueParams;
import com.lss.core.vo.hx.ClueListVo;

public interface ClueService {
	/**
	 * 创建线索
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	ReturnVo create(ClueParams params) throws Exception;

	/**
	 * 派单
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	ReturnVo dispatch(ClueParams params) throws Exception;

	/**
	 * 列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(ClueParams params);

	/**
	 * 转跟进
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	ReturnVo turnfollowup(WorkRecord params, LoginAdmin admin) throws Exception;

	/**
	 * 编辑线索
	 * 
	 * @return
	 * @throws Exception
	 */
	ReturnVo edit(ClueListVo vo) throws Exception;

	/**
	 * 确认/取消到店
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	ReturnVo operation(ClueParams params) throws Exception;

	/**
	 * 接诊（换新调）
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo visiting(WorkOrder params);
	
	/**
	 * 到店（换新调）
	 * @param orderno
	 * @return
	 */
	ReturnVo toshop(String orderno);
}
