package com.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.messages.QueryMessages;
import com.beans.SMSBean;

public class ExternalProcesses implements QueryMessages{
	
	public static boolean isValid(String cc){
		
		char[] temp = cc.toCharArray();
		int[] digits = new int[temp.length];
		int sum = 0;
		
		if (!checkIfZero(cc)) {
		
			if (cc.length()>=12 || cc.length()<=19) {
				
				// convert cardNumber to integer array 
	
				for(int ctr = 0; ctr < temp.length; ctr++){
					digits[ctr] = temp[ctr] - '0';
				}
			
				int length = digits.length;
					
				for(int ctr2 = 0; ctr2 < length; ctr2++){
					
					// reverse digit
					int digit = digits[length - ctr2 - 1];
					
					if(ctr2 % 2 == 1){
							digit *= 2;
					}
						
					sum += digit > 9 ? digit - 9 : digit;
				}
					
				
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		return sum%10==0;
	}
	
	private static boolean checkIfZero(String number) {
		
		boolean isZero = true;
		
		for (char ctr:number.toCharArray()) {
			if (ctr != '0') {
				return false;
			}
		}
		return isZero;
		
	}

	
	public static void sendSMS(StringBuffer Response, SMSBean sms) {
		
		
		try{
			
			String propFile = "D:/THESIS/eLoad Files/sqlConnection.property";
			FileInputStream fils = new FileInputStream(propFile);
			Properties property = new Properties();
			property.load(fils);
			
			String Data = "";
			String RequestURL = property.getProperty("chikka_url");
			
			Data = ("message_type=" + URLEncoder.encode(sms.getMesssageType(), "UTF-8"));
			Data += ("&mobile_number=63" + URLEncoder.encode(sms.getMobileNumber(), "UTF-8"));
			Data += ("&shortcode=" + URLEncoder.encode(sms.getShortCode(), "UTF-8"));
			Data += ("&message_id=" + URLEncoder.encode(sms.getMessageId(), "UTF-8"));
			Data += ("&message=" + URLEncoder.encode(sms.getMessage(), "UTF-8"));
			Data += ("&client_id=" + URLEncoder.encode(sms.getClientid(), "UTF-8"));
			Data += ("&secret_key=" + URLEncoder.encode(sms.getSecretkey(), "UTF-8"));
			System.out.println(Data);
			URL Address = new URL(RequestURL);
	
			HttpURLConnection Connection = (HttpURLConnection) Address.openConnection();
			Connection.setRequestMethod("POST");
			Connection.setDoInput(true);
			Connection.setDoOutput(true);
	
			DataOutputStream Output;
			Output = new DataOutputStream(Connection.getOutputStream());
			Output.writeBytes(Data);
			Output.flush();
			Output.close();
	
			BufferedReader Input = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			StringBuffer ResponseBuffer = new StringBuffer();
			String InputLine;
	
			while ((InputLine = Input.readLine()) != null)
			{
			ResponseBuffer = ResponseBuffer.append(InputLine);
			ResponseBuffer = ResponseBuffer.append("\n\n\n");
			}
	
			Response.replace(0, 0, ResponseBuffer.toString());
			String ResultCode = Response.substring(0, 4);
			System.out.println(ResultCode);
			Input.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static String generateMessageId() {
		return RandomStringUtils.randomNumeric(32);
	}
	
	public static int generateID() {
		
		return Integer.parseInt(RandomStringUtils.randomNumeric(6));
		  
	}
	
	public static void sendMail(String user_email) {
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication("adventjava@gmail.com", "advancejavaee");
		email.setDebug(true);
		email.setStartTLSEnabled(true);
		email.setSmtpPort(587);
		 
		 try {
				email.addTo(user_email);
			
		  email.setFrom("adventjava@gmail.com", "Load2Go");
		  email.setSubject("Thank you for registering.");
		  email.setHtmlMsg("<h1>Welcome to Load2Go.</h1>"
				  + "<p>Click <a href='http://localhost:8080/testJav/activate?email=" + user_email + 
				  "'>here</a> to activate your account.</p>");
		  email.send();
		  } catch (EmailException e) {
				
				e.printStackTrace();
			}
	 
	}
	
public static void sendCode(String user_email, String code) {
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication("adventjava@gmail.com", "advancejavaee");
		email.setDebug(true);
		email.setStartTLSEnabled(true);
		email.setSmtpPort(587);
		 
		 try {
				email.addTo(user_email);
			
		  email.setFrom("adventjava@gmail.com", "Load2Go");
		  email.setSubject("Reset Pass Code ");
		  email.setHtmlMsg("<h1>Forgot Password code</h1><p> Code: " + code + "</p>" + "<br/> <p>"+
		  "Click <a href='http://localhost:8080/testJav/resetPass.jsp'>Here</a> to change your password.</p>");
		  email.send();
		  } catch (EmailException e) {
				
				e.printStackTrace();
			}
	 
	}
	
	
	public static String passwordHasher(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String returnedPass = null;
		md.update(password.getBytes());
		 byte byteData[] = md.digest();
		 
	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	System.out.println("Hex format : " + hexString.toString());
	    	returnedPass = hexString.toString();
	    	return returnedPass;
	}

}
