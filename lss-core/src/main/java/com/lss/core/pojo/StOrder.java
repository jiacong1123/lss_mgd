package com.lss.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StOrder {
    /** id*/
    private Integer id;

    /** 管理员ID*/
    private Integer adminid;

    /** 登记人姓名*/
    private String adminname;

    /** 统计月份*/
    private Date stDate;

    /** 月客户数（当月分配的客户数）*/
    private Integer allotUserCount;

    /** 新到店数（当月到店的客户）*/
    private Integer reserveUserCount;

    /** 历史到店数（累计到店-当月到店）*/
    private Integer hisReserveCount;

    /** 总到店数:累计到店（新客到店数+历史到店数）*/
    private Integer allReserveCount;

    /** 新客到店率（新客到店数÷月客户数）*/
    private BigDecimal monReserveRate;

    /** 综合到店率（总到店数÷月客户数（本月分配的客户））*/
    private BigDecimal allReserveRate;

    /** 大项成交数（大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交））*/
    private Integer bigDealCount;

    /** 成交率（大项）（大项目成交数÷总到店数）*/
    private BigDecimal bigDealRate;

    /** 业绩完成（分为单位）（状态（已完成）+全部项目实收金额（大项目+其他））*/
    private Long allDealAmt;

    /** 创建时间*/
    private Date createDate;

    /** 修改时间*/
    private Date updateDate;

    /** 备注*/
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Integer getAllotUserCount() {
        return allotUserCount;
    }

    public void setAllotUserCount(Integer allotUserCount) {
        this.allotUserCount = allotUserCount;
    }

    public Integer getReserveUserCount() {
        return reserveUserCount;
    }

    public void setReserveUserCount(Integer reserveUserCount) {
        this.reserveUserCount = reserveUserCount;
    }

    public Integer getHisReserveCount() {
        return hisReserveCount;
    }

    public void setHisReserveCount(Integer hisReserveCount) {
        this.hisReserveCount = hisReserveCount;
    }

    public Integer getAllReserveCount() {
        return allReserveCount;
    }

    public void setAllReserveCount(Integer allReserveCount) {
        this.allReserveCount = allReserveCount;
    }

    public BigDecimal getMonReserveRate() {
        return monReserveRate;
    }

    public void setMonReserveRate(BigDecimal monReserveRate) {
        this.monReserveRate = monReserveRate;
    }

    public BigDecimal getAllReserveRate() {
        return allReserveRate;
    }

    public void setAllReserveRate(BigDecimal allReserveRate) {
        this.allReserveRate = allReserveRate;
    }

    public Integer getBigDealCount() {
        return bigDealCount;
    }

    public void setBigDealCount(Integer bigDealCount) {
        this.bigDealCount = bigDealCount;
    }

    public BigDecimal getBigDealRate() {
        return bigDealRate;
    }

    public void setBigDealRate(BigDecimal bigDealRate) {
        this.bigDealRate = bigDealRate;
    }

    public Long getAllDealAmt() {
        return allDealAmt;
    }

    public void setAllDealAmt(Long allDealAmt) {
        this.allDealAmt = allDealAmt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}