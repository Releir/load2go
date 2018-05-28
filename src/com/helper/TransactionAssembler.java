package com.helper;

import java.util.Date;

import com.beans.TransactionBean;

public class TransactionAssembler {
	
	public static TransactionBean getInstance(String amount, String prefix, String mobileNumber) {
		
		TransactionBean user = new TransactionBean();
		
		String description = "Regular Load: " + amount + 
				" Mobile Number: " + prefix + mobileNumber;
		user.setAmount(Double.parseDouble(amount));
		user.setReward(getReward(Double.parseDouble(amount)));
		user.setDescription(description);
		user.setDate(new Date());
		System.out.println(user.getAmount());
		System.out.println(user.getDate());
		System.out.println(user.getReward());
		System.out.println(user.getDescription());
		
		return user;
		
	}
	
	private static double getReward(double amount) {
		
		double reward = 0;
		
		if (amount>=10 && amount<=30) reward = 1;
		else if (amount==50 || amount==60) reward = 2;
		else if (amount==100 || amount==150) reward = 3;
		else if (amount==200) reward = 5;
		else if (amount==300) reward = 6;
		else if (amount==500) reward = 7;
		else if (amount==1000) reward = 8;
		else if (amount==1500) reward = 9;
		else if (amount==2000) reward = 10;
		else reward = 0;
		
		return reward;
	}

}
