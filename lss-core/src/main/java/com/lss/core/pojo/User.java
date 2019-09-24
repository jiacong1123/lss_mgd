package com.lss.core.pojo;

import java.util.Date;

public class User {
    private Integer userid;

    private String phone;

    private String password;

    private String name;

    private Integer sex;

    private Integer age;

    private String wechat;

    private String province;

    private String city;

    private String area;

    private String photo;

    private Integer sourceid;

    private Date sourcedate;

    private Integer status;

    private Date createtime;

    private String notes;
    /** 微信号*/
    private String noWxAlias;
    /** 2级来源id*/
    private Integer sourceid2;

    /** 2级来源名称*/
    private String sourcename2;
    
    /** 标签名称，多个则英文逗号分割*/
    private String lablenames;

    /** 标签备注，json存贮用于前后端解析*/
    private String lableremarks;
    
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Date getSourcedate() {
        return sourcedate;
    }

    public void setSourcedate(Date sourcedate) {
        this.sourcedate = sourcedate;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

	public String getNoWxAlias() {
		return noWxAlias;
	}

	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
	}

	public Integer getSourceid2() {
		return sourceid2;
	}

	public void setSourceid2(Integer sourceid2) {
		this.sourceid2 = sourceid2;
	}

	public String getSourcename2() {
		return sourcename2;
	}

	public void setSourcename2(String sourcename2) {
		this.sourcename2 = sourcename2;
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
    
    
}