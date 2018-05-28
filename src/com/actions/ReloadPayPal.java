package com.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.beans.TransactionBean;
import com.opensymphony.xwork2.ActionSupport;

public class ReloadPayPal extends ActionSupport implements SessionAware{
	
	
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
			
		String propFile = "D:/THESIS/eLoad Files/paypal.property";
		FileInputStream fils;
		try {
			fils = new FileInputStream(propFile);
		
		Properties property = new Properties();
		property.load(fils);
		
		sessionMap.put("CANCEL_PAYPAL", property.get("CANCEL_PAYPAL"));
		sessionMap.put("RETURN_PAYPAL", property.get("RETURN_PAYPAL"));
		
			TransactionBean transaction = new TransactionBean();
			transaction.setAmount(getAmount());
			transaction.setReward(0);
			transaction.setDate(new Date());
			transaction.setDescription("WALLET LOAD");
			sessionMap.put("wallet_transaction", transaction);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	
	}

}
