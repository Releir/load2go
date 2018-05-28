package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wallet")
public class Wallet {
	
	@Id
	@Column(name="wallet_id" ,nullable = false)
	private Integer wallet_id;
	
	@Column(name="wallet_balance" ,nullable = false)
	private double wallet_balance;
	
	public Wallet(Integer wallet_id, double wallet_balance){
		this.wallet_id = wallet_id;
		this.wallet_balance = wallet_balance;
	}
	
	public Integer getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(Integer wallet_id) {
		this.wallet_id = wallet_id;
	}
	public double getWallet_balance() {
		return wallet_balance;
	}
	public void setWallet_balance(double wallet_balance) {
		this.wallet_balance = wallet_balance;
	}
}
