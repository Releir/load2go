package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="transaction")
public class TransactionTable implements Serializable{
	
	@Id
//	@ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", nullable = false)
	@Column(name="user_id" ,nullable = false)
	private String user_id;
	
	@Id
//	@ManyToOne(targetEntity = TransactionContent.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "transaction_id", nullable = false)
	@Column(name="transaction_id" ,nullable = false)
	private Integer transaction_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

}
