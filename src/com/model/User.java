package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity 
@Table (name = "user")
public class User {

	@Id @Column (name="user_id" ,nullable = false)
	private String email;
	
	@Column (name="user_first_name" ,nullable = false)
	private String firstName;
	@Column (name="user_last_name" ,nullable = false)
	private String lastName;
	@Column (name="user_password" ,nullable = false)
	private String password;	
	
	
	@ManyToOne(targetEntity = Security.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "security_id", nullable = false)
	private Security security_id;
	
	@ManyToOne(targetEntity = Wallet.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet_id;
	
	@ManyToOne(targetEntity = Reward.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "rewards_id", nullable = false)
	private Reward reward_id;
	
	@Transient
	private String confPass;
	@Transient
	private String secquest;
	@Transient
	private String secans;
	@Transient
	private String answer;	
	
	////////////////////////////////
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	////////////////////////////////
	
	public Security getSecurity_id() {
		return security_id;
	}
	public void setSecurity_id(Security security_id) {
		this.security_id = security_id;
	}
	public Wallet getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(Wallet wallet_id) {
		this.wallet_id = wallet_id;
	}
	public Reward getReward_id() {
		return reward_id;
	}
	public void setReward_id(Reward reward_id) {
		this.reward_id = reward_id;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecquest() {
		return secquest;
	}
	public void setSecquest(String secquest) {
		this.secquest = secquest;
	}
	public String getSecans() {
		return secans;
	}
	public void setSecans(String secans) {
		this.secans = secans;
	}
	
}
