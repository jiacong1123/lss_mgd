package com.lss.core.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public static String get(String url, Map<String, String> paramsMap)
			throws Exception {
		String responseText = null;
		try {
			CloseableHttpClient client = createSSLClientDefault();
			HttpEntity entity = null;
			CloseableHttpResponse response = null;
			StringBuilder sb = new StringBuilder();
			if (paramsMap != null) {
				for (Map.Entry param : paramsMap.entrySet()) {
					sb.append(new StringBuilder().append("&")
							.append((String) param.getKey()).append("=")
							.append((String) param.getValue()).toString());
				}
				url = new StringBuilder().append(url).append("?")
						.append(sb.toString().substring(1)).toString();
			}

			HttpGet method = new HttpGet(url);

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000).setSocketTimeout(5000).build();
			method.setConfig(requestConfig);
			response = client.execute(method);
			entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
			if (response != null)
				response.close();
			if (response.getStatusLine().getStatusCode() != 200)
				throw new Exception();
		} catch (Exception e) {
			throw new Exception();
		}
		return responseText;
	}

	public static String post(String url, Map<String, String> paramsMap)
			throws Exception {
		String responseText = "";
		try {
			CloseableHttpClient client = createSSLClientDefault();
			HttpPost method = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000).setSocketTimeout(5000).build();
			method.setConfig(requestConfig);
			HttpEntity entity = null;
			CloseableHttpResponse response = null;
			if (paramsMap != null) {
				List paramList = new ArrayList();
				for (Map.Entry param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(
							(String) param.getKey(), (String) param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			}
			response = client.execute(method);
			entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
			if (response != null) {
				response.close();
			}
		} catch (IOException e) {
		}
		return responseText;
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
			if (null != token) {
				method.setHeader("Authorization", "Bearer " + token);
			}

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
					logger.error("post请求提交失败:" + url);
					logger.error("");
				} finally {
					result.close();
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
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