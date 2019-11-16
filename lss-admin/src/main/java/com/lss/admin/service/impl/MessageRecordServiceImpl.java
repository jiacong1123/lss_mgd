package com.lss.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lss.admin.service.IMessageRecordService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.MessageRecord;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.MessageRecordVo;
import com.lss.core.vo.admin.params.MessageRecordParams;
@Service
public class MessageRecordServiceImpl implements IMessageRecordService {

	private static final Logger log = LoggerFactory.getLogger(MessageRecordServiceImpl.class);
	
	@Override
	public void insert(MessageRecord messageRecord) {
		log.debug("insert(MessageRecord messageRecord={})-start",messageRecord);
		try {
			MapperManager.messageRecordMapper.insert(messageRecord);
		} catch (Exception e) {
			log.error("新增短信记录错误!");
		}
		
	}

	@Override
	public ReturnVo messageList(MessageRecordParams messageRecordParams, LoginAdmin loginAdmin) {
		log.debug("messageList(MessageRecordParams messageRecordParams, LoginAdmin loginAdmin)-start",messageRecordParams,loginAdmin);
		ReturnVo returnVo = new ReturnVo();
		try {
			
			// 电销组长 ，看到自己和下属的
			if (loginAdmin.getRoles().contains(9)) {
				Admin findAdmin = MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					// 如果没有用所属人查询就查小组所有成员
					if (messageRecordParams.getAdminId() == null) {
						// 下属及同组员工
						List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
						if (adminids.size() == 0) {// 没下属，则给一个不存在的账号 避免拿了所有员工数据
							adminids.add(-1);
						}
						messageRecordParams.setAdminIds(adminids);
					}
				} else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			}

			// 同时拥有电销组长,电销员两个角色
			if (loginAdmin.getRoles().contains(9) && loginAdmin.getRoles().contains(3)) {
				Admin findAdmin = MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					// 如果没有用所属人查询就查小组所有成员
					if (messageRecordParams.getAdminId() == null) {
						// 下属及同组员工
						List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
						if (adminids.size() == 0) {// 没下属，则给一个不存在的账号 避免拿了所有员工数据
							adminids.add(-1);
						}
						messageRecordParams.setAdminIds(adminids);
					}
				} else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			}
			
			if (loginAdmin.getRoles().contains(3) && !loginAdmin.getRoles().contains(9)) {
				messageRecordParams.setAdminId(loginAdmin.getAdminid());
			}
			
			int count = MapperManager.messageRecordMapper.messageListCount(messageRecordParams);
			if(count>0) {
				List<MessageRecordVo> list =  MapperManager.messageRecordMapper.messageList(messageRecordParams);
				returnVo.setObj(list);
			}
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
			return returnVo;
		} catch (Exception e) {
			log.error("查询短信记录错误!");
			returnVo.setResult(ResponseCode.failure);
			returnVo.setMsg(ResponseCode.failureMsg);
			return returnVo;
		}
		
	}

}
