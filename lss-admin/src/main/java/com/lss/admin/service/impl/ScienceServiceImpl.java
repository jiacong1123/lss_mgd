package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.ScienceService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Science;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

@Service
public class ScienceServiceImpl implements ScienceService {

	@Override
	public ReturnVo save(Science params) {
		ReturnVo result = new ReturnVo();
		String msg = checkScience(params);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (params.getId() == null) {
			// 添加
			params.setCreatetime(new Date());
			res = MapperManager.scienceMapper.insertSelective(params);
		} else {
			// 编辑
			res = MapperManager.scienceMapper
					.updateByPrimaryKeySelective(params);
		}
		if (res > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	/**
	 * 校验口腔科普
	 * 
	 * @param params
	 * @return
	 */
	private String checkScience(Science params) {
		if (ObjectUtil.isEmpty(params.getTitle()))
			return "标题不能为空";
		if (ObjectUtil.isEmpty(params.getAnswer()))
			return "内容不能为空";
		if (ObjectUtil.isEmpty(params.getDoctorid()))
			return "请选择医生";
		if (params.getAnswertime() == null)
			return "请选择回答时间";
		return "";
	}

	@Override
	public ReturnVo list(ScienceParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.scienceMapper.adminCount(params));
		result.setObj(MapperManager.scienceMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo operate(Science params) {
		ReturnVo result = new ReturnVo();
		if (MapperManager.scienceMapper.updateByPrimaryKeySelective(params) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

}
