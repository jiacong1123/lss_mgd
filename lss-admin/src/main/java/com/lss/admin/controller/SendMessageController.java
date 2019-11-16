package com.lss.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.MessageRecord;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.params.MessageParams;
import com.lss.core.vo.admin.params.MessageRecordParams;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsBatchSend;
import com.yunpian.sdk.model.SmsSingleSend;
import com.yunpian.sdk.model.Template;

/**
 * 短信 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("sendMessage")
public class SendMessageController extends BaseController{

	private static String APIKEY = "190e7d32a532d335c83fb4dd3db7eeab";
	//初始化clnt,使用单例方式
	private static YunpianClient clnt = new YunpianClient(APIKEY).init();
	
	/**
	 * 短信模板列表
	 * @return
	 */
	@RequestMapping("templateList")
	public ReturnVo templateList(MessageParams messageParams) {
		return ServiceManager.messageService.findMessageTemplate(messageParams);
	}
	
	/**
	 * 获取模板
	 * @param messageParams
	 * @return
	 */
	@RequestMapping("getTemplate")
	public ReturnVo getTemplate(@RequestBody MessageParams messageParams) {
		ReturnVo returnVo = new ReturnVo();
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.APIKEY, APIKEY);
		param.put(YunpianClient.TPL_ID,messageParams.getTemplateId());
		Result<List<Template>> r = clnt.tpl().get(param);
		if(null!=r && r.getCode() == 0) {
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
			returnVo.setObj(r);
		}else {
			returnVo.setResult(ResponseCode.failure);
			returnVo.setMsg(ResponseCode.failureMsg);
		}
		return returnVo;
	}
	
	
	/**
	 * 发送短信
	 * @param messageParams
	 * @return
	 */
	@RequestMapping("sendMessage")
	public ReturnVo sendMessage(@RequestBody MessageParams messageParams) {
		ReturnVo returnVo = new ReturnVo();
		Map<String, String> param = clnt.newParam(5);
		param.put(YunpianClient.APIKEY, APIKEY);
		param.put(YunpianClient.MOBILE, messageParams.getPhone());
		param.put(YunpianClient.TPL_ID, messageParams.getTemplateId());
		
		StringBuilder sb = new StringBuilder("#one#=");
		sb.append(messageParams.getOne());
		if(messageParams.getTwo() !=null && messageParams.getTwo()!= "") {
			sb.append("&#two#=");
			sb.append(messageParams.getTwo());
		}
		if(messageParams.getThree() !=null && messageParams.getThree() != "") {
			sb.append("&#three#=");
			sb.append(messageParams.getThree());
		}
		if(messageParams.getFour() !=null && messageParams.getFour() != "") {
			sb.append("&#four#=");
			sb.append(messageParams.getFour());
		}
		if(messageParams.getFive() !=null && messageParams.getFive() != "") {
			sb.append("&#five#=");
			sb.append(messageParams.getFive());
		}
		if(messageParams.getSix() !=null && messageParams.getSix() != "") {
			sb.append("&#six#=");
			sb.append(messageParams.getSix());
		}
		if(messageParams.getSeven() !=null && messageParams.getSeven() != "") {
			sb.append("&#seven#=");
			sb.append(messageParams.getSeven());
		}
		
		param.put(YunpianClient.TPL_VALUE, sb.toString());
		Result<SmsBatchSend> r = clnt.sms().tpl_batch_send(param);
		if(null != r && r.getCode() == 0) {
			List<SmsSingleSend> data = r.getData().getData();
			//记录发送失败的手机号码
			StringBuilder phone = new StringBuilder("发送失败的手机号码:");
			for (SmsSingleSend smsSingleSend : data) {
				if(smsSingleSend.getCode()!=0) {
					phone.append(smsSingleSend.getMobile()).append(":").append(smsSingleSend.getMsg()).append(",");
				}
				//新增一条短信记录
				UserVo user = ServiceManager.userService.findUserByPhone(smsSingleSend.getMobile());
				if(null != user) {
					MessageRecord messageRecord = new MessageRecord();
					messageRecord.setCode(UUID.randomUUID().toString());
					messageRecord.setAdminName(loginAdmin.getName());
					messageRecord.setAdminId(loginAdmin.getAdminid());
					messageRecord.setContent(messageParams.getContent());
					messageRecord.setPhone(user.getPhone());
					messageRecord.setSendDate(new Date());
					if(0==smsSingleSend.getCode()) {
						messageRecord.setStatus(1);
					}else {
						messageRecord.setStatus(2);
					}
					messageRecord.setUserName(user.getName());
					messageRecord.setRemark(smsSingleSend.getSid().toString());//短信ID
					ServiceManager.messageRecordService.insert(messageRecord);
				}
			}
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(phone.toString());
			return returnVo;
		}else {
			returnVo.setResult(ResponseCode.failure);
			returnVo.setMsg(ResponseCode.failureMsg);
			return returnVo;
		}
	}
	
	/**
	 * 发送短信
	 * @param messageParams
	 * @return
	 */
	@RequestMapping("messageList")
	public ReturnVo messageList(@RequestBody MessageRecordParams messageRecordParams) {
		return ServiceManager.messageRecordService.messageList(messageRecordParams,loginAdmin);
	}
	
}
