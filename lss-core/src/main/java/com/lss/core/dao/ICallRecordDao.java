package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.CallRecordDto;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.pojo.CallRecord;

public interface ICallRecordDao {
	int deleteByPrimaryKey(Integer id);

	int insert(CallRecord record);

	int insertSelective(CallRecord record);

	CallRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CallRecord record);

	int updateByPrimaryKey(CallRecord record);

	List<CallRecordDto> findCallRecords(FindCallRecordPage findCallRecordPage);

	List<CallRecordDto> findCallRecordPage(FindCallRecordPage findCallRecordPage);

	int findCallRecordPageCount(FindCallRecordPage findCallRecordPage);

	/**
	 * @param param
	 * @return
	 */
	List<CallRecordDto> findUnReadlist(FindCallRecordPage param);

	/**
	 * @param param
	 * @return
	 */
	int findUnreadCount(FindCallRecordPage param);

	/**
	 * @param param
	 * @return
	 */
	int findUnReadCount(FindCallRecordPage param);

	/**
	 * @param param
	 */
	void updateCallRecordStatus(FindCallRecordPage param);
}