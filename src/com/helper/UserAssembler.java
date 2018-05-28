package com.helper;

import com.beans.RegistrationBean;

public class UserAssembler {
	
	public static RegistrationBean getInstance(String email, String firstName, String lastName, String secquest,
			String secans, String password) {
		
		RegistrationBean user = new RegistrationBean();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSecretQuestion(secquest);
		user.setSecretAnswer(secans);
		user.setPassword(password);
		
		return user;
		
	}

}
