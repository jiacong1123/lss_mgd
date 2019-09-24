/**
 * 
 */
package com.lss.admin.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.ICallRecordService;
import com.lss.admin.service.ISmsRecordService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.WorkOrderVo;

/**
 * 类说明：拨打电话相关业务。
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年5月6日
 */
@RestController
@RequestMapping("phone")
public class PhoneController extends BaseController {
	
	@Resource
	ICallRecordService callRecordService;
	
	@Resource
	ISmsRecordService smsRecordService;
	
	/**
	 * 拨打电话前绑定小号。
	 * @return
	 */
	@RequestMapping("/callbind")
	public ReturnVo bind(@RequestBody WorkOrderVo order) {
		LoginAdmin currUser = loginAdmin;
		return ServiceManager.phoneService.phoneBind(order,currUser);
	}
	
	/**
	 * 电话记录。
	 * @param param
	 * @return
	 */
	@RequestMapping("/callRecord/list")
	public ReturnVo callRecordList(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		param.setProcessStatus(ProcessStatus.PROCESSED.name());
		vo = callRecordService.findCallRecordPage(param);
		return vo;
	}
	
	/**
	 * 短信记录。
	 * @param param
	 * @return
	 */
	@RequestMapping("/smsRecord/list")
	public ReturnVo smsRecordList(@RequestBody FindSmsRecordPage param) {
		ReturnVo vo = null;
		param.setProcessStatus(ProcessStatus.PROCESSED.name());
		vo = smsRecordService.findSmsRecordPage(param);
		return vo;
	}
	
	/**
	 * 未接电话记录。
	 * @param param
	 * @return
	 */
	@RequestMapping("/callRecord/unReadlist")
	public ReturnVo unReadlist(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if(StringUtils.isEmpty(param.getAdminId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("管理员ID不能为空");
			return vo;
		}
		if(StringUtils.isEmpty(param.getEmpId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("登陆帐号不能为空");
			return vo;
		}
		vo = callRecordService.findUnReadlist(param);
		return vo;
	}
	
	/**
	 * 查询未处理的未接来电数量
	 * @param param
	 * @return
	 */
	@RequestMapping("/unReadCount")
	public ReturnVo unReadCount(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if(StringUtils.isEmpty(param.getAdminId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("管理员ID不能为空");
			return vo;
		}
		if(StringUtils.isEmpty(param.getEmpId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("登陆帐号不能为空");
			return vo;
		}
		param.setType("InBound_Call");
		param.setProcessStatus("INIT");
		vo = callRecordService.findUnReadCount(param);
		return vo;
	}
	
	/**
	 * 更新未接来电状态为已处理
	 * @param param
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReturnVo updateStatus(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if(StringUtils.isEmpty(param.getRecordId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("通话记录ID不能为空");
			return vo;
		}
		param.setProcessStatus("PROCESSED");
		vo = callRecordService.updateCallRecordStatus(param);
		return vo;
	}
}

