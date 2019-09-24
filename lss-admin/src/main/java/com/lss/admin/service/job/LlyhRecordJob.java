/**
 * 
 */
package com.lss.admin.service.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lss.admin.service.ICallRecordService;

/**
 * 类说明：连连隐号推送记录处理。
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
public class LlyhRecordJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(LlyhRecordJob.class);

	@Resource
	ICallRecordService callRecordService;
   
	//@Scheduled(fixedDelay=1000)  //第一种方式  
    //fixedDelay延时多少毫秒，多少毫秒执行一次  
	//@Scheduled(cron="0 * * * * *")     //第二种方式  
	@Scheduled(cron = "0 1 6-23 * * ?")  //每天的6-23时01分执行 正式
//	@Scheduled(fixedDelay=300000) //测试用，5分钟执行一次  
	public void processCallRecord() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:开始处理通话记录。", ctime);
		callRecordService.processCallRecord(ctime+"");
		logger.info("批次{}:开始处理通话记录完成。", ctime);
	}

	@Scheduled(cron = "0 30 6-23 * * ? ")  //每天的6-23时30分执行 正式
//	@Scheduled(fixedDelay=300000) //测试用，5分钟执行一次  
	public void processSmsRecord() {
		Long ctime = System.currentTimeMillis();
		logger.info("批次{}:开始处理短信记录。", ctime);
		callRecordService.processSmsRecord(ctime+"");
		logger.info("批次{}:开始处理短信记录完成。", ctime);
	}

}
