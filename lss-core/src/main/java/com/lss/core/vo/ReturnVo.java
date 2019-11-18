package com.lss.core.vo;

import java.io.Serializable;

import com.lss.core.constant.ResponseCode;

/**
 * 返回数据
 * 
 * @author SWWH
 *
 * @param <T>
 */
public class ReturnVo extends PageVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675951871968646789L;

	/**
	 * 返回码
	 */
	private Integer result;

	/**
	 * 返回数据
	 */
	private Object obj;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * sessionid
	 */
	private String ticket;

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public static ReturnVo success(Object obj) {
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		result.setObj(obj);
		return result;
	}
	
	public static ReturnVo failure(Integer result,String msg) {
		ReturnVo returnVo = new ReturnVo();
		returnVo.setResult(result);
		returnVo.setMsg(msg);
		return returnVo;
	}
}
