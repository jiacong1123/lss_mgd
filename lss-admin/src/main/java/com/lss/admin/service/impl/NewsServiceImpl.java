package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.NewsService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.News;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.BannerParams;

@Service
public class NewsServiceImpl implements NewsService {

	@Override
	public ReturnVo save(News params) {
		ReturnVo result = new ReturnVo();
		String msg = checkNews(params);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (params.getId() == null) {
			// 添加
			if (params.getStatus() == null)
				params.setStatus(0);
			params.setCreatetime(new Date());
			res = MapperManager.newsMapper.insertSelective(params);
		} else {
			// 编辑
			res = MapperManager.newsMapper.updateByPrimaryKeySelective(params);
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
	 * 校验Banner
	 * 
	 * @param params
	 * @return
	 */
	private String checkNews(News params) {
		if (ObjectUtil.isEmpty(params.getTitle()))
			return "请填写标题";
		if (ObjectUtil.isEmpty(params.getSubtitle()))
			return "请填写副标题";
		if (ObjectUtil.isEmpty(params.getImage()))
			return "请上传主图";
		if (ObjectUtil.isEmpty(params.getContent()))
			return "请编辑内容";
		if (params.getType() == null)
			return "请选择类型";
		return "";
	}

	@Override
	public ReturnVo list(BannerParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.newsMapper.adminCount(params));
		result.setObj(MapperManager.newsMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo operate(News params) {
		ReturnVo result = new ReturnVo();
		if (MapperManager.newsMapper.updateByPrimaryKeySelective(params) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

}
