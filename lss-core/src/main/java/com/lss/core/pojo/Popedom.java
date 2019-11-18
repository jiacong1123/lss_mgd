package com.lss.core.pojo;

public class Popedom {
	private Integer popeid;

	private String name;

	private String icon;

	private String url;

	private Integer level;

	private Integer parentid;

	public Integer getPopeid() {
		return popeid;
	}

	public void setPopeid(Integer popeid) {
		this.popeid = popeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}