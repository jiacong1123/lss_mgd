package com.lss.core.pojo;

import java.util.Date;

public class WorkRecord {
    private Integer id;

    private String orderno;

    private Integer adminid;

    private String content;

    private Integer talktime;

    private Date createtime;
    /** 状态（1成功 0失败）*/
    private Integer status;

    /** 备注*/
    private String remark;

    /** 录音地址*/
    private String recordUrl;
    /** 备注2,微信聊天时则记录终端及客户微信信息 */
    private String remark2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTalktime() {
        return talktime;
    }

    public void setTalktime(Integer talktime) {
        this.talktime = talktime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
}