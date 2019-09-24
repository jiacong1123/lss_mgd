package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.ActivityService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Activity;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Override
	public ReturnVo save(Activity params) {
		ReturnVo result = new ReturnVo();
		String msg = checkActivity(params);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (params.getActid() == null) {
			// 添加
			params.setCreatetime(new Date());
			res = MapperManager.activityMapper.insertSelective(params);
		} else {
			// 修改
			res = MapperManager.activityMapper
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
	 * 校验活动参数
	 * 
	 * @param params
	 * @return
	 */
	private String checkActivity(Activity params) {
		if (ObjectUtil.isEmpty(params.getTitle()))
			return "活动标题不能为空";
		if (ObjectUtil.isEmpty(params.getImage()))
			return "请上传活动图片";
		if (ObjectUtil.isEmpty(params.getUrl())) {
			// url 为 null 验证 内容
			if (ObjectUtil.isEmpty(params.getContent()))
				return "活动内容不能为空";
		}
		if (params.getPopup() != null && params.getPopup() == 1) {
			if (ObjectUtil.isEmpty(params.getTcimage()))
				return "请上传弹窗图片";
		}
		if (params.getSort() == null)
			return "请填写排序";
		if (params.getStart() == null)
			return "请选择活动开始时间";
		if (params.getEnd() == null)
			return "请选择活动结束时间";
		return "";
	}

	@Override
	public ReturnVo list(ScienceParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.activityMapper.adminCount(params));
		result.setObj(MapperManager.activityMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo operate(Activity params) {
		ReturnVo result = new ReturnVo();
		if (MapperManager.activityMapper.updateByPrimaryKeySelective(params) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo details(Activity params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.activityMapper.selectByPrimaryKey(params
				.getActid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
