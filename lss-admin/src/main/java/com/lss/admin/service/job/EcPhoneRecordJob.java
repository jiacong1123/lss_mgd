package com.lss.admin.service.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.PhoneService;
import com.lss.core.base.MapperManager;
import com.lss.core.dao.ICallRecordDao;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.CallRecord;
import com.lss.core.util.DateUtils;
import com.lss.core.util.EcPhoneUtil;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.admin.EcPhoneRecodeResultVo;
import com.lss.core.vo.admin.EcPhoneRecodeVo;
import com.lss.core.vo.admin.UserVo;

/**
 * 每天的6-23时,每小时第一分钟定时去EC拉取通话记录
 * 
 * @author Administrator
 *
 */
@Component
@Lazy(false)
public class EcPhoneRecordJob {

	private static final Logger logger = LoggerFactory.getLogger(EcPhoneRecordJob.class);
	
	@Resource
	private PhoneService phoneService;
	@Resource
	private ICallRecordDao callRecordDao;

	// EC通话常量
	private static String EC_PHONE_RECORD_URL = "https://open.workec.com/record/telRecord";
	private static String GET_EC_TOKEN_KEY = "getECtoken";

//	@Scheduled(cron = "0 1 6-23 * * ?")  //每天的6-23时01分执行 正式
	@Scheduled(fixedDelay = 300000) // 测试用，5分钟执行一次
	public void addRecord() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:开始处理EC通话记录。", ctime);
		// 查询所有的userid
		List<String> userList = ServiceManager.adminService.selectUserIds();
		String startDate = DateUtils.format(new Date(), DateUtils.YYYYMMDD);
		for (String userIds : userList) {
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("userIds", userIds);
			jsonParam.put("startDate", startDate);
			jsonParam.put("endDate", startDate);
			String token = EcPhoneUtil.getToken(GET_EC_TOKEN_KEY);
			JSONObject post = EcPhoneUtil.httpPost(EC_PHONE_RECORD_URL, jsonParam, token);
			if (null != post && 200 == post.getIntValue("errCode")) {
				List<EcPhoneRecodeResultVo> list = post.getObject("data", EcPhoneRecodeVo.class).getResult();
				if (null != list && list.size() > 0) {
					for (EcPhoneRecodeResultVo result : list) {
						//从redis中查询通话唯一标识,如果不存在就插入通话记录,并把话单唯一标识存入缓存
						String value = RedisUtil.getString(result.getMd5());
						if(StringUtils.isEmpty(value)) {
							CallRecord records = new CallRecord();
							records.setRecordId(result.getMd5());// 通话唯一标识
							records.setType(result.getType() + "");// 通话类型
							records.setShowNo(result.getCalltono());// 被叫号码
							records.setLlResult("ANSWERED");
							records.setStartTime(result.getStarttime());// 通话开始时间
							records.setEndTime(null);
							records.setDuration(Integer.parseInt(result.getCalltime()));// 通话时长
							records.setRecordingUrl(result.getPath());// 原始录音地址
							
							// 通过userID查询admin
							Admin admin = MapperManager.adminMapper.selectAdminIdByUserId(result.getUserId());
							if(null!=admin) {
								records.setAdminId(admin.getAdminid());
								records.setAdminName(admin.getName());
							}
							records.setEmpNo(admin.getPhone());
							//通过手机号码查询工单系统客户
							UserVo userVo = MapperManager.userMapper.queryByPhone(result.getCalltono());
							if(null!=userVo) {
								records.setUserName(userVo.getName());
							}
							records.setCusNo(result.getCalltono());
							records.setCusInfo(result.getCustomerName());
							records.setProcessStatus(ProcessStatus.INIT.name());
							records.setCreateTime(new Date());
							//新增通话记录
							callRecordDao.insertSelective(records);
							
							
							RedisUtil.setString(result.getMd5(), EC_PHONE_RECORD_URL, RedisUtil.EXRP_DAY);
						}
						
					}
				}

			}
		}
		logger.info("批次{}:开始处理通EC话记录完成。", ctime);
	}
}
