package com.lss.core.pojo;

import java.util.Date;

public class Admin {
    private Integer adminid;

    private Integer clinicid;

    private Integer doctorid;

    private String loginame;

    private String loginpwd;

    private String name;

    private String phone;

    private Integer status;

    private Date createtime;

    private Date logintime;
    
    /** 话机号码，多个英文逗号分割， 电话号码, 固话带区号*/
    private String callerNos;

    /** 小号*/
    private String transferNo;
    
    /** 所属部门id*/
    private Integer orgId;

    /** 所属部门名称*/
    private String orgName;

    /** 微信号*/
    private String noWx;

    /** ec系统用户ID*/
    private String ecUserId;
    
    
    
    public String getEcUserId() {
		return ecUserId;
	}

	public void setEcUserId(String ecUserId) {
		this.ecUserId = ecUserId;
	}

	public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getClinicid() {
        return clinicid;
    }

    public void setClinicid(Integer clinicid) {
        this.clinicid = clinicid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public String getLoginame() {
        return loginame;
    }

    public void setLoginame(String loginame) {
        this.loginame = loginame == null ? null : loginame.trim();
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd == null ? null : loginpwd.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

	public String getCallerNos() {
		return callerNos;
	}

	public void setCallerNos(String callerNos) {
		this.callerNos = callerNos;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
    
}