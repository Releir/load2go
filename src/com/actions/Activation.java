package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.Queries;
import com.opensymphony.xwork2.ActionSupport;

public class Activation extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private SessionMap<String, Object> sessionMap;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String execute() {
	
		Queries.activateAccount(getEmail());
		
		if(sessionMap.get("regEmail")!=null){
			sessionMap.remove("regEmail");
		}
		return SUCCESS;
	}

}
