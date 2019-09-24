package com.lss.admin.service.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lss.admin.service.IStOrderService;

/**
 * 类说明 员工工单月成交统计。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019.08.15
 */
@Component
@Lazy(false)
public class StOrderJob {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(LlyhRecordJob.class);

	@Resource
	IStOrderService stOrderService;
	
	@Scheduled(cron = "0 10 0 1 * ? ")  //每月1号 0点10分 运行 2019/9/1 0:10:00 & 2019/10/1 0:10:00
//	@Scheduled(fixedDelay=300000) //测试用，5分钟执行一次  
	public void batchAddDailyStCall() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:系统开始统计员工工单月成交信息。", ctime);
		stOrderService.batchAddMonthStOrder(ctime+"");
		logger.info("批次{}:系统统计 员工工单月成交信息完成。", ctime);
	}
}
