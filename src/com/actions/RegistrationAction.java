package com.actions;

import java.util.Map;

import nl.captcha.Captcha;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.Queries;
import com.messages.ErrorMessages;
import com.model.User;
import com.beans.RegistrationBean;
import com.opensymphony.xwork2.ActionSupport;
import com.helper.UserAssembler;

public class RegistrationAction extends ActionSupport implements SessionAware, ErrorMessages{
	
	private static final long serialVersionUID = 5851572619394839311L;
	private SessionMap<String,Object> sessionMap;  
	
	public User reg;
	
	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}

	public User getReg() {
		return reg;
	}

	public void setReg(User reg) {
		this.reg = reg;
	}

	public String execute() throws Exception {
		
		String captcha_ans = reg.getAnswer();
		
		Captcha captcha = (Captcha) sessionMap.get(Captcha.NAME);
		
		if (captcha.isCorrect(captcha_ans)) {
			
			if (validation(reg.getEmail(), reg.getPassword(), reg.getConfPass(), reg.getLastName(), reg.getFirstName(), reg.getSecans(), reg.getSecquest()).equals(SUCCESS)) {
				
				sessionMap.put("regEmail", reg.getEmail());
				return SUCCESS;
			}
			else {
				
				return ERROR;
			}
		} else {
			addActionError(CAPTCHA_ERROR);
			return ERROR;
		}
	}
	

	
	public String validation(String user_email, String user_password, String conf_pass, String last_name, String first_name, String sec_answer,
			String sec_quest) throws Exception {
		
		if (user_email == null || user_email.equals("")) {
			
			 addActionError("Email is required");
			 return ERROR;
		}
		
		if (user_password == null || user_password.equals("")) {
			
			addActionError("Password is required");
			 return ERROR;
		}
		
		if (last_name == null || last_name.equals("")) {
			
			addActionError("Last Name is required");
			 return ERROR;
		}
		
		if (first_name == null || first_name.equals("")) {
			
			addActionError("First Name is required");
			 return ERROR;
		}
		
		if (sec_answer == null || sec_answer.equals("")) {
			
			addActionError("Secret Answer is required");
			 return ERROR;
		}
		
		if (conf_pass == null || conf_pass.equals("")) {
			
			addActionError("Password confirmation is required");
			 return ERROR;
		}
		
		if (conf_pass.length()<8 || user_password.length()<8) {
			
			addActionError("Password should be 8 characters or above");
			 return ERROR;
		}
		
		if(!conf_pass.equals(user_password)){
			addActionError("Password must be the same");
			 return ERROR;
		}
		
		if (user_email != null && user_password != null && last_name != null && first_name != null &&
				sec_answer != null && sec_quest != null) {
			
			RegistrationBean user = UserAssembler.getInstance(user_email, first_name, last_name, 
					sec_quest, sec_answer, user_password);
			
			String message = Queries.processRegistration(user);
			
			if (!message.equals(SUCCESS)) {
				
				addActionError(user_email + " " + message);
				return ERROR;
			}
			
		}
		
		return SUCCESS;
		
	}

}
