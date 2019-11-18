package com.lss.core.base;

import java.io.Serializable;

public interface AbstractMapper<T,ID extends Serializable> {

	 int deleteByPrimaryKey(ID key);

	 int insert(T record);

	 int insertSelective(T record);

	 T selectByPrimaryKey(ID key);

	 int updateByPrimaryKeySelective(T record);

	 int updateByPrimaryKey(T record);
}
