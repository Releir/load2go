package com.actions;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.beans.TransactionBean;
import com.db.HibernateConnection;
import com.db.SubQueries;
import com.opensymphony.xwork2.ActionSupport;

public class Rewards extends ActionSupport implements SessionAware{
	
	
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
		if(amount>SubQueries.getRewardPoints(conn, sessionMap.get("currentUser").toString())){
			addActionError("Insufficient Balance. Please purchase more load to gain reward points.");
			return SUCCESS;
		}
		else {
			System.out.println("THE AMOUNT OF SHITTTTT " + getAmount());
			TransactionBean transaction = new TransactionBean();
			transaction.setAmount(getAmount());
			transaction.setReward(0);
			transaction.setDate(new Date());
			transaction.setDescription("REDEEMED POINTS");
			sessionMap.put("rewards_transaction", transaction);
			
		}
		
		
		return SUCCESS;
	
	}

}
