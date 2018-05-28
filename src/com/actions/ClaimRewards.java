package com.actions;


import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.beans.TransactionBean;
import com.db.HibernateConnection;
import com.db.Queries;
import com.db.SubQueries;
import com.opensymphony.xwork2.ActionSupport;

public class ClaimRewards extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap<String, Object>) map;
		
	}
	
	public String execute() {
		
		SessionFactory conn = HibernateConnection.getSessionFactory();
		Queries.claimReward((TransactionBean)sessionMap.get("rewards_transaction"), sessionMap.get("currentUser").toString());
		sessionMap.put("user_details", SubQueries.getUserAccount(conn, sessionMap.get("currentUser").toString()));
		System.out.println("SUCCESSFUL TRANSACTION.");
		return SUCCESS;
	}
}
