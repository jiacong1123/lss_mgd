package com.lss.core.vo.admin;

import java.util.List;

public class EcPhoneRecodeVo {

	private int maxPageNo;
	private int pageNo;
	private int pageSize;
	private int startRow;
	private int total;
	private List<EcPhoneRecodeResultVo> result;
	
	public int getMaxPageNo() {
		return maxPageNo;
	}
	public void setMaxPageNo(int maxPageNo) {
		this.maxPageNo = maxPageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<EcPhoneRecodeResultVo> getResult() {
		return result;
	}
	public void setResult(List<EcPhoneRecodeResultVo> result) {
		this.result = result;
	}
	
	
	
	
}
