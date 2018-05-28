package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.beans.ForgotPassBean;
import com.db.Queries;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class ResettingPassAction extends ActionSupport {
	
	private Queries query = new Queries();
	private ForgotPassBean user;

	public ForgotPassBean getUser() {
		return user;
	}

	public void setUser(ForgotPassBean user) {
		this.user = user;
	}


	public String execute() throws Exception {
		if (validation(user.getCode(),user.getPassword(),user.getConfPass()).equals(SUCCESS)) {
			return SUCCESS;
		}
		else {
			
			return ERROR;
		}
	}
	
	public String validation(String code, String pass, String confPass) throws Exception {
		
		if (code == null || code.equals("")) {
			 addActionError("Code is required");
			 return ERROR;
		}
		
		if (pass == null || pass.equals("")) {
			
			 addActionError("Password is required");
			 return ERROR;
		}
		
		if (confPass == null || confPass.equals("")) {
			
			 addActionError("Confirmation password is required");
			 return ERROR;
		}
		
		if (!pass.equals(confPass)) {
			
			 addActionError("Passwords does not match");
			 return ERROR;
		}
		
		if (code != null && pass != null && confPass != null) {
		
			String message = query.changePassword(code, pass);
			if (!message.equals(SUCCESS)) {
				addActionError(message);
				return ERROR;
			}
			
		}
		
		return SUCCESS;
		
	}
}
