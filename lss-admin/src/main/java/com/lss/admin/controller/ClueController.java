package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClueParams;
import com.lss.core.vo.hx.ClueListVo;

/**
 * 线索
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("clue")
public class ClueController extends BaseController {

	/**
	 * 创建线索
	 * 
	 * @return
	 */
	@RequestMapping("create")
	public ReturnVo create(@RequestBody ClueParams params) {
		try {
			return ServiceManager.clueService.create(params);
		} catch (Exception e) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.error);
			result.setMsg(ResponseCode.errorMsg);
			return result;
		}
	}

	/**
	 * 派单
	 * 
	 * @return
	 */
	@RequestMapping("dispatch")
	public ReturnVo dispatch(@RequestBody ClueParams params) {
		try {
			return ServiceManager.clueService.dispatch(params);
		} catch (Exception e) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.error);
			result.setMsg(ResponseCode.errorMsg);
			return result;
		}
	}

	/**
	 * 线索列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody ClueParams params) {
		return ServiceManager.clueService.list(params);
	}

	/**
	 * 转跟进
	 * 
	 * @return
	 */
	@RequestMapping("turnfollowup")
	public ReturnVo turnfollowup(@RequestBody WorkRecord params) {
		try {
			return ServiceManager.clueService.turnfollowup(params, loginAdmin);
		} catch (Exception e) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.error);
			result.setMsg(ResponseCode.errorMsg);
			return result;
		}
	}

	/**
	 * 编辑线索
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public ReturnVo edit(@RequestBody ClueListVo vo) {
		try {
			return ServiceManager.clueService.edit(vo);
		} catch (Exception e) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.error);
			result.setMsg(ResponseCode.errorMsg);
			return result;
		}
	}

	/**
	 * 确认/取消到店
	 * 
	 * @return
	 */
	@RequestMapping("operation")
	public ReturnVo operation(@RequestBody ClueParams params) {
		try {
			return ServiceManager.clueService.operation(params);
		} catch (Exception e) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.error);
			result.setMsg(ResponseCode.errorMsg);
			return result;
		}
	}

	/**
	 * 接诊（换新调）
	 * 
	 * @return
	 */
	@RequestMapping("visiting")
	public ReturnVo visiting(WorkOrder params) {
		return ServiceManager.clueService.visiting(params);
	}

	/**
	 * 到店（换新调）
	 * 
	 * @return
	 */
	@RequestMapping("toshop")
	public ReturnVo toshop(String orderno) {
		return ServiceManager.clueService.toshop(orderno);
	}
}
