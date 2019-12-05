package com.lss.admin.service;

import com.lss.core.pojo.Product;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.ProductVo;
import com.lss.core.vo.admin.params.ProductParams;

public interface ProductService {
	/**
	 * 添加/编辑产品
	 * 
	 * @param product
	 * @return
	 */
	ReturnVo save(ProductVo product);

	/**
	 * 产品列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(ProductParams params);

	/**
	 * 产品详情
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo details(Product params);

	/**
	 * 启用/禁用/删除产品
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo operate(Product params);

	/**
	 * 产品菜单
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo menus(Product params);
}
