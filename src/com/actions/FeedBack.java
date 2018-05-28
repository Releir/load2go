package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.beans.FeedbackBean;
import com.db.Queries;
import com.helper.FeedbackAssembler;
import com.opensymphony.xwork2.ActionSupport;

public class FeedBack extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private SessionMap<String, Object> sessionMap;
	private String feedback;

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute(){
		
		FeedbackBean feedback = FeedbackAssembler.getInstance(getFeedback());
		Queries.processFeedback(feedback, sessionMap.get("currentUser").toString());
		
		return SUCCESS;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
