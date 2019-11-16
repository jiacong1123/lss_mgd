package com.lss.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.Template;

public class SendMessageTest extends SpringTestCase{

	@Test
	public void templateList() {
		//初始化clnt,使用单例方式
		YunpianClient clnt = new YunpianClient("190e7d32a532d335c83fb4dd3db7eeab").init();
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.APIKEY, "190e7d32a532d335c83fb4dd3db7eeab");
		Result<List<Template>> r = clnt.tpl().get(param);
		System.out.println("返回结果:"+r);
	}
}
