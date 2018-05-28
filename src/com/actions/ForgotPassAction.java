package com.actions;

import com.db.Queries;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ForgotPassAction extends ActionSupport {
	
	private Queries query = new Queries();
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() throws Exception {
		System.out.println("Forget pass email entered is: " + user.getEmail());
		if (validation(user.getEmail()).equals(SUCCESS)) {
			return SUCCESS;
		}
		else {
			
			return ERROR;
		}
	}
	
	public String validation(String user_email) {
		
		if (user_email == null || user_email.equals("")) {
			 addActionError("Email is required");
			 return ERROR;
		}else if (user_email != null) {
			String message = query.forgotPassMail(user_email);
			if (!message.equals(SUCCESS)) {
				addActionError(message);
				return ERROR;
			}
		}
		
		return SUCCESS;
		
	}
}
