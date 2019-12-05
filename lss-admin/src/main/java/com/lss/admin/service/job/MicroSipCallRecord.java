/**
 * 
 */
package com.lss.admin.service.job;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lss.core.base.MapperManager;
import com.lss.core.dao.ICallRecordDao;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.CallRecord;
import com.lss.core.util.MicroSipUtil;
import com.lss.core.util.QiniuUtil;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.admin.UserVo;

/**
 * 类说明：超脑云通话记录处理。
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月8日
 */
@Component
@Lazy(false)
public class MicroSipCallRecord {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MicroSipCallRecord.class);

	@Resource
	private ICallRecordDao callRecordDao;

	// @Scheduled(fixedDelay=1000) //第一种方式
	// fixedDelay延时多少毫秒，多少毫秒执行一次
	// @Scheduled(cron="0 * * * * *") //第二种方式
//	@Scheduled(cron = "0 1 6-23 * * ?")  //每天的6-23时01分执行 正式
	@Scheduled(fixedDelay = 300000) // 测试用，5分钟执行一次
	public void processCallRecord() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:开始处理超脑云通话记录。", ctime);
		JSONObject jsonObject = MicroSipUtil.getRecord();
		if (null != jsonObject) {
			JSONObject data = jsonObject.getJSONObject("data");
			if (null != data) {
				logger.debug("处理超脑云通话记录响应:"+data);
				int status = (int) data.get("status");
				if (0 == status) {
					JSONObject result = data.getJSONObject("result");
					JSONArray bills = result.getJSONArray("bills");
					for (int i = 0; i < bills.size(); i++) {
						JSONObject bill = bills.getJSONObject(i);
						int id = (int) bill.get("id");
						String destnumber = bill.getString("destnumber");
						// 从redis中查询通话唯一标识,如果不存在就插入通话记录,并把话单唯一标识存入缓存
						String value = RedisUtil.getString(id + "");
						if (StringUtils.isEmpty(value)) {
							CallRecord records = new CallRecord();
							records.setRecordId(id + "");// 通话唯一标识
							records.setType(bill.getString("direction"));
							if(destnumber.startsWith("0")) {
								records.setShowNo(destnumber.substring(1));// 被叫号码
								records.setCusNo(destnumber.substring(1));// 被叫号码
							}else {
								records.setShowNo(destnumber);// 被叫号码
								records.setCusNo(destnumber);// 被叫号码
							}
							records.setLlResult("ANSWERED");
							records.setStartTime(bill.getString("starttime"));// 通话开始时间
							records.setEndTime(bill.getString("endtime"));
							records.setDuration((int) bill.get("billsec"));// 通话时长

							// 获取录音地址
							JSONObject object = MicroSipUtil.getSoundRecord(bill.getString("recordfilename"));
							if (null != object) {
								JSONObject dataResult = object.getJSONObject("data");
								int statusResult = (int) dataResult.get("status");
								if (0 == statusResult) {
									JSONObject resultR = dataResult.getJSONObject("result");
									// 1.上传录音到七牛
									if (StringUtils.isNotBlank(resultR.getString("downurl"))) {
										try {
											String url = QiniuUtil.getInstant()
													.uploadFromUrl(resultR.getString("downurl"));
											records.setLssRecordUrl(url);
											records.setRecordingUrl(url);
										} catch (Exception e) {
											logger.error("上传七牛失败！", e);
										}
									}
								}
							}

							// 通过userID查询admin
							Admin admin = MapperManager.adminMapper
									.selectAdminIdByMicroUserId(bill.getString("extnumber"));
							if (null != admin) {
								records.setAdminId(admin.getAdminid());
								records.setAdminName(admin.getName());
								records.setEmpNo(admin.getPhone());
							}
							// 通过手机号码查询工单系统客户
							UserVo userVo = MapperManager.userMapper.queryByPhone(destnumber.substring(1));
							if (null != userVo) {
								records.setUserName(userVo.getName());
							}
							records.setProcessStatus(ProcessStatus.PROCESSED.name());
							records.setCreateTime(new Date());
							// 新增通话记录
							callRecordDao.insertSelective(records);

							RedisUtil.setString(id + "", id + "", RedisUtil.EXRP_DAY);
						}
					}
				}
			}
			logger.info("批次{}:开始处理超脑云通话记录完成。", ctime);
		}

	}

}
