package com.lss.core.pojo;

import java.util.Date;
/*
 * 用户统计
 */
public class UserCount {

	private int status;
	private String tagname;
	private String tagname2;
	private Date sourcedate;
	private Date allottime;
	private String level;
	private int number;
	private String percent;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public Date getSourcedate() {
		return sourcedate;
	}
	public void setSourcedate(Date sourcedate) {
		this.sourcedate = sourcedate;
	}
	public Date getAllottime() {
		return allottime;
	}
	public void setAllottime(Date allottime) {
		this.allottime = allottime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getTagname2() {
		return tagname2;
	}
	public void setTagname2(String tagname2) {
		this.tagname2 = tagname2;
	}
	
}
