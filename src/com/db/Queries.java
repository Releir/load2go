package com.db;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.beans.LoginBean;
import com.messages.ErrorMessages;
import com.messages.QueryMessages;
import com.model.FeedbackContent;
import com.model.FeedbackTable;
import com.model.Reward;
import com.model.Security;
import com.model.TransactionContent;
import com.model.TransactionTable;
import com.model.User;
import com.model.Wallet;
import com.beans.FeedbackBean;
import com.beans.RegistrationBean;
import com.beans.TransactionBean;
import com.beans.TransactionHistoryBean;
import com.beans.UserAccountBean;
import com.opensymphony.xwork2.ActionSupport;
import com.helper.ExternalProcesses;

public class Queries extends ActionSupport implements QueryMessages, ErrorMessages{
	
	private static final long serialVersionUID = 1L;
	private static SessionFactory conn;
	private static Session sessionHib;
	private static Transaction tx;
	
	
	@SuppressWarnings("rawtypes")
	public String changePassword(String code, String newPass) throws NoSuchAlgorithmException{
		conn = HibernateConnection.getSessionFactory();
		sessionHib = conn.openSession();
		tx = sessionHib.beginTransaction();
		try{
			Query query = sessionHib.createSQLQuery(SELECT_FOR_CODE);
			query.setParameter("userPass", code);
			List results = query.list();
			if(results.isEmpty()){
				sessionHib.flush();
				sessionHib.clear();
				sessionHib.close();
				return INVALID_CODE;
			}else{
				String thisPass = ExternalProcesses.passwordHasher(newPass);
				if(thisPass.equals(code)){
					return PASS_SHOULD_NOT_BE_THE_SAME;
				}else{
					Query queryPass = sessionHib.createSQLQuery(UPDATE_PASS_FORGOT);
					queryPass.setParameter("newPass", thisPass);
					queryPass.setParameter("code", code);
					int updateresult = queryPass.executeUpdate();
					System.out.println("Password change rows affected: " + updateresult);
					tx.commit();
					sessionHib.flush();
					sessionHib.clear();
					sessionHib.close();
				}
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public String forgotPassMail(String email){
		String code = "";
		conn = HibernateConnection.getSessionFactory();
		sessionHib = conn.openSession();
		tx = sessionHib.beginTransaction();
		try{
			Query query = sessionHib.createSQLQuery(SELECT_FOR_LOGIN);
			query.setParameter("userId", email);
			
			List results = query.list();
			if(results.isEmpty()){
				sessionHib.flush();
				sessionHib.clear();
				sessionHib.close();
				return NO_EMAIL_REGISTERED;
			}else{
				for(Iterator listIterator = results.iterator();listIterator.hasNext();){
		            Object[] values = (Object[]) listIterator.next();
		            for(int i=0;i<values.length;i++){
		            	code = values[1].toString();
		            }
				}
				ExternalProcesses.sendCode(email, code);
				tx.commit();
				sessionHib.flush();
				sessionHib.clear();
				sessionHib.close();
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}

		return SUCCESS;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public String processLogin(LoginBean user, SessionMap<String, Object> session) throws Exception{
		
		
			conn = HibernateConnection.getSessionFactory();
			sessionHib = conn.openSession();
			tx = sessionHib.beginTransaction();
			
		try{
			Query query = sessionHib.createSQLQuery(SELECT_FOR_LOGIN);
			query.setParameter("userId" , user.getUsername());
			List results = query.list();
			
			if(results.isEmpty()){
				sessionHib.flush();
				sessionHib.clear();
				sessionHib.close();
				return NOT_REGISTERED;
			}
			
	        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
					if (values[0].equals(user.getUsername()) && values[1].equals(ExternalProcesses.passwordHasher(user.getPassword()))) {
						
						if (SubQueries.isActivated(conn, user.getUsername())) {
							
							/** SET CURRENT USER CREDENTIALS FOR ACCOUNT VIEWING*/
							UserAccountBean user_details = SubQueries.getUserAccount(conn, user.getUsername());
							
							session.put("currentUser", user.getUsername());
							session.put("user_details", user_details);
					        tx.commit();
					        sessionHib.flush();
					        sessionHib.clear();
							return SUCCESS;
						}else{
					        sessionHib.flush();
					        sessionHib.clear();
					        sessionHib.close();
							return NOT_ACTIVATED;
						}
					}else{
				        sessionHib.flush();
				        sessionHib.clear();
				        sessionHib.close();
						return WRONG_CREDENTIALS;
					}
	            
	            }
	        }
		}catch(Exception e){
	           if (tx != null) {
	               tx.rollback();
	               throw e;
	             }
		}
	        System.out.println("Ending this processLogin");
	        tx.commit();
	        sessionHib.flush();
	        sessionHib.clear();
	        return SUCCESS;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<TransactionHistoryBean> getTransactionHistory(String email) {
		
		System.out.println("Starting the getTransactionHistory");
		TransactionHistoryBean transaction;
		ArrayList<TransactionHistoryBean> history = new ArrayList<TransactionHistoryBean>();
		String query = SELECT_TRANSACTION_CONTENT;
		int size = SubQueries.getTransactionIDs(conn, email).size();
		System.out.println(size);
		
		if (size != 0) {
			//ITERATE TRANSACTION IDs TO SELECT IT FROM TRANSSACTION_CONTENT
			for (String id : SubQueries.getTransactionIDs(conn, email)) {
				
				if (id.equals(SubQueries.getTransactionIDs(conn, email).get(size-1))) {
					query += "transaction_id=" + id;
				}else{
					query += "transaction_id=" + id + " or "; 
				}
				
			}
			
			query += " ORDER by transaction_date DESC";
			
			System.out.println(query);
			
			System.out.println("Starting the transaction");
			sessionHib = conn.openSession();
			tx = sessionHib.beginTransaction();
			Query queryTransaction = sessionHib.createSQLQuery(query);
			List results = queryTransaction.list();
			for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            int realValue = values.length / 5;
	            for(int i=0;i<realValue;i++){
	            	transaction = new TransactionHistoryBean();
					transaction.setDate(values[4].toString());
					transaction.setDescription(values[1].toString());
					transaction.setId(values[0].toString());
					transaction.setRewards((String)values[3].toString());
					transaction.setAmount(values[2].toString());
					history.add(transaction);
	            }
			}
			
		}
		
		sessionHib.flush();
		sessionHib.clear();
		
		return history;
	}
	
	public static String processRegistration(RegistrationBean user) throws Exception {
		
		conn = HibernateConnection.getSessionFactory();
		Session session = conn.openSession();
		tx = session.beginTransaction();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(!SubQueries.checkDuplicateEmail(conn, user.getEmail())) {
					/** ASSIGNING IDs*/
					Integer reward = ExternalProcesses.generateID();
					Integer wallet = ExternalProcesses.generateID();
					Integer security = ExternalProcesses.generateID();
					
					Reward rewardOb = new Reward(reward, 0);
					Wallet walletOb = new Wallet(wallet, 0);
					Security securityOb = new Security(security,user.getSecretQuestion(), user.getSecretAnswer(), df.format(new Date()));
					System.out.println("Starting HOOMAN");
					User hooman = new User();
					hooman.setEmail(user.getEmail());
					hooman.setPassword(ExternalProcesses.passwordHasher(user.getPassword()));
					hooman.setFirstName(user.getFirstName());
					hooman.setLastName(user.getLastName());
					System.out.println("Setted Email Pass first and last name");
					hooman.setWallet_id(walletOb);
					System.out.println("Wallet Set");
					hooman.setSecurity_id(securityOb);
					System.out.println("Security Set");
					hooman.setReward_id(rewardOb);
					System.out.println("Reward set");
					
					System.out.println("Saving HOOMAN");
					session.save(hooman);
					System.out.println("I think hooman is safe but still saving");
					tx.commit();
					System.out.println("HOOMAN SAVED YAY!");
					session.flush();
					session.clear();
					ExternalProcesses.sendMail(user.getEmail());

					
					return SUCCESS;
			
			}else {
				return DUPLICATE_EMAIL;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
		return SUCCESS;
	}
	
	public static void activateAccount(String email) {
		
		try{
			conn= HibernateConnection.getSessionFactory();
			sessionHib = conn.openSession();
			tx = sessionHib.getTransaction();
			tx.begin();
			Query queryActivate = sessionHib.createSQLQuery(UPDATE_STATUS);
			queryActivate.setParameter("userId",email);
			int result = queryActivate.executeUpdate();
			tx.commit();
			System.out.println("Activation of " + email + " has affected " + result + " row(s)");
			sessionHib.flush();
			sessionHib.clear();
			sessionHib.close();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
	}
	
	public static void claimReward(TransactionBean transaction, String email) {
		
		int transactionID = ExternalProcesses.generateID();
		
		try{
			sessionHib = conn.openSession();
			tx = sessionHib.beginTransaction();
			TransactionContent tc= new TransactionContent();
			tc.setTransaction_amount(Double.parseDouble(transaction.getAmount()));
			tc.setTransaction_reward_point(Double.parseDouble(transaction.getReward()));
			tc.setTransaction_description(transaction.getDescription());
			tc.setTransaction_id(transactionID);
			tc.setTransaction_date(transaction.getDate());
			sessionHib.save(tc);
			
			TransactionTable tt = new TransactionTable();
			tt.setTransaction_id(transactionID);
			tt.setUser_id(email);
			sessionHib.save(tt);
	
			Query queryRewards = sessionHib.createSQLQuery(UPDATE_REWARD_POINT_CLAIM);
			queryRewards.setParameter("origReward", SubQueries.getRewardPoints(conn, email));
			queryRewards.setParameter("latestReward", Double.parseDouble(transaction.getAmount()));
			queryRewards.setParameter("userId", email);
			int result = queryRewards.executeUpdate();
			System.out.println("THE RESULT OF REWARD IS " + result);
			
			Query queryWallet = sessionHib.createSQLQuery(UPDATE_WALLET_BALANCE_RELOAD);
			queryWallet.setParameter("origWallet", SubQueries.getWalletBalance(conn, email));
			queryWallet.setParameter("latestWallet", Double.parseDouble(transaction.getAmount()));
			queryWallet.setParameter("userId", email);
			int result2 = queryWallet.executeUpdate();
			System.out.println("THE RESULT OF WALLET IS " + result2);
			
			tx.commit();
			sessionHib.flush();
			sessionHib.clear();
		}catch(Exception e){
			if(tx != null){
				tx.rollback();
				throw e;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getNumberPrefixes(String provider) {
		
		conn = HibernateConnection.getSessionFactory();
		ArrayList<String> prefixes = new ArrayList<String>();
		
		try{
		sessionHib = conn.openSession();
		tx = sessionHib.beginTransaction();
		List<String> results = (List<String>)sessionHib.createSQLQuery(SELECT_PREFIXES + "'" + provider + "'").list();
		for(String s : results){
			prefixes.add(s);
		}
		tx.commit();
		sessionHib.flush();
		sessionHib.clear();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
		return prefixes;
	}
	
	public static void reloadWallet(String email, TransactionBean transaction) {
		
		int transactionID = ExternalProcesses.generateID();
		
		try{
			sessionHib = conn.openSession();
			tx = sessionHib.beginTransaction();
			TransactionContent tc= new TransactionContent();
			tc.setTransaction_amount(Double.parseDouble(transaction.getAmount()));
			tc.setTransaction_reward_point(Double.parseDouble(transaction.getReward()));
			tc.setTransaction_description(transaction.getDescription());
			tc.setTransaction_id(transactionID);
			tc.setTransaction_date(transaction.getDate());
			sessionHib.save(tc);
			
			TransactionTable tt = new TransactionTable();
			tt.setTransaction_id(transactionID);
			tt.setUser_id(email);
			sessionHib.save(tt);
	
			double realReward =SubQueries.getRewardPoints(conn, email) + Double.parseDouble(transaction.getReward());
			Query queryRewards = sessionHib.createSQLQuery(UPDATE_REWARD_POINT);
			queryRewards.setParameter("realReward",realReward);
			queryRewards.setParameter("userId", email);
			int result = queryRewards.executeUpdate();
			System.out.println("THE RESULT OF REWARD IS " + result);
			
			Query queryWallet = sessionHib.createSQLQuery(UPDATE_WALLET_BALANCE_RELOAD);
			queryWallet.setParameter("origWallet", SubQueries.getWalletBalance(conn, email));
			queryWallet.setParameter("latestWallet", Double.parseDouble(transaction.getAmount()));
			queryWallet.setParameter("userId", email);
			int result2 = queryWallet.executeUpdate();
			System.out.println("THE RESULT OF WALLET IS " + result2);
			
			tx.commit();
			sessionHib.flush();
			sessionHib.clear();
		}catch(Exception e){
			if(tx != null){
				tx.rollback();
				throw e;
			}
		}
		
	}
	
	public static int insertTransaction(String email, TransactionBean transaction){
		
		conn = HibernateConnection.getSessionFactory();
		
		int transactionID = ExternalProcesses.generateID();
		
		try{
			sessionHib = conn.openSession();
			tx = sessionHib.beginTransaction();
			TransactionContent tc= new TransactionContent();
			tc.setTransaction_amount(Double.parseDouble(transaction.getAmount()));
			tc.setTransaction_reward_point(Double.parseDouble(transaction.getReward()));
			tc.setTransaction_description(transaction.getDescription());
			tc.setTransaction_id(transactionID);
			tc.setTransaction_date(transaction.getDate());
			sessionHib.save(tc);
			
			TransactionTable tt = new TransactionTable();
			tt.setTransaction_id(transactionID);
			tt.setUser_id(email);
			sessionHib.save(tt);
	
			double realReward =SubQueries.getRewardPoints(conn, email) + Double.parseDouble(transaction.getReward());
			Query queryRewards = sessionHib.createSQLQuery(UPDATE_REWARD_POINT);
			queryRewards.setParameter("realReward",realReward);
			queryRewards.setParameter("userId", email);
			int result = queryRewards.executeUpdate();
			System.out.println("THE RESULT OF REWARD IS " + result);
			
			Query queryWallet = sessionHib.createSQLQuery(UPDATE_WALLET_BALANCE_PURCHASE);
			queryWallet.setParameter("origWallet", SubQueries.getWalletBalance(conn, email));
			queryWallet.setParameter("latestWallet", Double.parseDouble(transaction.getAmount()));
			queryWallet.setParameter("userId", email);
			int result2 = queryWallet.executeUpdate();
			System.out.println("THE RESULT OF WALLET IS " + result2);
			
			tx.commit();
			sessionHib.flush();
			sessionHib.clear();
		}catch(Exception e){
			if(tx != null){
				tx.rollback();
				throw e;
			}
		}
		return transactionID;
	}
	
	public static boolean processFeedback(FeedbackBean feed, String email){
		boolean isCompleted = false;
		try{
		conn = HibernateConnection.getSessionFactory();
		sessionHib = conn.openSession();
		tx = sessionHib.beginTransaction();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		int feedbackid = ExternalProcesses.generateID();
		FeedbackContent fc = new FeedbackContent();
		fc.setFeedback_id(feedbackid);
		fc.setFeedback_comment(feed.getFeedback_content());
		fc.setFeedback_date(df.format(date));
		sessionHib.save(fc);
		
		FeedbackTable ft = new FeedbackTable();
		ft.setFeedback_id(feedbackid);
		ft.setUser_id(email);
		sessionHib.save(ft);
		
		tx.commit();
		sessionHib.flush();
		sessionHib.clear();
		
		isCompleted = true;
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
		
		return isCompleted;
	}
}
