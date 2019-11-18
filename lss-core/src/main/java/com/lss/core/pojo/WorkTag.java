package com.lss.core.pojo;

import java.util.List;

public class WorkTag {
    /** 标签id*/
    private Integer tagid;

    /** 类型 1预约项目 2工单来源 3职称 4 医生科室 5换新请求路径*/
    private Integer type;

    /** 标签名称*/
    private String tagname;

    /** 级别*/
    private Integer grade;

    /** 上级ID*/
    private Integer parentid;
    
    /** 子集*/
    private List<WorkTag> child;
    
    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public List<WorkTag> getChild() {
		return child;
	}

	public void setChild(List<WorkTag> child) {
		this.child = child;
	}
    
    
}