package com.lss.core.vo.admin;

import com.lss.core.pojo.User;

public class UserVo extends User {
	private String sourcename;
	private String msg;
	private String orderno;
	
	private Integer orderstatus;//工单状态
	private Integer adminid;//所属销售id
	private String adminname;//所属销售名称
	private Integer isclue;//0 不是线索 1 转线索
	private Integer followup;//跟进次数
	/**当前进行中的工单*/
	private WorkOrderVo order;
	
	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public Integer getIsclue() {
		return isclue;
	}

	public void setIsclue(Integer isclue) {
		this.isclue = isclue;
	}

	public Integer getFollowup() {
		return followup;
	}

	public void setFollowup(Integer followup) {
		this.followup = followup;
	}

	public WorkOrderVo getOrder() {
		return order;
	}

	public void setOrder(WorkOrderVo order) {
		this.order = order;
	}
	
}
