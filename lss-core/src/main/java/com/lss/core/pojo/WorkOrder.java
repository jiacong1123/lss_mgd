package com.lss.core.pojo;

import java.util.Date;

public class WorkOrder {
    private String orderno;

    private Integer userid;
    //状态 0未分配 1待跟进 2已预约 3已到店 4已完成 5已关闭 10新分配 99全部 98待回收列表
    private Integer status;

    private Date createtime;

    private Integer clinicid;

    private Integer projectid;

    private Integer adminid;

    private String complaint;

    private String worknotes;

    private String level;

    private Date returndate;

    private Date reservedate;

    private String reservetime;

    private String closereason;

    private Integer doctorid;

    private Date arrivaltime;

    private Integer followup;

    private Integer pid;

    private Integer isclue;

    private String usertype;

    private String usertypename;

    private Integer cluestatus;

    private Integer ordertype;

    private String clinicname;

    private Date visitingtime;
	/** 实收金额（分为单位）*/
    private Long amount;
    /** 标签名称，多个则英文逗号分割*/
    private String lablenames;

    /** 标签备注，json存贮用于前后端解析*/
    private String lableremarks;

    /** 应收金额(分为单位）*/
    private Long receivablesamt;

    /** 欠收金额(分为单位）*/
    private Long debtamt;
    /** 收费时间*/
    private Date payTime;
    
    /** 最新跟进时间*/
    private Date followuptime;

    /** 分配时间*/
    private Date allottime;

    /** 最新跟进备注*/
    private String followupremarks;
    
    /** 回访状态:1-未回访;2-已回访*/
    private String isReturn;
    
    

    public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getClinicid() {
        return clinicid;
    }

    public void setClinicid(Integer clinicid) {
        this.clinicid = clinicid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
    }

    public String getWorknotes() {
        return worknotes;
    }

    public void setWorknotes(String worknotes) {
        this.worknotes = worknotes == null ? null : worknotes.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Date getReservedate() {
        return reservedate;
    }

    public void setReservedate(Date reservedate) {
        this.reservedate = reservedate;
    }

    public String getReservetime() {
        return reservetime;
    }

    public void setReservetime(String reservetime) {
        this.reservetime = reservetime == null ? null : reservetime.trim();
    }

    public String getClosereason() {
        return closereason;
    }

    public void setClosereason(String closereason) {
        this.closereason = closereason == null ? null : closereason.trim();
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Integer getFollowup() {
        return followup;
    }

    public void setFollowup(Integer followup) {
        this.followup = followup;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIsclue() {
        return isclue;
    }

    public void setIsclue(Integer isclue) {
        this.isclue = isclue;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getUsertypename() {
        return usertypename;
    }

    public void setUsertypename(String usertypename) {
        this.usertypename = usertypename == null ? null : usertypename.trim();
    }

    public Integer getCluestatus() {
        return cluestatus;
    }

    public void setCluestatus(Integer cluestatus) {
        this.cluestatus = cluestatus;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname == null ? null : clinicname.trim();
    }

    public Date getVisitingtime() {
        return visitingtime;
    }

    public void setVisitingtime(Date visitingtime) {
        this.visitingtime = visitingtime;
    }

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getLablenames() {
		return lablenames;
	}

	public void setLablenames(String lablenames) {
		this.lablenames = lablenames;
	}

	public String getLableremarks() {
		return lableremarks;
	}

	public void setLableremarks(String lableremarks) {
		this.lableremarks = lableremarks;
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

	public Date getFollowuptime() {
		return followuptime;
	}

	public void setFollowuptime(Date followuptime) {
		this.followuptime = followuptime;
	}

	public Date getAllottime() {
		return allottime;
	}

	public void setAllottime(Date allottime) {
		this.allottime = allottime;
	}

	public String getFollowupremarks() {
		return followupremarks;
	}

	public void setFollowupremarks(String followupremarks) {
		this.followupremarks = followupremarks;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
    
}