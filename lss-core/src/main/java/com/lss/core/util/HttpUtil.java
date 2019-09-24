package com.lss.core.util;

import java.io.IOException;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

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
}