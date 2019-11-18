package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.vo.admin.WorkRecordVo;
import com.lss.core.vo.admin.params.WorkOrderParams;

public interface WorkRecordMapper extends AbstractMapper<WorkRecord, Integer> {

	/**
	 * 工单记录
	 * 
	 * @param orderno
	 * @return
	 */
	List<WorkRecordVo> getRecordList(String orderno);
	
	/** 工单记录总数 */
	int getRecordListCount(WorkRecordVo recordVo);
	
	/**
	 * 系统自动关闭7天未跟进工单。
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019年8月12日 v1.4
	 */
	int autoCloseWorkOrderRecord(WorkOrderParams params);
}