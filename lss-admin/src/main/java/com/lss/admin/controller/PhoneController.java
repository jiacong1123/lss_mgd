/**
 * 
 */
package com.lss.admin.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.ICallRecordService;
import com.lss.admin.service.ISmsRecordService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.util.MD5;
import com.lss.core.util.MicroSipUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.EcPhoneVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.WorkOrderVo;

/**
 * 类说明：拨打电话相关业务。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月6日
 */
@RestController
@RequestMapping("phone")
public class PhoneController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(PhoneController.class);

	@Resource
	ICallRecordService callRecordService;

	@Resource
	ISmsRecordService smsRecordService;

	// EC电话常量
	private static String CALL_URL = "https://open.workec.com/v2/record/call";
	private static String AppID = "547800367602073600";
	private static String CorpID = "4943442";
	private static String AppSecret = "5LMq4CCtuSfo6J9M3Uu";

	/**
	 * 拨打电话前绑定小号。
	 * 
	 * @return
	 */
	@RequestMapping("/callbind")
	public ReturnVo bind(@RequestBody WorkOrderVo order) {
		LoginAdmin currUser = loginAdmin;
		return ServiceManager.phoneService.phoneBind(order, currUser);
	}

	/**
	 * 电话记录。
	 * 
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
	 * 
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
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/callRecord/unReadlist")
	public ReturnVo unReadlist(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if (StringUtils.isEmpty(param.getAdminId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("管理员ID不能为空");
			return vo;
		}
		if (StringUtils.isEmpty(param.getEmpId())) {
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
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/unReadCount")
	public ReturnVo unReadCount(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if (StringUtils.isEmpty(param.getAdminId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("管理员ID不能为空");
			return vo;
		}
		if (StringUtils.isEmpty(param.getEmpId())) {
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
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReturnVo updateStatus(@RequestBody FindCallRecordPage param) {
		ReturnVo vo = null;
		if (StringUtils.isEmpty(param.getRecordId())) {
			vo = new ReturnVo();
			vo.setResult(2);
			vo.setMsg("通话记录ID不能为空");
			return vo;
		}
		param.setProcessStatus("PROCESSED");
		vo = callRecordService.updateCallRecordStatus(param);
		return vo;
	}

	/**
	 * 超脑云拨打电话
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/microSipCall")
	public ReturnVo microSipCall(@RequestBody EcPhoneVo phoneVo) {
		ReturnVo returnVo = new ReturnVo();
		returnVo.setMsg(ResponseCode.failureMsg);
		returnVo.setResult(ResponseCode.failure);

		JSONObject jsonObject = MicroSipUtil.call(phoneVo);
		if (null != jsonObject) {
			JSONObject data = jsonObject.getJSONObject("data");
			if (null != data) {
				log.debug("超脑云拨打电话响应:" + data);
				int status = (int) data.get("status");
				if (0 == status) {
					returnVo.setMsg(ResponseCode.successMsg);
					returnVo.setResult(ResponseCode.success);
				}
			}
		}
		// 2019-11-25 新需求:通话时候即时新增工单流程,并更新最近联系信息!
		UserVo userVo = MapperManager.userMapper.queryByPhone(phoneVo.getCallPhone());
		if (null != userVo) {
			String orderno = MapperManager.workOrderMapper.findOrderNoByUserId(userVo.getUserid());
			// 新增工单流程
			WorkRecord record = new WorkRecord();
			record.setOrderno(orderno);
			record.setAdminid(loginAdmin.getAdminid());
			record.setContent("与: " + userVo.getName() + "通话!");
			record.setCreatetime(new Date());
			MapperManager.workRecordMapper.insertSelective(record);
			// 更新工单最近联系
			WorkOrder workOrder = new WorkOrder();
			workOrder.setOrderno(orderno);
			workOrder.setFollowuptime(new Date());
			workOrder.setFollowupremarks("与: " + userVo.getName() + "通话!");
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(workOrder);
		}
		return returnVo;
	}

	/**
	 * 通过EC拨打电话
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/ecCall")
	public ReturnVo ecCall(@RequestBody EcPhoneVo phoneVo) {
		ReturnVo returnVo = new ReturnVo();
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("userid", phoneVo.getUserId());
		jsonParam.put("phone", phoneVo.getCallPhone());
		JSONObject httpPost = httpPost(CALL_URL, jsonParam);
		int code = (int) httpPost.get("code");
		if (null != httpPost && 200 == code) {
			returnVo.setObj(httpPost);
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
		} else {
			returnVo.setResult(code);
			returnVo.setMsg(httpPost.get("msg").toString());
		}

		// 2019-11-25 新需求:通话时候即时新增工单流程,并更新最近联系信息!
		UserVo userVo = MapperManager.userMapper.queryByPhone(phoneVo.getCallPhone());
		if (null != userVo) {
			String orderno = MapperManager.workOrderMapper.findOrderNoByUserId(userVo.getUserid());
			// 新增工单流程
			WorkRecord record = new WorkRecord();
			record.setOrderno(orderno);
			record.setAdminid(loginAdmin.getAdminid());
			record.setContent("与: " + userVo.getName() + "通话!");
			record.setCreatetime(new Date());
			MapperManager.workRecordMapper.insertSelective(record);
			// 更新工单最近联系
			WorkOrder workOrder = new WorkOrder();
			workOrder.setOrderno(orderno);
			workOrder.setFollowuptime(new Date());
			workOrder.setFollowupremarks("与: " + userVo.getName() + "通话!");
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(workOrder);
		}
		return returnVo;
	}

	/**
	 * post请求
	 *
	 * @param url            url地址
	 * @param jsonParam      参数
	 * @param noNeedResponse 不需要返回结果
	 * @param token          header里面Authorization的值
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		JSONObject jsonResult = null;
		CloseableHttpClient client = getHttpClient();
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}

			method.setHeader("X-Ec-Cid", CorpID);
			String timeStamp = System.currentTimeMillis() + "";
			method.setHeader("X-Ec-TimeStamp", timeStamp);
			String sign = MD5.encrypt("appId=" + AppID + "&appSecret=" + AppSecret + "&timeStamp=" + timeStamp);
			method.setHeader("X-Ec-Sign", sign);

			CloseableHttpResponse result = client.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					log.error("post请求提交失败:" + url);
					log.error("");
				} finally {
					result.close();
				}
			}
		} catch (IOException e) {
			log.error("post请求提交失败:" + url, e);
		} finally {
			try {
				closeHttpClient(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonResult;
	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
}
