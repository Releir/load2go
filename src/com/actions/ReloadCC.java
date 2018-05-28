package com.actions;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.beans.TransactionBean;
import com.opensymphony.xwork2.ActionSupport;

public class ReloadCC extends ActionSupport implements SessionAware{
	
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap; 
	private double amount;
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute() {
			
			TransactionBean transaction = new TransactionBean();
			transaction.setAmount(getAmount());
			transaction.setReward(0);
			transaction.setDate(new Date());
			transaction.setDescription("WALLET LOAD");
			sessionMap.put("wallet_transaction", transaction);
			
		
		return SUCCESS;
	
	}

}
