package com.lss.core.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.lss.core.vo.PageParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindCallRecordPage extends PageParams {
	/** 参数 */
    private Integer id;

    /** 查询结束时间 */
    private Date endDate;
    
    /** 数据处理状态（INIT:初始 ;PROCESSED:已处理 ）*/
    private String processStatus;
    
    /** 顾问号码,*/
    private String empNo;
    
    /** 记录类型 OutBound_Call, 外呼电话；OutBound_Unkown, 陌生去电； InBound_Call, 客户电话； InBound_Call_Channel, 渠道电话； Unknow_Call, 陌生电话；*/
    private String type;
    
    /** 通话状态 ANSWERED(通话成功) ；BUSY(被叫忙)；NO_ANSWER(被叫无应答)；REJECT(被叫拒接)；HANGUP(主叫提前挂机)；INVALID_NUMBER(空号, 连连后台使用 信号异常)；POWER_OFF(关机)；UNAVAILABLE(暂时无法接听, 连连后台使用 信号异常)；SUSPEND(停机)；BLACK(黑名单号码)；OTHER(其他失败情形, 连连后台使用信号异常)*/
    private String llResult;
    
    /** 管理员名称*/
    private String adminName;
    
    /** 用户名称*/
    private String userName;
    
    /** 查询开始时间 */
    private Date startDate;
    
    /** 管理员Id*/
    private String adminId;
    
    /** 客户号码*/
    private String cusNo;
    
    /** 顾问登陆帐号*/
    private String empId;
    
    /** 通话记录id*/
    private String recordId;
    /** 通话类型:EC/连连小号/超脑云固话*/
    private String callType;
    
    
    
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLlResult() {
		return llResult;
	}

	public void setLlResult(String llResult) {
		this.llResult = llResult;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
