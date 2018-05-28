package com.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.Queries;
import com.opensymphony.xwork2.ActionSupport;

public class Prefixes extends ActionSupport implements SessionAware{
	
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap; 
	private String provider;
	

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute() {
		
		ArrayList<String> prefixes = 
				Queries.getNumberPrefixes(getProvider());
		
		sessionMap.put("prefixes", prefixes);
		sessionMap.put("provider", getProvider());
		
		
		return SUCCESS;
	
	}

}
