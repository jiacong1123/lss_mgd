package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.FindStCallPage;
import com.lss.core.dto.StCallDto;
import com.lss.core.pojo.StCall;

public interface IStCallDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StCall record);

    int insertSelective(StCall record);

    StCall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StCall record);

    int updateByPrimaryKey(StCall record);
    
    List<StCallDto> findStCalls(FindStCallPage findStCallPage);

	List<StCallDto> findStCallPage(FindStCallPage findStCallPage);

	int findStCallPageCount(FindStCallPage findStCallPage);
	
	List<StCallDto> findTodayStCallPage(FindStCallPage findStCallPage);

	int findTodayStCallCount(FindStCallPage findStCallPage);
	
	int batchAddDailyStCall(String stDate);
}