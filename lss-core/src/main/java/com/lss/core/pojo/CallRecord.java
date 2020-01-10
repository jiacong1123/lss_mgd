package com.lss.core.pojo;

import java.util.Date;

public class CallRecord {
    /** id*/
    private Integer id;

    /** 话单唯一标识*/
    private String recordId;

    /** 记录类型 OutBound_Call, 外呼电话；OutBound_Unkown, 陌生去电； InBound_Call, 客户电话； InBound_Call_Channel, 渠道电话； Unknow_Call, 陌生电话；*/
    private String type;

    /** 小号*/
    private String transferNo;

    /** 被叫显示号码*/
    private String showNo;

    /** 通话状态 ANSWERED(通话成功) ；BUSY(被叫忙)；NO_ANSWER(被叫无应答)；REJECT(被叫拒接)；HANGUP(主叫提前挂机)；INVALID_NUMBER(空号, 连连后台使用 信号异常)；POWER_OFF(关机)；UNAVAILABLE(暂时无法接听, 连连后台使用 信号异常)；SUSPEND(停机)；BLACK(黑名单号码)；OTHER(其他失败情形, 连连后台使用信号异常)*/
    private String llResult;

    /** 通话开始时间*/
    private String startTime;

    /** 通话结束时间*/
    private String endTime;

    /** 通话时长, 大于 0 已接通, 否则未接通*/
    private Integer duration;

    /** 录音地址, HTTP 地址, MP3 格式*/
    private String recordingUrl;

    /** 通话扩展信息 JSON,*/
    private String callInfo;

    /** 顾问编号*/
    private String empId;

    /** 顾问号码,*/
    private String empNo;

    /** 顾问号码归属地*/
    private String empNoArea;

    /** 顾问扩展信息 JSON*/
    private String empInfo;

    /** 客户编号*/
    private String cusId;

    /** 客户号码*/
    private String cusNo;

    /** 客户号码归属地*/
    private String cusNoArea;

    /** 客户扩展信息*/
    private String cusInfo;

    /** 数据处理状态（INIT:初始 ;PROCESSED:已处理 ）*/
    private String processStatus;

    /** 管理员ID*/
    private Integer adminId;

    /** 管理员名称*/
    private String adminName;

    /** 管理员手机*/
    private String adminPhone;

    /** 用户ID*/
    private Integer userId;

    /** 用户名称*/
    private String userName;

    /** 工单进度ID*/
    private Integer workrecordId;

    /** 工单号*/
    private String orderno;

    /** 自存录音URL*/
    private String lssRecordUrl;

    /** 创建时间*/
    private Date createTime;

    /** 修改时间*/
    private Date updateDate;
    
    /** 通话类型:EC/连连小号/超脑云固话*/
    private String callType;


    public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

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

    public String getLlResult() {
        return llResult;
    }

    public void setLlResult(String llResult) {
        this.llResult = llResult == null ? null : llResult.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl == null ? null : recordingUrl.trim();
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

    public String getEmpNoArea() {
        return empNoArea;
    }

    public void setEmpNoArea(String empNoArea) {
        this.empNoArea = empNoArea == null ? null : empNoArea.trim();
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

    public String getCusNoArea() {
        return cusNoArea;
    }

    public void setCusNoArea(String cusNoArea) {
        this.cusNoArea = cusNoArea == null ? null : cusNoArea.trim();
    }

    public String getCusInfo() {
        return cusInfo;
    }

    public void setCusInfo(String cusInfo) {
        this.cusInfo = cusInfo == null ? null : cusInfo.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getWorkrecordId() {
        return workrecordId;
    }

    public void setWorkrecordId(Integer workrecordId) {
        this.workrecordId = workrecordId;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getLssRecordUrl() {
        return lssRecordUrl;
    }

    public void setLssRecordUrl(String lssRecordUrl) {
        this.lssRecordUrl = lssRecordUrl == null ? null : lssRecordUrl.trim();
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