package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private SessionMap<String, Object> sessionMap;
	
	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String execute() {
		
		sessionMap.remove("currentUser");
		sessionMap.remove("user_details");
		sessionMap.invalidate();
		
		return SUCCESS;
	}

}
