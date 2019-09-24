/**
 * 
 */
package com.lss.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lss.admin.service.PhoneService;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.WorkOrderVo;

/**
 * 
 * 
 * 类说明：连连外呼测试。
 *  
 * 
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年5月7日
 */
public class PhoneServiceImplTest extends SpringTestCase {
	
	@Resource
	private PhoneService phoneService;
	
	@Test
	public void phoneBind() {
		WorkOrderVo order=new WorkOrderVo();
//		order.setOrderno("1902201005030001");
		order.setPhone("15801258968");
		
		LoginAdmin vo = new LoginAdmin();
		vo.setLoginame("dxy");//登录名
		vo.setAdminid(1);
		vo.setName("王小二");
		ReturnVo rt = phoneService.phoneBind(order, vo);
		System.out.println(JSON.toJSONString(rt));
	}
}
