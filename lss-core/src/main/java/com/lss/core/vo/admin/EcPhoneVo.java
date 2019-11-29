package com.lss.core.vo.admin;

public class EcPhoneVo {

	private String userId;//EC系统用户id
	
	private String callPhone;//被叫号码
	
	private String ecAccount;//EC帐号
	
	private String extnumber;
	
	

	public String getExtnumber() {
		return extnumber;
	}

	public void setExtnumber(String extnumber) {
		this.extnumber = extnumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getEcAccount() {
		return ecAccount;
	}

	public void setEcAccount(String ecAccount) {
		this.ecAccount = ecAccount;
	}
	

	
	
	
}
