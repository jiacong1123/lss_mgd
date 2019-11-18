package com.lss.core.vo.hx;

import java.util.Date;

public class HxClue {
    /** 线索号*/
    private String code;

    /** 客户姓名*/
    private String name;

    /** 电话号码*/
    private String phone;

    /** 性别*/
    private String sex;

    /** 年龄*/
    private Integer age;

    /** 省名称*/
    private String province;

    /** 市名称*/
    private String city;

    /** 区名称*/
    private String area;

    /** 完整地址（省市区详细地址全部包含）*/
    private String addrInfo;

    /** 来源*/
    private String source;

    /** 微信号*/
    private String wechatNo;

    /** 微信昵称*/
    private String wechatName;

    /** 客户类型*/
    private String userType;

    /** 客户单价*/
    private Long userPrice;

    /** 状态(FREEZE冻结中，CANG可抢，HASG已被抢）)*/
    private String status;

    /** 是否有效(VALID:有效，INVALID：无效)*/
    private String validStatus;

    /** 添加日期*/
    private Date createTime;

    /** 工单号*/
    private String orderNo;

    /** 预约项目*/
    private String project;

    /** 预约日期*/
    private String reserveDate;

    /** 预约时间*/
    private String reserveTime;

    /** 跟进人员*/
    private String followName;

    /** 意愿等级*/
    private String wishLevel;

    /** 主诉*/
    private String complaint;

    /** 备注*/
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Long getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(Long userPrice) {
        this.userPrice = userPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime == null ? null : reserveTime.trim();
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName == null ? null : followName.trim();
    }

    public String getWishLevel() {
        return wishLevel;
    }

    public void setWishLevel(String wishLevel) {
        this.wishLevel = wishLevel == null ? null : wishLevel.trim();
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}