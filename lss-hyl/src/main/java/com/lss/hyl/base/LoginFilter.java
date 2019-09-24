package com.lss.hyl.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;

/**
 * 后台用户session过滤
 * 
 * @author SWWH
 *
 */
public class LoginFilter implements Filter {

	private static final Logger log = LoggerFactory
			.getLogger(LoginFilter.class);

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
		// 不拦截文件
		String servletPath = request.getServletPath();
		if (servletPath.endsWith(".txt") || servletPath.endsWith(".html")
				|| servletPath.endsWith(".ioc")
				|| servletPath.startsWith("/user/login")) {
			chain.doFilter(req, res);
			return;
		}

		// 验证设置不拦截
		boolean flag = false;
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
		// 验证是否登录
		Integer doctorid = (Integer) request.getSession().getAttribute(
				SystemConstant.hylDoctorSession);
		if (ObjectUtil.isEmpty(doctorid)) {
			ReturnVo result = new ReturnVo();
			result.setResult(ResponseCode.noLogin);
			result.setMsg(ResponseCode.noLoginMsg);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(result));
		} else {
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