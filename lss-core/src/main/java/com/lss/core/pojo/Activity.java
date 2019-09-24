package com.lss.core.pojo;

import java.util.Date;

public class Activity {
	private Integer actid;

	private String title;

	private String image;

	private String url;

	private String content;

	private Integer popup;

	private String tcimage;

	private Integer sort;

	private Date start;

	private Date end;

	private Integer status;

	private Integer views;

	private Date createtime;

	public Integer getActid() {
		return actid;
	}

	public void setActid(Integer actid) {
		this.actid = actid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getPopup() {
		return popup;
	}

	public void setPopup(Integer popup) {
		this.popup = popup;
	}

	public String getTcimage() {
		return tcimage;
	}

	public void setTcimage(String tcimage) {
		this.tcimage = tcimage == null ? null : tcimage.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}