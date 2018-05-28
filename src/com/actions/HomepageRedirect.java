package com.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.Queries;
import com.beans.TransactionHistoryBean;
import com.opensymphony.xwork2.ActionSupport;

public class HomepageRedirect extends ActionSupport implements SessionAware{
	
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap; 
	

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute() {
		String email = sessionMap.get("currentUser").toString();
		System.out.println("This is the email from session map: " + email);
		ArrayList<TransactionHistoryBean> history = null;
		history = Queries.getTransactionHistory(email);
		
		sessionMap.put("transactionHistory", history);
		
		
		
		return SUCCESS;
	
	}

}
