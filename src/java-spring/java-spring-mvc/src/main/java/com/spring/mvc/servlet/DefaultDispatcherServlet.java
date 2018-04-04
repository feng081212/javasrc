package com.spring.mvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class DefaultDispatcherServlet extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long curTime = System.currentTimeMillis() ;
		super.doDispatch(request, response);
		String url = request.getRequestURI() ;
		if(url.contains(".")){
			
		} else {
			url = request.getRequestURI() + " ===> " + request.getParameterMap() ;
			System.out.println(url + " 请求耗时" + (System.currentTimeMillis()-curTime) + "ms");
		}
	}
}
