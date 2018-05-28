package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table  (name = "security")
public class Security {

	@Id
	@Column(name="security_id" ,nullable = false)
	private Integer security_id;
	
	@Column(name="security_question" ,nullable = false)
	private String security_question;
	
	@Column(name="security_answer" ,nullable = false)
	private String security_answer;
	
	@Column(name="reg_date" ,nullable = false)
	private String reg_date;
	
	public Security(Integer security_id, String security_question, String security_answer, String reg_date){
		this.security_id = security_id;
		this.security_question = security_question;
		this.security_answer =security_answer;
		this.reg_date = reg_date;
	}

	public Integer getSecurity_id() {
		return security_id;
	}

	public void setSecurity_id(Integer security_id) {
		this.security_id = security_id;
	}

	public String getSecurity_question() {
		return security_question;
	}

	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}

	public String getSecurity_answer() {
		return security_answer;
	}

	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}
