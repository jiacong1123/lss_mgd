package com.lss.core.pojo;

import java.io.Serializable;

public class UserCountExeclVo implements Serializable {
	
	private String tagname;
	private String tagname2;
	private String sourcedate;
	private String allottime;
	private String level;
	private int number;
	private String percent;
	
	
	public String getTagname2() {
		return tagname2;
	}
	public void setTagname2(String tagname2) {
		this.tagname2 = tagname2;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getSourcedate() {
		return sourcedate;
	}
	public void setSourcedate(String sourcedate) {
		this.sourcedate = sourcedate;
	}
	public String getAllottime() {
		return allottime;
	}
	public void setAllottime(String allottime) {
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
	
	
}
