package com.helper;

import com.beans.LoginBean;

public class LoginAssembler {
	
	public static LoginBean getInstance(String email, String password) {
		
		LoginBean user = new LoginBean();
		user.setUsername(email);
		user.setPassword(password);
		
		return user;
		
	}
	
	public static LoginBean forgotPass(String email){
		LoginBean user = new LoginBean();
		user.setUsername(email);
		return user;
	}

}
