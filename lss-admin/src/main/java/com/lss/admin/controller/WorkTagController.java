package com.lss.admin.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.IWorkTagService;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;

/**
 * 工单 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("workTag")
public class WorkTagController extends BaseController {
	
	
	
	/**
	 * 待关闭工单总量
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ReturnVo tagList(@RequestBody WorkTag workTag) {
		return ServiceManager.workTagService.tagList(workTag);
	}
	
	
	
	
}
