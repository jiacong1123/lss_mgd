package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lss.admin.service.ProductService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Pictures;
import com.lss.core.pojo.Product;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.ProductVo;
import com.lss.core.vo.admin.params.ProductParams;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo save(ProductVo product) {
		ReturnVo result = new ReturnVo();
		String msg = checkProduct(product);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (product.getPid() == null) {
			// 添加
			product.setCreatetime(new Date());
			res = MapperManager.productMapper.insertSelective(product);
		} else {
			// 修改
			res = MapperManager.productMapper
					.updateByPrimaryKeySelective(product);
		}
		if (res > 0) {
			MapperManager.picturesMapper.deleteByPid(product.getPid());
			for (Pictures pic : product.getList()) {
				pic.setPid(product.getPid());
				MapperManager.picturesMapper.insertSelective(pic);
			}
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	/**
	 * 校验产品
	 * 
	 * @param product
	 * @return
	 */
	private String checkProduct(ProductVo product) {
		if (product.getClassid() == null)
			return "请选择产品分类";
		if (ObjectUtil.isEmpty(product.getTitle()))
			return "产品标题不能为空";
		if (ObjectUtil.isEmpty(product.getDes()))
			return "产品描述不能为空";
		if (ObjectUtil.isEmpty(product.getImage()))
			return "请上传产品图片";
		if (product.getCostprice() == null)
			return "请填写市场价";
		if (product.getPrice() == null)
			return "请填写现价";
		if (ObjectUtil.isEmpty(product.getDetails()))
			return "请编辑产品详情";
		return "";
	}

	@Override
	public ReturnVo list(ProductParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.productMapper.adminCount(params));
		result.setObj(MapperManager.productMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo details(Product params) {
		ReturnVo result = new ReturnVo();
		if (params.getPid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		Product pro = MapperManager.productMapper.selectByPrimaryKey(params
				.getPid());
		if (pro != null) {
			ProductVo vo = new ProductVo();
			vo.setPid(pro.getPid());
			vo.setClassid(pro.getClassid());
			vo.setTitle(pro.getTitle());
			vo.setDes(pro.getDes());
			vo.setImage(pro.getImage());
			vo.setCostprice(pro.getCostprice());
			vo.setPrice(pro.getPrice());
			vo.setDetails(pro.getDetails());
			vo.setStatus(pro.getStatus());
			vo.setCreatetime(pro.getCreatetime());
			// 查询产品图片
			vo.setList(MapperManager.picturesMapper.getList(pro.getPid()));
			result.setObj(vo);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo operate(Product params) {
		ReturnVo result = new ReturnVo();
		if (MapperManager.productMapper.updateByPrimaryKeySelective(params) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo menus(Product params) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getClassid())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		result.setObj(MapperManager.productMapper.getListByClassid(params
				.getClassid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
