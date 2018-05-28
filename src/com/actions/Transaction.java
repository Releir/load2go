package com.actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.beans.SMSBean;
import com.beans.TransactionBean;
import com.db.HibernateConnection;
import com.db.Queries;
import com.db.SubQueries;
import com.helper.ExternalProcesses;
import com.helper.SMSAssembler;
import com.messages.ErrorMessages;
import com.opensymphony.xwork2.ActionSupport;

public class Transaction extends ActionSupport implements SessionAware, ErrorMessages{
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap;  
	private String purchaseType;
	private String ccNumber;
	
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute() {
		
		if (validation().equals(SUCCESS)) {
			return SUCCESS;
		}
		else {
			
			return ERROR;
		}
	}
	
	@SuppressWarnings("unused")
	public String validation() {
		
		SessionFactory conn = HibernateConnection.getSessionFactory();
		
		if (getPurchaseType().equals("reloadWalletViaPaypal")) {
			
			TransactionBean transaction = (TransactionBean) sessionMap.get("wallet_transaction");
			
			Queries.reloadWallet(sessionMap.get("currentUser").toString(), transaction);
			
			sessionMap.put("user_details", SubQueries.getUserAccount(conn, sessionMap.get("currentUser").toString()));
			addActionMessage("Transaction Complete. Thank you!");
			 return SUCCESS;
		}
		
		else if (getPurchaseType().equals("purchaseLoad")) {
			
			TransactionBean transaction = (TransactionBean) sessionMap.get("transaction");
			
			int transactionId = Queries.insertTransaction(sessionMap.get("currentUser").toString(), 
					transaction);
			System.out.println("SUCCESSFUL TRANSACTION.");
			System.out.println("Current user: " + sessionMap.get("currentUser").toString());
			/** SEND SMS*/
			SMSBean sms = SMSAssembler.getInstance(sessionMap,transaction.getAmount(), transactionId);
			StringBuffer Response = new StringBuffer();
			ExternalProcesses.sendSMS(Response, sms);
			sessionMap.put("user_details", SubQueries.getUserAccount(conn, sessionMap.get("currentUser").toString()));
			addActionMessage("Transaction Complete. Thank you!");
			return SUCCESS;
		}
		
		else if (getPurchaseType().equals("reloadWalletViaCreditCard")) {
			System.out.println("CC NUMBER IS " + getCcNumber());
			 try  
			  {  
			    double d = Double.parseDouble(getCcNumber());  
			  }  
			  catch(NumberFormatException nfe)  
			  {  
					addActionError(CC_INVALID);
					return ERROR; 
			  }  
			if (ExternalProcesses.isValid(getCcNumber())) {
				Queries.reloadWallet(sessionMap.get("currentUser").toString(), 
						(TransactionBean) sessionMap.get("wallet_transaction"));
				System.out.println("SUCCESSFUL TRANSACTION.");
				System.out.println("Current user: " + sessionMap.get("currentUser").toString());
				sessionMap.put("user_details", SubQueries.getUserAccount(conn, sessionMap.get("currentUser").toString()));
				addActionMessage("Transaction Complete. Thank you!");
				return SUCCESS;
			}
			else {
				addActionError(CC_INVALID);
				return ERROR;
			}
		}
		
		return SUCCESS;
	}

}
