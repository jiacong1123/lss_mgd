package com.lss.core.vo.ztc;

import java.io.Serializable;

/**
 * 类说明：客户直通车导购表
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 * 
 *         CreateDate: 2017年7月12日
 */
public class ZtcImGuidMember implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5433655528265823736L;

	/**
	 * 访问令牌
	 */
	private String token;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 导购编号 .
	 */
	private String memberNoGuid;

	/**
	 * 导购姓名 .
	 */
	private String memberNameGuid;

	/**
	 * 商户编号 .
	 */
	private String memberNoMerchant;

	/**
	 * 商户名称 .
	 */
	private String memberNameMerchant;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
	}

	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid;
	}

	public String getMemberNoMerchant() {
		return memberNoMerchant;
	}

	public void setMemberNoMerchant(String memberNoMerchant) {
		this.memberNoMerchant = memberNoMerchant;
	}

	public String getMemberNameMerchant() {
		return memberNameMerchant;
	}

	public void setMemberNameMerchant(String memberNameMerchant) {
		this.memberNameMerchant = memberNameMerchant;
	}

	@Override
	public String toString() {
		return "ZtcImGuidMember [token=" + token + ", code=" + code + ", memberNoGuid=" + memberNoGuid
				+ ", memberNameGuid=" + memberNameGuid + ", memberNoMerchant=" + memberNoMerchant
				+ ", memberNameMerchant=" + memberNameMerchant + "]";
	}

}