package com.lss.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lss.core.constant.SystemConstant;

public class CrossDomainFilter implements Filter {

	private static final Logger log = LoggerFactory
			.getLogger(CrossDomainFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		// 获取跨域域名
		String domain = request.getHeader("Origin");
		// 校验白名单
		if (SystemConstant.domainWhiteList.contains(domain)) {
			response.setHeader("Access-Control-Allow-Origin", domain);
		} else if (StringUtils.isNotEmpty(domain) && (domain.startsWith("http://192.168.")
				|| (domain.startsWith("http://localhost"))
				)) {// 内网开发或测试环境通过
			response.setHeader("Access-Control-Allow-Origin", domain);
		}
		response.setHeader("Access-Control-Allow-Methods",
				"PUT, POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,x-requested-with,ticket");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {

	}

	public void destroy() {

	}
}
