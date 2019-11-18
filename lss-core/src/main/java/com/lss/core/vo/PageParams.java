package com.lss.core.vo;

public class PageParams {
	private Integer page=1;
	private Integer limit=10;
	private Integer index;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
		if (this.limit != null)
			this.index = (page - 1) * this.limit;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
		if (this.page != null)
			this.index = (this.page - 1) * limit;
	}

	public Integer getIndex() {
		return index;
	}
}
