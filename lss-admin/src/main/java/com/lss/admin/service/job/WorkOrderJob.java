package com.lss.admin.service.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lss.admin.service.WorkOrderService;

/**
 * 类说明：工单自动关闭JOb。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019.08.15
 */
@Component
@Lazy(false)
public class WorkOrderJob {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(LlyhRecordJob.class);

	@Resource
	WorkOrderService workOrderService;
	
	@Scheduled(cron = "0 20 0 * * ? ")  //每天的0时20分执行 正式
//	@Scheduled(fixedDelay=300000) //测试用，5分钟执行一次  
	public void autoCloseWorkOrder() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:系统开始关闭工单。", ctime);
		workOrderService.autoCloseWorkOrder(ctime+"");
		logger.info("批次{}:系统关闭工单完成。", ctime);
	}
	
}
