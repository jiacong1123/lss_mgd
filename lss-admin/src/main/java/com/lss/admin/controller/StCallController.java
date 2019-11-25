package com.lss.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.IStCallService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dto.FindStCallPage;
import com.lss.core.dto.StCallDto;
import com.lss.core.dto.StCallExportDto;
import com.lss.core.exception.LssException;
import com.lss.core.util.DateUtils;
import com.lss.core.util.ExportExcel;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;

/**
 * 电联统计查询
 * 
 * @author lhy 2019.08.16
 *
 */
@RestController
@RequestMapping("stcall")
public class StCallController extends BaseController {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StCallController.class);

	@Resource
	private IStCallService stCallService;

	/**
	 * 今日联系
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("list/today")
	public ReturnVo findTodayStCallPage(@RequestBody FindStCallPage params) {

		return stCallService.findTodayStCallPage(params, loginAdmin);
	}
	
	/**
	 * 今日联系统计
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("list/today/count")
	public ReturnVo countToday(@RequestBody FindStCallPage params) {
		params.setPage(1);
		params.setLimit(100000000);
		return stCallService.findTodayStCallCount(params, loginAdmin);
	}

	/**
	 * 历史联系
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("list/history")
	public ReturnVo findHistoryStCallPage(@RequestBody FindStCallPage params) {
		if(StringUtils.isNotEmpty(params.getStartDateStr())) {
			params.setStartDate(DateUtils.parseDate(params.getStartDateStr()));
		}
		if(StringUtils.isNotEmpty(params.getEndDateStr())) {
			params.setEndDate(DateUtils.parseDate(params.getEndDateStr()));	
		}
		LoginAdmin admin = loginAdmin;
		// 查询条件
		if (ObjectUtil.isEmpty(admin.getRoles())) {
			throw new LssException(ResponseCode.failure, "还未分配角色");
		}
		// 如果是 电销组长查小组成员的，是电销员则只查自己的
		if (admin.getRoles().contains(9)) {// 电销组长
			params.setAdminids(ServiceManager.adminService.selectGroupAdminids(admin.getAdminid()));
		} else if (admin.getRoles().contains(3)) {// 电销员
			List<Integer> adminids = new ArrayList<Integer>();
			adminids.add(admin.getAdminid());
			params.setAdminids(adminids);
		}
		return stCallService.findStCallPage(params);
	}
	
	/**
	 * 历史联系统计
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("list/history/count")
	public ReturnVo countHistory(@RequestBody FindStCallPage params) {
		params.setPage(1);
		params.setLimit(100000000);
		if(StringUtils.isNotEmpty(params.getStartDateStr())) {
			params.setStartDate(DateUtils.parseDate(params.getStartDateStr()));
		}
		if(StringUtils.isNotEmpty(params.getEndDateStr())) {
			params.setEndDate(DateUtils.parseDate(params.getEndDateStr()));	
		}
		LoginAdmin admin = loginAdmin;
		// 查询条件
		if (ObjectUtil.isEmpty(admin.getRoles())) {
			throw new LssException(ResponseCode.failure, "还未分配角色");
		}
		// 如果是 电销组长查小组成员的，是电销员则只查自己的
		if (admin.getRoles().contains(9)) {// 电销组长
			params.setAdminids(ServiceManager.adminService.selectGroupAdminids(admin.getAdminid()));
		} else if (admin.getRoles().contains(3)) {// 电销员
			List<Integer> adminids = new ArrayList<Integer>();
			adminids.add(admin.getAdminid());
			params.setAdminids(adminids);
		}
		return stCallService.findStCallCount(params);
	}

	/**
	 * 批量导出今日联系
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("export/today")
	public void exportTodayStCall(FindStCallPage params) {
		params.setPage(0);// 0 查询所有的数据
		try {
			ReturnVo findData = stCallService.findTodayStCallPage(params, loginAdmin);

			List<StCallDto> list = (List<StCallDto>) findData.getObj();
			List<StCallExportDto> exportData = new ArrayList<StCallExportDto>();
			if (ObjectUtil.isNotEmpty(list)) {
				for (StCallDto ele : list) {
					StCallExportDto exDto=new StCallExportDto();
					exDto.setStDate(DateUtils.formatdateFormat(ele.getStDate()));
					exDto.setAdminname(ele.getAdminname());
					exDto.setDuration(ele.getDuration());
					exDto.setAvgDuration(ele.getAvgDuration());
					exDto.setCallCount(ele.getCallCount());
					
					exportData.add(exDto);
				}
			}
			ExportExcel<StCallExportDto> ee = new ExportExcel<StCallExportDto>();
			String[] headers = {"统计时间", "员工姓名", "通话次数", "通话总时长（秒）", "平均时长（秒）"};
			String fileName = "电联今日统计";
			ee.exportExcel(headers, exportData, fileName, response);
			
		} catch (Exception e) {
			logger.error("导出信息失败：", e);
		}
	}

	/**
	 * 批量导出历史联系
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("export/history")
	public void exportHistoryStCall(FindStCallPage params) {
		params.setPage(0);// 0 查询所有的数据
		try {
			ReturnVo findData = findHistoryStCallPage(params);

			List<StCallDto> list = (List<StCallDto>) findData.getObj();
			List<StCallExportDto> exportData = new ArrayList<StCallExportDto>();
			if (ObjectUtil.isNotEmpty(list)) {
				for (StCallDto ele : list) {
					StCallExportDto exDto=new StCallExportDto();
					exDto.setStDate(DateUtils.formatdateFormat(ele.getStDate()));
					exDto.setAdminname(ele.getAdminname());
					exDto.setDuration(ele.getDuration());
					exDto.setAvgDuration(ele.getAvgDuration());
					exDto.setCallCount(ele.getCallCount());
					
					exportData.add(exDto);
				}
			}
			ExportExcel<StCallExportDto> ee = new ExportExcel<StCallExportDto>();
			String[] headers = {"统计时间", "员工姓名", "通话次数", "通话总时长（秒）", "平均时长（秒）"};
			String fileName = "电联历史统计";
			ee.exportExcel(headers, exportData, fileName, response);
		} catch (Exception e) {
			logger.error("导出信息失败：", e);
		}
	}
	 
}
