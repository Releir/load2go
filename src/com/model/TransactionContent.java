package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_content")
public class TransactionContent {

	@Id
	@Column(name="transaction_id" ,nullable = false)
	private int transaction_id;
	
	@Column(name="transaction_description" ,nullable = false)
	private String transaction_description;
	
	@Column(name="transaction_amount" ,nullable = false)
	private double transaction_amount;
	
	@Column(name="transaction_reward_point" ,nullable = false)
	private double transaction_reward_point;
	
	@Column(name="transaction_date" ,nullable = false)
	private String transaction_date;
	
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_description() {
		return transaction_description;
	}
	public void setTransaction_description(String transaction_description) {
		this.transaction_description = transaction_description;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public double getTransaction_reward_point() {
		return transaction_reward_point;
	}
	public void setTransaction_reward_point(double transaction_reward_point) {
		this.transaction_reward_point = transaction_reward_point;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String string) {
		this.transaction_date = string;
	}
}
