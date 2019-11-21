package com.lss.admin.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.RolePopedom;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.MD5;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.EcGetUserVo;
import com.lss.core.vo.admin.EcTokenVo;
import com.lss.core.vo.admin.params.AdminParams;
import com.lss.core.vo.admin.params.PersonParams;
import com.lss.core.vo.admin.params.PopedomParams;
import com.lss.core.vo.admin.params.RoleParams;
import com.mongodb.util.JSON;
import com.qiniu.util.Json;

/**
 * 管理员 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("admin")
public class AdminUserController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

	// EC电话常量
	private static final String GET_TOKEN_URL = "https://open.workec.com/auth/accesstoken";
	private static final String GET_USERID_URL = "https://open.workec.com/user/findUserInfoById";
	private static String AppID = "547800367602073600";
	private static String CorpID = "4943442";
	private static String AppSecret = "5LMq4CCtuSfo6J9M3Uu";
	private static int Expiration_Date = 7200;
	private static String GET_EC_TOKEN_KEY = "getECtoken";

	/**
	 * 添加/编辑管理员
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Admin admin) {
		return ServiceManager.adminService.save(admin);
	}

	/**
	 * 管理员列表
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody AdminParams params) {
		return ServiceManager.adminService.list(params);
	}

	/**
	 * 启用/禁用/删除
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Admin admin) {
		return ServiceManager.adminService.operate(admin);
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping("rolelist")
	public ReturnVo rolelist() {
		return ServiceManager.adminService.rolelist();
	}

	/**
	 * 编辑用户角色
	 * 
	 * @return
	 */
	@RequestMapping("editrole")
	public ReturnVo editrole(@RequestBody RoleParams params) {
		return ServiceManager.adminService.editrole(params);
	}

	/**
	 * 权限列表
	 * 
	 * @return
	 */
	@RequestMapping("popedomlist")
	public ReturnVo popedomlist() {
		return ServiceManager.adminService.popedomlist();
	}

	/**
	 * 编辑角色权限
	 * 
	 * @return
	 */
	@RequestMapping("editpopedom")
	public ReturnVo editpopedom(@RequestBody PopedomParams params) {
		return ServiceManager.adminService.editpopedom(params);
	}

	/**
	 * 角色权限
	 * 
	 * @return
	 */
	@RequestMapping("rolepopedom")
	public ReturnVo rolepopedom(@RequestBody RolePopedom params) {
		return ServiceManager.adminService.rolepopedom(params);
	}

	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@RequestMapping("menulist")
	public ReturnVo menulist() {
		return ServiceManager.adminService.menulist(this.loginAdmin);
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("changepassword")
	public ReturnVo changepassword(@RequestBody AdminParams params) {
		return ServiceManager.adminService.changepassword(params, this.loginAdmin);
	}

	/**
	 * 标签
	 * 
	 * @return
	 */
	@RequestMapping("tags")
	public ReturnVo tags(@RequestBody WorkTag tag) {
		return ServiceManager.workOrderService.tags(tag);
	}

	/**
	 * 人员(不带权限查所有人)
	 * 
	 * @return
	 */
	@RequestMapping("personnel")
	public ReturnVo personnel(@RequestBody PersonParams params) {
		return ServiceManager.adminService.personnel(params);
	}

	/**
	 * 人员列表(带权限)
	 * 
	 * @return
	 */
	@RequestMapping("personnelList")
	public ReturnVo personnelList() {
		PersonParams params = new PersonParams();
		return ServiceManager.adminService.personnelList(params, loginAdmin);
	}

	/**
	 * 员工绑定话机及小号
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.05.08
	 */
	@RequestMapping("bindPhone")
	public ReturnVo bindPhone(@RequestBody Admin params) {
		return ServiceManager.adminService.updateCallerNos(params);
	}

	/**
	 * 获取EC用户ID
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.05.08
	 */
	@RequestMapping("getEcUserId")
	public ReturnVo getEcUserId(@RequestBody Admin params) {
		ReturnVo returnVo = new ReturnVo();
		String value = RedisUtil.getString(params.getPhone());
		if(StringUtils.isNotEmpty(value)) {
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
		}else {
			try {
				String token = getToken(GET_EC_TOKEN_KEY);
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("account", params.getPhone());
				JSONObject post = httpPost(GET_USERID_URL,jsonParam,token);
				EcGetUserVo ecGetUserVo = post.getObject("data", EcGetUserVo.class);
				int f_user_id = ecGetUserVo.getF_user_id();
				params.setEcUserId(f_user_id+"");
				//存入redis
				RedisUtil.setString(params.getPhone(), params.getEcUserId());
				ServiceManager.adminService.updateByPhone(params);
				returnVo.setResult(ResponseCode.success);
				returnVo.setMsg(ResponseCode.successMsg);
			} catch (Exception e) {
				returnVo.setResult(ResponseCode.failure);
				returnVo.setMsg(ResponseCode.failureMsg);
			}
		}
		return returnVo;
	}

	// 获取ec接口token
	public String getToken(String key) {
		String value = RedisUtil.getString(key);
		if(StringUtils.isNotEmpty(value)) {
			return value;
		}else {
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
						//把token存入redis
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
	public static JSONObject httpPost(String url, JSONObject jsonParam,String token) {
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
