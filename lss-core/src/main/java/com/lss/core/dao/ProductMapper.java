package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Product;
import com.lss.core.vo.admin.ProductVo;
import com.lss.core.vo.admin.params.ProductParams;

public interface ProductMapper extends AbstractMapper<Product, Integer> {

	/**
	 * 后台获取产品数量
	 * 
	 * @return
	 */
	int adminCount(ProductParams params);

	/**
	 * 后台获取产品列表
	 * 
	 * @param params
	 * @return
	 */
	List<ProductVo> adminList(ProductParams params);

	/**
	 * 根据分类id查询产品列表
	 * 
	 * @param classid
	 * @return
	 */
	List<Product> getListByClassid(Integer classid);
}