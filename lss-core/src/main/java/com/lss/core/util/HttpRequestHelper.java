package com.lss.core.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.lss.core.constant.LlyhConstant;
import com.lss.core.constant.ResponseCode;
import com.lss.core.exception.LssException;
import com.lss.core.vo.phone.LlyhBindResultVo;
import com.lss.core.vo.phone.LlyhBindVo;

public class HttpRequestHelper {

	private static Logger log = Logger.getLogger(HttpRequestHelper.class);

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url
					+ (ObjectUtil.isEmpty(param) ? "" : "?" + param);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendGetGBK(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url
					+ (ObjectUtil.isEmpty(param) ? "" : "?" + param);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "gbk"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String doGetMethod(String url, Map<String, String> keyValueMap) {
		// Protocol myhttps = new Protocol("https", new
		// MySecureProtocolSocketFactory (), 443);
		// Protocol.registerProtocol("https", myhttps);
		HttpClient client = new HttpClient();

		StringBuffer sb = new StringBuffer(url);
		PostMethod postMethod = null;
		try {
			// 设置请求参数
			if (keyValueMap != null) {
				Iterator it = keyValueMap.entrySet().iterator();
				if (keyValueMap.size() > 0) {
					sb.append("?");
					while (it.hasNext()) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it
								.next();
						sb.append(entry.getKey() + "=" + entry.getValue() + "&");
					}
					sb.deleteCharAt(sb.length() - 1);
				}

			}
			postMethod = new PostMethod(sb.toString());
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			postMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			// todo:设置超时时间
			postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
					200000);
			int statusCode = client.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return "调用外部链接异常！";
			}
			String responseBody = postMethod.getResponseBodyAsString();
			return responseBody;
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
	}

	public static String doGetMethod2(String url,
			Map<String, String> keyValueMap, Log logger)
			throws MalformedURLException {
		// Protocol myhttps = new Protocol("https", new
		// MySecureProtocolSocketFactory (), 443);
		// Protocol.registerProtocol("https", myhttps);
		HttpClient client = new HttpClient();
		/*
		 * URL strUrl = new URL(url); String host = strUrl.getHost(); int port =
		 * strUrl.getPort();
		 * 
		 * Protocol myhttps = new Protocol("http", new
		 * MySecureProtocolSocketFactory (), port);
		 * Protocol.registerProtocol("http", myhttps);
		 * client.getHostConfiguration().setHost(host, port, myhttps);
		 */
		StringBuffer sb = new StringBuffer(url);
		PostMethod postMethod = null;
		try {
			// 设置请求参数
			if (keyValueMap != null) {
				Iterator it = keyValueMap.entrySet().iterator();
				if (keyValueMap.size() > 0) {
					sb.append("?");
					while (it.hasNext()) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it
								.next();
						sb.append(entry.getKey() + "=" + entry.getValue() + "&");
					}
					sb.deleteCharAt(sb.length() - 1);
				}

			}
			// logger.error("doGetMethod");
			// logger.error("sb:"+sb.toString());
			postMethod = new PostMethod(sb.toString());
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			postMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			// todo:设置超时时间
			postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
					200000);

			// logger.error("postMethod setedd");

			int statusCode = client.executeMethod(postMethod);

			// logger.error("statusCode:"+statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				return "调用外部链接异常！";
			}
			String responseBody = postMethod.getResponseBodyAsString();
			// logger.error("responseBody:"+responseBody);
			return responseBody;
		} catch (Exception e) {
			// logger.error("e:"+e.getMessage());
			return e.getMessage();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
	}

	/**
	 * 推送普通消息
	 * 
	 * @author 游海
	 * @param messageContext
	 *            推送的消息内容
	 * @param Node
	 *            接收人用户名
	 * @return 推送的结果，可以不处理
	 */
	public static String PostOrdinaryPush(String url,
			Map<String, String> keyValueMap) {
		// String url = "http://"+ JiveGlobals.getXMLProperty("push.host") +
		// "/push-service/api/v1/message/toalias";
		try {
			/*
			 * String message = URLEncoder.encode("{\"title\":\"" + Name +
			 * "\",\"senderName\":\"" + Name + "\",\"senderId\":\"" + SendNode +
			 * "\",\"content\":\"" + messageContext+ "\",\"type\":10}","utf-8");
			 * String params = "alias=" + Node+ "&message=" + message +
			 * "&channel=3";
			 */
			// logger.error("url:"+url);
			StringBuffer sb = new StringBuffer();
			// 设置请求参数
			if (keyValueMap != null) {
				Iterator it = keyValueMap.entrySet().iterator();
				if (keyValueMap.size() > 0) {
					// sb.append("?");
					while (it.hasNext()) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it
								.next();
						sb.append(entry.getKey() + "=" + entry.getValue() + "&");
					}
					sb.deleteCharAt(sb.length() - 1);
				}

			}
			// logger.error("sb:"+sb.toString());
			// Post请求的url，与get不同的是不需要带参数
			URL postUrl = new URL(url);
			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			// Output to the connection. Default is
			// false, set to true because post
			// method must write something to the
			// connection
			// 设置是否向connection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true
			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("POST");
			// Post cannot use caches
			// Post 请求不能使用缓存
			connection.setUseCaches(false);
			// This method takes effects to
			// every instances of this class.
			// URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。
			// connection.setFollowRedirects(true);

			// This methods only
			// takes effacts to this
			// instance.
			// URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数
			connection.setInstanceFollowRedirects(true);
			// Set the content type to urlencoded,
			// because we will write
			// some URL-encoded content to the
			// connection. Settings above must be set before connect!
			// 配置本次连接的Content-type，配置为application/x- www-form-urlencoded的
			// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
			// 进行编码
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// connection.setRequestProperty("token",
			// JiveGlobals.getXMLProperty("push.token"));
			// 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行 connect。
			// logger.error(" PostOrdinaryPush 1");
			connection.connect();
			// logger.error(" PostOrdinaryPush 2");
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
			// DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面
			out.writeBytes(sb.toString());

			out.flush();
			out.close(); // flush and close
			// if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
			// //System.out.println("推送成功！");
			// }else{
			// //System.out.println("推送失败！");
			// }
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// logger.error(" PostOrdinaryPush 3");
			String line;
			String msg = "";
			/*
			 * System.out.println(" ============================= ");
			 * System.out.println(" Contents of post request ");
			 * System.out.println(" ============================= ");
			 */
			while ((line = reader.readLine()) != null) {
				msg += line;
			}

			// logger.error(" PostOrdinaryPush" +msg);
			/*
			 * System.out.println(" ============================= ");
			 * System.out.println(" Contents of post request ends ");
			 * System.out.println(" ============================= ");
			 */
			reader.close();
			connection.disconnect();
			return msg;
			// sendPostRequestByForm(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String post(String strURL, String params) {
		// System.out.println(strURL);
		// System.out.println(params);
		log.info(strURL);
		log.info(params);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			if (params != null)
				out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // utf-8编码
				// System.out.println(result);
				log.info(result);
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

	/**
	 * 发送post请求
	 * 
	 * @param urlStr
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String request(String urlStr, String data)
			throws UnsupportedEncodingException, IOException {
		// log.info("请求地址：" + urlStr);
		URL url = new URL(urlStr);
		URLConnection con = url.openConnection();
		con.setRequestProperty("Pragma:", "no-cache");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Content-Type", "text/xml");
		// 设置post
		con.setDoOutput(true);
		con.setDoInput(true);
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write(new String(data.getBytes("UTF-8")));
		out.flush();
		out.close();
		// 获取返回数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String line;
		String res = "";
		while ((line = reader.readLine()) != null) {
			res += line;
		}
		return res;
	}

	public static String sendPostAuth(String url, String param,
			Map<String, String> map) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			// conn.setRequestProperty("Authorization",
			// "Basic "+Constant.base64Key);
			// conn.setRequestProperty("Content-Type", "application/json");
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String temp = it.next();
				conn.setRequestProperty(temp, map.get(temp));
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			// out = new PrintWriter(conn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(
					conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			// new InputStreamReader(conn.getInputStream());
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送json格式数据
	 * 
	 * @return
	 */
	public static String sendJsonPost(String url, String obj) {
		String result = null;
		try {
			// 创建连接
			URL urlnet = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlnet
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());

			// out.writeBytes(obj.toString());//这个中文会乱码
			out.write(obj.getBytes("UTF-8"));// 这样可以处理中文乱码问题
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			result = sb.toString();
		} catch (MalformedURLException e) {
			log.error("请求失败，",e);
			throw new LssException(ResponseCode.requstFailure, ResponseCode.requstFailureMsg, e);
//			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("请求失败，",e);
			throw new LssException(ResponseCode.requstFailure, ResponseCode.requstFailureMsg, e);
//			e.printStackTrace();
		} catch (IOException e) {
			log.error("请求失败，",e);
			throw new LssException(ResponseCode.requstFailure, ResponseCode.requstFailureMsg, e);
//			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		LlyhBindVo bindVo = new LlyhBindVo();
		bindVo.setToken(LlyhConstant.ACCESS_TOKEN);
		bindVo.setType("PER_PERSON");
		bindVo.setCallerId("dxy");
		String[] cStrings= {"15898589585","15898589586"};
		bindVo.setCallerNos(cStrings);
		bindVo.setCalledId("15800001111");
		bindVo.setCalledNo("15800001111");
		bindVo.setPayload("2222");
		bindVo.setAgentContact("wx123456test");
		String reqJson = JSON.toJSONString(bindVo);
		System.out.println(reqJson);
//		String resultJson= HttpRequestHelper.sendJsonPost(LlyhConstant.BIND_URL, reqJson);
//		LlyhBindResultVo resultVo = JSON.parseObject(resultJson, LlyhBindResultVo.class);
//		
//		System.out.println(resultVo.getCode()+resultVo.getMessage());
	}
}
