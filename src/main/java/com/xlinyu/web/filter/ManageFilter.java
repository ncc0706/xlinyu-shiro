package com.xlinyu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.xlinyu.web.constant.SystemConstant;
import com.xlinyu.web.domain.CurrentUser;

public class ManageFilter implements Filter{

	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute(SystemConstant.CURRENT_USER);
		if(currentUser == null){
			logger.info("currentUser: " + currentUser);
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
			response.sendRedirect(basePath + "/admin/login");
		}else{
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
		
	}
	
}
