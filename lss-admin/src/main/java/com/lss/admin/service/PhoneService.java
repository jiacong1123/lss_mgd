/**
 * 
 */
package com.lss.admin.service;

import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.WorkOrderVo;
import com.lss.core.vo.phone.CallEventListVo;
import com.lss.core.vo.phone.CallRecordListVo;
import com.lss.core.vo.phone.LlyhResultVo;
import com.lss.core.vo.phone.LlyhSmsRecordListVo;

/**
 * 
 * 
 * 类说明：连连推送的内容处理。 <br/>
 * 电话相关记录。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月6日
 */
public interface PhoneService {

	/**
	 * 电话记录
	 * 
	 * @param recordListVo
	 * @return
	 */
	public LlyhResultVo saveCallRecord(CallRecordListVo recordListVo);

	/**
	 * 短信记录
	 * 
	 * @param smsRecordListVo
	 * @return
	 */
	public LlyhResultVo saveSmsRecord(LlyhSmsRecordListVo smsRecordListVo);

	/**
	 * 电话事件记录
	 * 
	 * @param smsRecordListVo
	 * @return
	 */
	public LlyhResultVo saveEventRecord(CallEventListVo callEventListVo);

	/**
	 * 外呼号码绑定接口
	 * 
	 * @param bindVo
	 */
	public ReturnVo phoneBind(WorkOrderVo order,LoginAdmin loginAdmin);

}
