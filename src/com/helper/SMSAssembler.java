package com.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.struts2.dispatcher.SessionMap;

import com.messages.QueryMessages;
import com.beans.SMSBean;

public class SMSAssembler implements QueryMessages{
	
	public static SMSBean getInstance(SessionMap<String, Object> session, String amount, int transaction_id) {
		
		SMSBean sms = new SMSBean();
		
		try{
			String propFile = "D:/THESIS/eLoad Files/sqlConnection.property";
			FileInputStream fils = new FileInputStream(propFile);
			Properties property = new Properties();
			property.load(fils);
			
			
			String messageID = ExternalProcesses.generateMessageId();
			
			String message = "Regular Load: " + amount + 
					" has been loaded to " + session.get("number").toString() + 
					" from LOAD2GO. Reference ID: " + transaction_id;
			System.out.println(message.length());
			sms.setMesssageType("SEND");
			sms.setClientid(property.getProperty("CLIENT_ID"));//setClientID here
			sms.setMessageId(messageID);
			sms.setMessage(message);
			System.out.println(session.get("number").toString());
			sms.setMobileNumber(session.get("number").toString());
			System.out.println(sms.getMobileNumber());
			sms.setSecretkey(property.getProperty("SECRET_KEY"));
			sms.setShortCode(property.getProperty("SHORT_CODE"));
			System.out.println(messageID.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sms;
		
	}

}
