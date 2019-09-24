package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.pojo.Popedom;

public class PopedomVo extends Popedom {
	private List<PopedomVo> list;

	public List<PopedomVo> getList() {
		return list;
	}

	public void setList(List<PopedomVo> list) {
		this.list = list;
	}
}
