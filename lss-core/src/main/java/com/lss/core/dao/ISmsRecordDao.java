package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.SmsRecordDto;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.pojo.SmsRecord;

public interface ISmsRecordDao {
	int deleteByPrimaryKey(Integer id);

	int insert(SmsRecord record);

	int insertSelective(SmsRecord record);

	SmsRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SmsRecord record);

	int updateByPrimaryKey(SmsRecord record);

	List<SmsRecordDto> findSmsRecords(FindSmsRecordPage findSmsRecordPage);

	List<SmsRecordDto> findSmsRecordPage(FindSmsRecordPage findSmsRecordPage);

	int findSmsRecordPageCount(FindSmsRecordPage findSmsRecordPage);
}