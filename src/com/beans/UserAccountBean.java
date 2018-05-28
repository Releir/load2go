package com.beans;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class UserAccountBean {
	
	String email;
	String name;
	String reward;
	String balance;
	
	private String formatDecimal(double amount) {
		
		NumberFormat df = DecimalFormat.getInstance();
		df.setMaximumFractionDigits(0);
		return df.format(amount).toString();
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = formatDecimal(reward);
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance =  formatDecimal(balance);
	}
	
	

}
