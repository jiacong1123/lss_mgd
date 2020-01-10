package com.lss.core.pojo;

public class UserSource {

	private String tagname;	//客户来源一
	private String tagname2;//客户来源二
	private int total;		//客户总数
	private int connect;	//接通数
	private String connectRate;//接通率
	private int reserve;	//预约数
	private String reserveRate;//预约率
	private String connectReserveRate;//接通预约率
	private int arrival;	//到店数
	private String arrivalRate;//到店率
	private String connectArrivalRate;//接通到店率
	private int deal;	//成交数
	private String dealRate;//成交率
	private String connectDealRate;//接通成交率
	
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getTagname2() {
		return tagname2;
	}
	public void setTagname2(String tagname2) {
		this.tagname2 = tagname2;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getConnect() {
		return connect;
	}
	public void setConnect(int connect) {
		this.connect = connect;
	}
	public String getConnectRate() {
		return connectRate;
	}
	public void setConnectRate(String connectRate) {
		this.connectRate = connectRate;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public String getReserveRate() {
		return reserveRate;
	}
	public void setReserveRate(String reserveRate) {
		this.reserveRate = reserveRate;
	}
	public String getConnectReserveRate() {
		return connectReserveRate;
	}
	public void setConnectReserveRate(String connectReserveRate) {
		this.connectReserveRate = connectReserveRate;
	}
	public int getArrival() {
		return arrival;
	}
	public void setArrival(int arrival) {
		this.arrival = arrival;
	}
	public String getArrivalRate() {
		return arrivalRate;
	}
	public void setArrivalRate(String arrivalRate) {
		this.arrivalRate = arrivalRate;
	}
	public String getConnectArrivalRate() {
		return connectArrivalRate;
	}
	public void setConnectArrivalRate(String connectArrivalRate) {
		this.connectArrivalRate = connectArrivalRate;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	public String getDealRate() {
		return dealRate;
	}
	public void setDealRate(String dealRate) {
		this.dealRate = dealRate;
	}
	public String getConnectDealRate() {
		return connectDealRate;
	}
	public void setConnectDealRate(String connectDealRate) {
		this.connectDealRate = connectDealRate;
	}
	
	
	
}
