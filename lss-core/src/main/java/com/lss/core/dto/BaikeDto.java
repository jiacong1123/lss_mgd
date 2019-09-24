package com.lss.core.dto;

import java.io.Serializable;
import java.util.Date;

public class BaikeDto implements Serializable { 

    /** 主键id*/
    private Integer id;

    /** 标题*/
    private String title;

    /** 副标题*/
    private String subtitle;

    /** 主图*/
    private String image;

    /** 类型（1.口腔百科）*/
    private Integer type;

    /** 状态 1启用 0禁用 -1删除*/
    private Integer status;

    /** 创建时间*/
    private Date createtime;

    /** 点击量*/
    private Integer clickvolume;

    /** 小图*/
    private String smallIcon;

    /** */
    private String url;

    /** 内容*/
    private String content;
    
    /** 标签，多个则英文逗号分割 （隐适美、矫正、种植、贴面）*/
    private String lables;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getClickvolume() {
        return clickvolume;
    }

    public void setClickvolume(Integer clickvolume) {
        this.clickvolume = clickvolume;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon == null ? null : smallIcon.trim();
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

	public String getLables() {
		return lables;
	}

	public void setLables(String lables) {
		this.lables = lables;
	}
    
}
