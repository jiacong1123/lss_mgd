package com.lss.core.dto;

import java.io.Serializable;
import java.util.Date;

public class CallEventDto implements Serializable { 

    /** id*/
    private Integer id;

    /** 话单唯一标识*/
    private String recordId;

    /** 电话事件类型与场景(DIALING_OUTBOUND, 外呼去电拨打事件；DIALING_UNKNOWNOUTCALL, 陌生去电拨打事件；DIALING_INBOUND, 客户来电拨打事件；DIALING_CHANNEL, 渠道来电拨打事件；DIALING_UNKNOWN, 陌生来电拨打事件)*/
    private String type;

    /** 发生时间*/
    private String time;

    /** 小号*/
    private String transferNo;

    /** 被叫显示号码*/
    private String showNo;

    /** 扩展信息 JSON*/
    private String callInfo;

    /** 顾问编号*/
    private String empId;

    /** 顾问号码*/
    private String empNo;

    /** 顾问扩展信息 JSON*/
    private String empInfo;

    /** 客户编号*/
    private String cusId;

    /** 客户号码*/
    private String cusNo;

    /** 客户扩展信息 JSON*/
    private String cusInfo;

    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getTransferNo() {
        return transferNo;
    }

    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo == null ? null : transferNo.trim();
    }

    public String getShowNo() {
        return showNo;
    }

    public void setShowNo(String showNo) {
        this.showNo = showNo == null ? null : showNo.trim();
    }

    public String getCallInfo() {
        return callInfo;
    }

    public void setCallInfo(String callInfo) {
        this.callInfo = callInfo == null ? null : callInfo.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(String empInfo) {
        this.empInfo = empInfo == null ? null : empInfo.trim();
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId == null ? null : cusId.trim();
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo == null ? null : cusNo.trim();
    }

    public String getCusInfo() {
        return cusInfo;
    }

    public void setCusInfo(String cusInfo) {
        this.cusInfo = cusInfo == null ? null : cusInfo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
}
