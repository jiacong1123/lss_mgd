package com.lss.core.vo.admin;

import java.util.Date;
import java.util.List;

import com.lss.core.constant.SystemConstant;
import com.lss.core.util.DateUtils;
import com.lss.core.vo.ztc.ZtcPersonMember;

public class WorkOrderVo {
	private String orderno;
	private String name;
	private String phone;
	private String source;
	private String clinicname;
	private String project;
	private String adminname;
	private String level;
	private String doctorname;
	private Date returndate;
	
	private Integer clinicid;
	private Integer projectid;
	private Integer adminid;
	private Integer doctorid;
	private String reservetime;
	private Date reservedate;
	private Integer isclue;
	private String worknotes;
	private Date createtime;
    private String closereason;
	/** 实收金额（分为单位）*/
    private Long amount;
    /** 应收金额(分为单位）*/
    private Long receivablesamt;
    private Integer status;
    private Integer followup;
    
	/** 微信好友 2019.05.13 lhy*/
	List<ZtcPersonMember> personMembers;
	/**来源日期*/
	private Date sourcedate;
    private Integer sex;
    /** 2级来源名称*/
    private String sourcename2;
    
    /** 标签名称，多个则英文逗号分割*/
    private String lablenames;

    /** 标签备注，json存贮用于前后端解析*/
    private String lableremarks;
    /** 用户ID */
    private Integer userid;
    
    /** 最新跟进时间*/
    private Date followuptime;
    /** 最新跟进备注*/
    private String followupremarks;
    /** 调库日期 */
    private Date closedate;
    /** 回访状态:1-未回访;2-已回访*/
    private String isReturn;
    
    /** 分配日期 */
    private Date allottime;
    
    private String city;
    
    
    
    
    
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getAllottime() {
		return allottime;
	}

	public void setAllottime(Date allottime) {
		this.allottime = allottime;
	}

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
		this.orderno = orderno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
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

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public String getReservetime() {
		return reservetime;
	}

	public void setReservetime(String reservetime) {
		this.reservetime = reservetime;
	}

	public Date getReservedate() {
		return reservedate;
	}

	public void setReservedate(Date reservedate) {
		this.reservedate = reservedate;
	}

	public Integer getIsclue() {
		return isclue;
	}

	public void setIsclue(Integer isclue) {
		this.isclue = isclue;
	}

	public String getWorknotes() {
		return worknotes;
	}

	public void setWorknotes(String worknotes) {
		this.worknotes = worknotes;
	}

	public List<ZtcPersonMember> getPersonMembers() {
		return personMembers;
	}

	public void setPersonMembers(List<ZtcPersonMember> personMembers) {
		this.personMembers = personMembers;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getClosereason() {
		return closereason;
	}

	public void setClosereason(String closereason) {
		this.closereason = closereason;
	}

	public Date getSourcedate() {
		return sourcedate;
	}

	public void setSourcedate(Date sourcedate) {
		this.sourcedate = sourcedate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSourcename2() {
		return sourcename2;
	}

	public void setSourcename2(String sourcename2) {
		this.sourcename2 = sourcename2;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFollowup() {
		return followup;
	}

	public void setFollowup(Integer followup) {
		this.followup = followup;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getFollowuptime() {
		return followuptime;
	}

	public void setFollowuptime(Date followuptime) {
		this.followuptime = followuptime;
	}

	public String getFollowupremarks() {
		return followupremarks;
	}

	public void setFollowupremarks(String followupremarks) {
		this.followupremarks = followupremarks;
	}

	public Date getClosedate() {
		if (closedate == null && followuptime != null) {
			Date now = DateUtils.getDateDay(followuptime);
			//关闭时间
			Date nextDay = DateUtils.addDate(now, (-SystemConstant.WORK_ORDER_CLOSE_DAY+1));
			//若关闭时间在今天之前（过去）或今天，则关闭日期为明天
			Date today = DateUtils.getDateDay(new Date());
			if(nextDay.before(today)||nextDay.equals(today)) {
				nextDay=DateUtils.addDate(today, 1);
			}
			return nextDay;
		}
		return closedate;
	}

	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}

	public Long getReceivablesamt() {
		return receivablesamt;
	}

	public void setReceivablesamt(Long receivablesamt) {
		this.receivablesamt = receivablesamt;
	}
	
}
