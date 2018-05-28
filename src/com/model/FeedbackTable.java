package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="feedback")
public class FeedbackTable implements Serializable {

	@Id
	@Column(name="user_id" ,nullable = false)
	private String user_id;
	
	@Id
	@Column(name="feedback_id" ,nullable = false)
	private Integer feedback_id;

	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}
}
