package com.lss.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lss.admin.service.IMessageService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.MessageVo;
import com.lss.core.vo.admin.params.MessageParams;
@Service
public class MessageServiceImpl implements IMessageService{

	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Override
	public ReturnVo findMessageTemplate(MessageParams messageParams) {
		log.debug("findMessageTemplate(MessageParams messageParams={})-start",messageParams);
		ReturnVo returnVo = new ReturnVo();
		try {
			List<MessageVo> list = MapperManager.messageMapper.findMessageTemplate(messageParams);
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
			returnVo.setObj(list);
			return returnVo;
		} catch (Exception e) {
			log.error("查询短信模板列表错误");
			returnVo.setResult(ResponseCode.failure);
			returnVo.setMsg(ResponseCode.failureMsg);
			return returnVo;
		}
		
	}

}
