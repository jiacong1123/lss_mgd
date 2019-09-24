package com.lss.admin.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.IStOrderService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dto.FindStOrderPage;
import com.lss.core.dto.StOrderDto;
import com.lss.core.dto.StOrderExportDto;
import com.lss.core.exception.LssException;
import com.lss.core.util.DateUtils;
import com.lss.core.util.ExportExcel;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;

/**
 * 员工工单月成交统计 查询
 * 
 * @author lhy 2019.08.16
 *
 */
@RestController
@RequestMapping("storder")
public class StOrderController extends BaseController {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StOrderController.class);

	@Resource
	private IStOrderService stOrderService;

	/**
	 * 历史统计
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("list/history")
	public ReturnVo findHistoryStOrderPage(@RequestBody FindStOrderPage params) {
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
		return stOrderService.findStOrderPage(params);
	}
	
	/**
	 * 批量导出历史联系
	 * @author lhy 2019.08.15 v1.4
	 */
	@RequestMapping("export/history")
	public void exportHistoryStCall(FindStOrderPage params) {
		params.setPage(0);// 0 查询所有的数据
		try {
			ReturnVo findData = findHistoryStOrderPage(params);

			List<StOrderDto> list = (List<StOrderDto>) findData.getObj();
			List<StOrderExportDto> exportData = new ArrayList<StOrderExportDto>();
			if (ObjectUtil.isNotEmpty(list)) {
				BigDecimal bigD100=new BigDecimal(100); 
				for (StOrderDto ele : list) {
					StOrderExportDto exDto=new StOrderExportDto();
					exDto.setStDate(DateUtils.formatdateMonthFormatYYYY_MM(ele.getStDate()));
					exDto.setAdminname(ele.getAdminname());
					exDto.setAdminname(ele.getAdminname());
					exDto.setAllotUserCount(ele.getAllotUserCount());
					exDto.setReserveUserCount(ele.getReserveUserCount());
					exDto.setHisReserveCount(ele.getHisReserveCount());
					exDto.setAllReserveCount(ele.getAllReserveCount());
					exDto.setMonReserveRate(ele.getMonReserveRate());
					exDto.setAllReserveRate(ele.getAllReserveRate());
					exDto.setBigDealCount(ele.getBigDealCount());
					exDto.setBigDealRate(ele.getBigDealRate());
					exDto.setAllDealAmt(
							new BigDecimal(ele.getAllDealAmt()).divide(bigD100, 2, RoundingMode.HALF_UP));
					 
					exportData.add(exDto);
				}
			}
			ExportExcel<StOrderExportDto> ee = new ExportExcel<StOrderExportDto>();

			String[] headers = {"月份", "员工姓名", "月客户数", "新到店数", "历史到店数", "总到店数"
					, "新客到店率", "综合到店率", "大项成交数", "成交率（大项）", "业绩完成（元）" };
			String fileName = "历史成交统计";
			ee.exportExcel(headers, exportData, fileName, response);
		} catch (Exception e) {
			logger.error("导出信息失败：", e);
		}
	}
	
	/**
	 * 当月成交
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.08.19 v1.4
	 */
	@RequestMapping("list/currmonth")
	public ReturnVo findCurrmonthStOrderPage(@RequestBody FindStOrderPage params) {
		return stOrderService.findStOrderCurrMonthPage(params , loginAdmin);
	}
	
	/**
	 * 批量导出当月成交
	 * @author lhy 2019.08.19 v1.4
	 */
	@RequestMapping("export/currmonth")
	public void exportCurrmonthStOrder(FindStOrderPage params) {
		params.setPage(0);// 0 查询所有的数据
		try {
			ReturnVo findData = stOrderService.findStOrderCurrMonthPage(params, loginAdmin);

			List<StOrderDto> list = (List<StOrderDto>) findData.getObj();
			List<StOrderExportDto> exportData = new ArrayList<StOrderExportDto>();
			if (ObjectUtil.isNotEmpty(list)) {
				BigDecimal bigD100=new BigDecimal(100); 
				for (StOrderDto ele : list) {
					StOrderExportDto exDto=new StOrderExportDto();
					exDto.setStDate(ele.getStDateStr());
					exDto.setAdminname(ele.getAdminname());
					exDto.setAdminname(ele.getAdminname());
					exDto.setAllotUserCount(ele.getAllotUserCount());
					exDto.setReserveUserCount(ele.getReserveUserCount());
					exDto.setHisReserveCount(ele.getHisReserveCount());
					exDto.setAllReserveCount(ele.getAllReserveCount());
					exDto.setMonReserveRate(ele.getMonReserveRate());
					exDto.setAllReserveRate(ele.getAllReserveRate());
					exDto.setBigDealCount(ele.getBigDealCount());
					exDto.setBigDealRate(ele.getBigDealRate());
					exDto.setAllDealAmt(new BigDecimal(ele.getAllDealAmt()).divide(bigD100, 2, RoundingMode.HALF_UP));
					 
					exportData.add(exDto);
				}
			}
			ExportExcel<StOrderExportDto> ee = new ExportExcel<StOrderExportDto>();

			String[] headers = {"月份", "员工姓名", "月客户数", "新到店数", "历史到店数", "总到店数"
					, "新客到店率", "综合到店率", "大项成交数", "成交率（大项）", "业绩完成（元）" };
			String fileName = "当月成交统计";
			ee.exportExcel(headers, exportData, fileName, response);
			
		} catch (Exception e) {
			logger.error("导出信息失败：", e);
		}
	}
 
}
