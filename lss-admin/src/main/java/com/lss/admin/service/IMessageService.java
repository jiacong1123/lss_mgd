package com.lss.admin.service;

import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.MessageParams;

public interface IMessageService {

	ReturnVo findMessageTemplate(MessageParams messageParams);

}
