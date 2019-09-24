package com.lss.core.vo;

/**
 * 分页数据对象
 * 
 * @author SWWH
 *
 */
public class PageVo {

	/**
	 * 页数
	 */
	private Integer page;
	/**
	 * 每页数量
	 */
	private Integer limit = 10;
	/**
	 * 总数
	 */
	private Integer total;
	/**
	 * 总页数
	 */
	private Integer totalPage;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
		if (this.page != null) {
			if (total % this.limit == 0) {
				this.totalPage = total / this.limit;
			} else {
				this.totalPage = total / this.limit + 1;
			}
		}
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
