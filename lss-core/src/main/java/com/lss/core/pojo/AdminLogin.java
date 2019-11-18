package com.lss.core.pojo;

import java.util.Date;

public class AdminLogin {
    /** id*/
    private Integer id;

    /** 管理员ID*/
    private Integer adminid;

    /** 类型(WXGZH:微信公众号)*/
    private String type;

    /** 第三方唯一标识*/
    private String openid;

    /** 创建时间*/
    private Date createDate;

    /** 修改时间*/
    private Date updateDate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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
}