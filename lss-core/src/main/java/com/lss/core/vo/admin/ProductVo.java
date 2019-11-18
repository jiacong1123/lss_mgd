package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.pojo.Pictures;
import com.lss.core.pojo.Product;

public class ProductVo extends Product {
	private String classname;
	private List<Pictures> list;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public List<Pictures> getList() {
		return list;
	}

	public void setList(List<Pictures> list) {
		this.list = list;
	}
}
