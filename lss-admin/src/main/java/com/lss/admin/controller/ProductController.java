package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Product;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.ProductVo;
import com.lss.core.vo.admin.params.ProductParams;

/**
 * 产品 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

	/**
	 * 添加/编辑产品
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody ProductVo product) {
		return ServiceManager.productService.save(product);
	}

	/**
	 * 产品列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody ProductParams params) {
		return ServiceManager.productService.list(params);
	}

	/**
	 * 产品详情
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody Product params) {
		return ServiceManager.productService.details(params);
	}

	/**
	 * 启用/禁用/删除产品
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Product params) {
		return ServiceManager.productService.operate(params);
	}

	/**
	 * 产品菜单
	 * 
	 * @return
	 */
	@RequestMapping("menus")
	public ReturnVo menus(@RequestBody Product params) {
		return ServiceManager.productService.menus(params);
	}
}
