package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.CallEventDto;
import com.lss.core.dto.FindCallEventPage;
import com.lss.core.pojo.CallEvent;

public interface ICallEventDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CallEvent record);

    int insertSelective(CallEvent record);

    CallEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CallEvent record);

    int updateByPrimaryKey(CallEvent record);
    
    List<CallEventDto> findCallEvents(FindCallEventPage findCallEventPage);
    
    List<CallEventDto> findCallEventPage(FindCallEventPage findCallEventPage);
    
    int findCallEventPageCount(FindCallEventPage findCallEventPage);
}