package com.lss.admin.service.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lss.admin.service.IStCallService;

/**
 * 类说明 員工电联统计。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019.08.15
 */
@Component
@Lazy(false)
public class StCallJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(LlyhRecordJob.class);

	@Resource
	IStCallService stCallService;
	
	@Scheduled(cron = "0 50 0 * * ? ")  //每天的0时50分执行 正式
//	@Scheduled(fixedDelay=300000) //测试用，5分钟执行一次  
	public void batchAddDailyStCall() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:系统开始统计电联信息。", ctime);
		stCallService.batchAddDailyStCall(ctime+"");
		logger.info("批次{}:系统统计电联信息完成。", ctime);
	}
}
