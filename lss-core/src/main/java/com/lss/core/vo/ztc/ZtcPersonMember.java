/**
 * 
 */
package com.lss.core.vo.ztc;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：导购客户好友信息
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月13日
 */
public class ZtcPersonMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 客户编号 .
	 */
	private String memberNo;

	/**
	 * 客户名称 .
	 */
	private String memberName;

	/**
	 * 导购编号 .
	 */
	private String memberNoGm;

	/**
	 * 导购姓名 .
	 */
	private String memberNameGm;

	/** * 商户编号 . */
	private String merchantNo;

	/** * 商户名称 . */
	private String merchantName;

	/** * 终端微信 */
	private String shopWx;

	/** 客户微信号 */
	private String noWx;

	/** 客户微信号别名 */
	private String noWxAlias;

	/** 客户昵称_微信 */
	private String nickNameWx;

	/** 头像地址 */
	private String headAddress;

	/** 手机号 */
	private String mobile;
	/** 终端头像*/
	private String terminalHeadurl;

	/** 查询的手机号 */
	private List<String> mobiles;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getNoWxAlias() {
		return noWxAlias;
	}

	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
	}

	public String getNickNameWx() {
		return nickNameWx;
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}

	public String getTerminalHeadurl() {
		return terminalHeadurl;
	}

	public void setTerminalHeadurl(String terminalHeadurl) {
		this.terminalHeadurl = terminalHeadurl;
	}

	@Override
	public String toString() {
		return "PersonMemberDto [code=" + code + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm=" + memberNameGm + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", shopWx=" + shopWx + ", noWx=" + noWx + ", noWxAlias="
				+ noWxAlias + ", nickNameWx=" + nickNameWx + ", headAddress=" + headAddress + ", mobile=" + mobile
				+ ", mobiles=" + mobiles + "]";
	}

}
