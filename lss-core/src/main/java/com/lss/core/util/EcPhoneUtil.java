package com.lss.core.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lss.core.vo.admin.EcTokenVo;

/**
 * EC通话请求工具类
 * 
 * @author Administrator
 *
 */
public class EcPhoneUtil {

	private static final Logger log = LoggerFactory.getLogger(EcPhoneUtil.class);

	// EC电话常量
	private static final String GET_TOKEN_URL = "https://open.workec.com/auth/accesstoken";
	private static String AppID = "547800367602073600";
	private static String CorpID = "4943442";
	private static String AppSecret = "5LMq4CCtuSfo6J9M3Uu";
	private static int Expiration_Date = 7200;
	
	// 获取ec接口token
	public static String getToken(String key) {
		String value = RedisUtil.getString(key);
		if (StringUtils.isNotEmpty(value)) {
			return value;
		} else {
			JSONObject jsonResult = null;
			String accessToken = "";
			try {
				CloseableHttpClient client = getHttpClient();
				HttpPost method = new HttpPost(GET_TOKEN_URL);
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("appId", AppID);
				jsonParam.put("appSecret", AppSecret);
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
				CloseableHttpResponse result = client.execute(method);
				if (result.getStatusLine().getStatusCode() == 200) {
					String str = "";
					try {
						/** 读取服务器返回过来的json字符串数据 **/
						str = EntityUtils.toString(result.getEntity());
						/** 把json字符串转换成json对象 **/
						jsonResult = JSONObject.parseObject(str);
						// 把token存入redis
						EcTokenVo ecTokenVo = jsonResult.getObject("data", EcTokenVo.class);
						accessToken = ecTokenVo.getAccessToken();
						RedisUtil.setString(key, accessToken, Expiration_Date);
					} catch (Exception e) {
						log.error("post请求提交失败:" + GET_TOKEN_URL);
						log.error("");
					} finally {
						result.close();
					}
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return accessToken;
		}

	}

	/**
	 * post请求
	 *
	 * @param url            url地址
	 * @param jsonParam      参数
	 * @param noNeedResponse 不需要返回结果
	 * @param token          header里面Authorization的值
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam, String token) {
		// post请求返回结果
		JSONObject jsonResult = null;
		CloseableHttpClient client = getHttpClient();
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}

			method.setHeader("Authorization", token);
			method.setHeader("CORP-ID", CorpID);

			CloseableHttpResponse result = client.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					log.error("post请求提交失败:" + url);
					log.error("");
				} finally {
					result.close();
				}
			}
		} catch (IOException e) {
			log.error("post请求提交失败:" + url, e);
		} finally {
			try {
				closeHttpClient(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonResult;
	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
}
