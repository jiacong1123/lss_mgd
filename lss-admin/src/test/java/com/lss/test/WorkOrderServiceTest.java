package com.lss.test;
import javax.annotation.Resource;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lss.admin.service.WorkOrderService;

/**
 * 
 */

/**
 * 
 * 
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月6日
 */
@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkOrderServiceTest {

	@Resource
	private WorkOrderService workOrderService;

	@Test
	public void insertWorkRecord() {
		Integer workRecordId = workOrderService.insertWorkRecord("999", 1, "通话", null);
		System.out.println(workRecordId);
	}
	
	@Test
	public void inttest() {
		Integer code=789547885;
		
		System.out.println(0==code);
		System.out.println(code.toString());
		
		String aString="156,124,";
		String[] s1=aString.split(",");
		System.out.println(s1[s1.length-1]);
	}
}
