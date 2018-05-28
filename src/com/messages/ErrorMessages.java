package com.messages;

public interface ErrorMessages {
	
	String NAMING_ERROR = "Error in naming.";
	String SQL_ERROR = "Something went wrong in the database.";
	String DUPLICATE_EMAIL = "is already registered.";
	String CAPTCHA_ERROR = "Wrong captcha";
	String FAILED_REGISTRATION = "FAILED REGISTRATION";
	String WRONG_CREDENTIALS = "Please check your password or username.";
	String NOT_LOGIN = "You are not logged in. Login to view this content.";
	String ACTIVATED = "Your account is already activated. Please login.";
	String NOT_ACTIVATED = "Your account is not activated. "
			+ "We have sent you an email regarding your account activation.";
	String SESSION_EXPIRED = "Your session has expired.";
	String NOT_REGISTERED = "You are not registered in the system.";
	String CC_INVALID = "Invalid Credit Card Number.";
	String NO_EMAIL_REGISTERED = "There is no user registered by that email in our system";
	String INVALID_CODE = "The code you have entered is invalid";
	String PASS_SHOULD_NOT_BE_THE_SAME = "New password should not be the same";

}
