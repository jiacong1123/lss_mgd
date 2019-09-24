package com.lss.core.pojo;

import java.util.Date;

public class OrderPay {
    /** id*/
    private Integer id;

    /** 管理员ID*/
    private Integer adminid;

    /** 工单号*/
    private String orderno;

    /** 应收（分为单位）*/
    private Long receivablesamt;

    /** 欠收（分为单位）*/
    private Long debtamt;

    /** 实收（分为单位）*/
    private Long amount;

    /** 收费时间*/
    private Date payTime;

    /** 创建时间*/
    private Date createDate;

    /** 修改时间*/
    private Date updateDate;

    /** 备注*/
    private String remark;

    /** 是否首次收费(Y是，N否)*/
    private String firstPay;

    /** 登记人姓名*/
    private String adminname;

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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Long getReceivablesamt() {
        return receivablesamt;
    }

    public void setReceivablesamt(Long receivablesamt) {
        this.receivablesamt = receivablesamt;
    }

    public Long getDebtamt() {
        return debtamt;
    }

    public void setDebtamt(Long debtamt) {
        this.debtamt = debtamt;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public String getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(String firstPay) {
        this.firstPay = firstPay == null ? null : firstPay.trim();
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }
}