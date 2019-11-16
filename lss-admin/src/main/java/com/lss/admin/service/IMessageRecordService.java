package com.lss.admin.service;

import com.lss.core.pojo.MessageRecord;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.MessageRecordParams;

public interface IMessageRecordService {

	void insert(MessageRecord messageRecord);

	ReturnVo messageList(MessageRecordParams messageRecordParams, LoginAdmin loginAdmin);

}
