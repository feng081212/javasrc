package com.spring.mvc.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CustomSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		initEnumItems(httpSessionEvent.getSession()) ;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		
	}
	
	protected void initEnumItems(HttpSession httpSession){
		httpSession.setAttribute("path", httpSession.getServletContext().getContextPath())  ;
	}

}
