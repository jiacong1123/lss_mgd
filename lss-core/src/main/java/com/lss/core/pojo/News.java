package com.lss.core.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private String title;

    private String subtitle;

    private String image;

    private String content;

    private String mcontent;

    /** 类型（1.新闻动态 2.部落动态 3公益.活动）*/
    private Integer type;

    /** 状态 1启用 0禁用 -1删除*/
    private Integer status;

    /** 创建时间*/
    private Date createtime;

    /** 点击量*/
    private Integer clickvolume;

    /** 小图*/
    private String smallIcon;

    /** 链接 */
    private String url;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent == null ? null : mcontent.trim();
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
		this.smallIcon = smallIcon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}