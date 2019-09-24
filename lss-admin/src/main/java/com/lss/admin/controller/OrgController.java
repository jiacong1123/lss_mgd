/**
 * 
 */
package com.lss.admin.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.service.IOrgService;
import com.lss.core.dto.FindOrgPage;
import com.lss.core.dto.OrgDto;
import com.lss.core.util.AssertUtils;
import com.lss.core.vo.ReturnVo;

/**
 * 
 * 类说明：机构
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月21日
 */
@RestController
@RequestMapping("org")
public class OrgController extends BaseController {

	@Resource
	private IOrgService orgService;

	/**
	 * 机构新增
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody OrgDto org) {
		AssertUtils.notNull(org.getParentId(),"上级机构不能为空");
		orgService.addOrg(org);
		return ReturnVo.success(org.getId());
	}

	/**
	 * 机构列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody FindOrgPage param) {
		List<OrgDto> orgDtos = orgService.findOrgs(param);// 总一级结构
		Integer rootId = param.getParentId() == null ? IOrgService.ROOT_ID : param.getParentId();
		OrgDto orgDto = orgSort(orgDtos,rootId);// 分级关系结构
		return ReturnVo.success(orgDto);
	}
	
	private OrgDto orgSort(List<OrgDto> orgDtos,Integer parentId){
		OrgDto orgDt = getRoot(orgDtos, parentId);
		orgDt.setChild(getOrgByParentId(orgDtos, orgDt.getId()));
		return orgDt;
	}
	
	private List<OrgDto> getOrgByParentId(List<OrgDto> orgDtos,Integer parentId){
		List<OrgDto> data = new ArrayList<>();
		for (Iterator<OrgDto> iterator = orgDtos.iterator(); iterator.hasNext();) {
			OrgDto orgDto = iterator.next();
			if(orgDto.getParentId()==parentId) {
				data.add(orgDto);
			}
		}
		
		for (OrgDto orgDto : data) {
			orgDto.setChild(getOrgByParentId(orgDtos, orgDto.getId()));
		}
		return data;
	}
	
	private OrgDto getRoot(List<OrgDto> orgDtos,Integer parentId){
		OrgDto rootOrg = null;
		for (Iterator<OrgDto> iterator = orgDtos.iterator(); iterator.hasNext();) {
			OrgDto orgDto = iterator.next();
			if (orgDto.getParentId() == parentId) {
				rootOrg=orgDto;
				break;
			}
		}
		return rootOrg;
	}
	
	/**
	 * 机构修改
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public ReturnVo edit(@RequestBody OrgDto org) {
		orgService.updateOrg(org);
		return ReturnVo.success(null);
	}
}
