package com.lss.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lss.admin.service.IWorkTagService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;

@Service
public class WorkTagServiceImpl implements IWorkTagService {

	@Override
	public ReturnVo tagList(WorkTag workTag) {
		List<WorkTag> list = MapperManager.workTagMapper.queryTags(workTag);
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.success);
		result.setObj(list);
		return result;
	}

	

}
