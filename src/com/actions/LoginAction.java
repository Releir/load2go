package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.Queries;
import com.beans.LoginBean;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.helper.LoginAssembler;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private User user;
	private SessionMap<String,Object> sessionMap;  
	private Queries query = new Queries();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public String execute() throws Exception {
		if (validation(user.getEmail(), user.getPassword()).equals(SUCCESS)) {
			return SUCCESS;
		}
		else {
			
			return ERROR;
		}
	}
	
	public String validation(String user_email, String user_password) throws Exception {
		
		if (user_email == null || user_email.equals("")) {
			
			 addActionError("Email is required");
			 return ERROR;
		}
		
		if (user_email == null || user_password.equals("")) {
			
			addActionError("Password is required");
			 return ERROR;
		}
		
		if (user_email != null && user_password != null) {
			LoginBean user = LoginAssembler.getInstance(user_email, user_password);
			
			String message = query.processLogin(user, sessionMap);
			if (!message.equals(SUCCESS)) {
				
				addActionError(message);
				return ERROR;
			}
			
		}
		
		return SUCCESS;
		
	}
}

