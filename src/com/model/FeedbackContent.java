package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback_content")
public class FeedbackContent {
	
	@Id
	@Column(name = "feedback_id" , nullable = false)
	private Integer feedback_id;
	
	@Column(name = "feedback_comment" , nullable = false)
	private String feedback_comment;
	
	@Column(name = "feedback_date" , nullable = false)
	private String feedback_date;
	

	public Integer getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}

	public String getFeedback_comment() {
		return feedback_comment;
	}

	public void setFeedback_comment(String feedback_comment) {
		this.feedback_comment = feedback_comment;
	}

	public String getFeedback_date() {
		return feedback_date;
	}

	public void setFeedback_date(String feedback_date) {
		this.feedback_date = feedback_date;
	}
	
	
}
