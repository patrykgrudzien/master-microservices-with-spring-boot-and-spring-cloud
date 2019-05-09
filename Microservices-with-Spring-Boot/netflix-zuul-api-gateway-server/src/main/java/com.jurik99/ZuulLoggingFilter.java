package com.jurik99;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Component
public class ZuulLoggingFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {}, request uri -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
//      (indicate when filter should happen)
//		return "pre";  -> apply before request is executed
//		return "post"; -> apply after request is executed
//		return "error"; -> filter only error request
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
