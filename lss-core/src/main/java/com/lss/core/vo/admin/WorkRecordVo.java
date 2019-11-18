package com.lss.core.vo.admin;

import java.util.Date;

public class WorkRecordVo {
	private Date time;
	private String name;
	private String content;
	 /** 状态（1成功 0失败）*/
    private Integer status;

    /** 备注*/
    private String remark;

    /** 录音地址*/
    private String recordUrl;
    /** 备注2,微信聊天时则记录终端及客户微信信息 */
    private String remark2;
    /** 客户直通车：终端微信*/
    private String shopWx;
    /** 客户直通车：客户编号 */
    private String memberNo;
    private String orderno;
	private Date start;
	private Date end;
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecordUrl() {
		return recordUrl;
	}

	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
