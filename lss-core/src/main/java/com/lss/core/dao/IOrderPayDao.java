package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.OrderPayDto;
import com.lss.core.dto.FindOrderPayPage;
import com.lss.core.pojo.OrderPay;

public interface IOrderPayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
    
    
	List<OrderPayDto> findOrderPays(FindOrderPayPage findOrderPayPage);

	List<OrderPayDto> findOrderPayPage(FindOrderPayPage findOrderPayPage);

	int findOrderPayPageCount(FindOrderPayPage findOrderPayPage);
}