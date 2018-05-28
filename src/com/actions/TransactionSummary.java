package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.beans.TransactionBean;
import com.db.HibernateConnection;
import com.db.SubQueries;
import com.helper.TransactionAssembler;
import com.opensymphony.xwork2.ActionSupport;

public class TransactionSummary extends ActionSupport implements SessionAware{
	
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap; 
	private double amount;
	private String mobileNumber;
	private String prefix;
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

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
		
		SessionFactory conn = HibernateConnection.getSessionFactory();
		System.out.println("Current user is " + sessionMap.get("currentUser"));
		if(amount>SubQueries.getWalletBalance(conn, sessionMap.get("currentUser").toString())){
			addActionError("Insufficient Balance. Please Reload or pick another amount.");
			return ERROR;
		}
		else {
			TransactionBean transaction = TransactionAssembler.getInstance(String.valueOf(getAmount()), getPrefix(), getMobileNumber());
			String number = getPrefix() + "" + getMobileNumber();
			System.out.println(getPrefix());
			System.out.println(number);
			sessionMap.put("number", number);
			sessionMap.put("transaction", transaction);
			
		}
		
		
		return SUCCESS;
	
	}

}
