package com.beans;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionBean {
	
	private int transaction_id;
	private String description;
	private String amount;
	private String reward;
	private String date;
	
	private DecimalFormat def = new DecimalFormat("#");
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = def.format(amount);
	}
	public String getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = def.format(reward);
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = df.format(date);
	}

}
