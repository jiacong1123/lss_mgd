package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
import com.lss.core.pojo.Baike;

public interface IBaikeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Baike record);

    int insertSelective(Baike record);

    Baike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baike record);

    int updateByPrimaryKeyWithBLOBs(Baike record);

    int updateByPrimaryKey(Baike record);
    
    List<BaikeDto> findBaikes(FindBaikePage findBaikePage);
    
    List<BaikeDto> findBaikePage(FindBaikePage findBaikePage);
    
    int findBaikePageCount(FindBaikePage findBaikePage);
}