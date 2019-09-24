package com.lss.applets.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;

/**
 * 后台用户session过滤
 * 
 * @author SWWH
 *
 */
public class LoginFilter implements Filter {

	private String excludedPage;
	private String[] excludedPages;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (request.getMethod().equals("OPTIONS")) {
			chain.doFilter(req, res);
			return;
		}
		boolean flag = false;
		String servletPath = request.getServletPath();
		for (String page : excludedPages) {
			if (servletPath.startsWith(page)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			chain.doFilter(request, response);
			return;
		}
		
		// 获取session
		HttpSession session = request.getSession();
		// 获取用户信息
		Object obj = session.getAttribute(SystemConstant.lssAdminSession);
		LoginAdmin admin = (LoginAdmin) obj;
		if (ObjectUtil.isEmpty(admin)) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.noLogin);
			result.setMsg(ResponseCode.noLoginMsg);
			// response.setStatus(401);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(result));
		} else {
			// 验证权限
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig filterConfig) {
		this.excludedPage = filterConfig.getInitParameter("releaseUrl");
		if (ObjectUtil.isNotEmpty(excludedPage)) {
			this.excludedPages = excludedPage.split(",");
		}
	}

	public void destroy() {

	}
}