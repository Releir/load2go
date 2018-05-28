package com.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.messages.QueryMessages;
import com.model.Reward;
import com.model.Security;
import com.model.Wallet;
import com.beans.RegistrationBean;
import com.beans.UserAccountBean;

public class SubQueries implements QueryMessages{
	
	private static Session session;
	private static Transaction tx;
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getTransactionIDs(SessionFactory conn, String email) {
		
		ArrayList<String> ids = new ArrayList<String>();
		
		session = conn.openSession();
		tx = session.beginTransaction();
		List<Integer> results = (List<Integer>)session.createSQLQuery(SELECT_TRANSACTIONS + "'" + email + "'").list();
		System.out.println("STARTING THE FOR EACH ON TRANSACTION ID");
		for(Integer s : results){
			ids.add(s.toString());
		}
		tx.commit();
		session.flush();
		session.clear();
		return ids;
		
	}
	
	public static void insertRewardID(SessionFactory conn, Integer reward2){
		
		try{
			Reward reward = new Reward(reward2, 0);
			session = conn.openSession();
			tx = session.beginTransaction();
			session.save(reward);
			session.getTransaction().commit();
			session.flush();
			session.clear();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
	}
	
	public static void insertWalletID(SessionFactory conn, Integer code){
		
		try{
			Wallet wallet = new Wallet(code, 0);
			session = conn.openSession();
			tx = session.beginTransaction();
			session.save(wallet);
			session.getTransaction().commit();
			session.flush();
			session.clear();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}

	}
	
	public static void insertSecurityID(SessionFactory conn, Integer code, RegistrationBean user, String date){
		
		try{
			Security security = new Security(code,user.getSecretQuestion(), user.getSecretAnswer(), date);
			session = conn.openSession();
			tx = session.beginTransaction();
			session.save(security);
			session.getTransaction().commit();
			session.flush();
			session.clear();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean checkDuplicateEmail(SessionFactory conn, String email) {
		
		boolean isDuplicated = false;
		
		
		session = conn.openSession();
		tx = session.beginTransaction();
		Query query = session.createSQLQuery(SELECT_FOR_LOGIN);
		query.setParameter("userId", email);
		List results = query.list();
		
        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
            Object[] values = (Object[]) listIterator.next();
            for(int i=0;i<values.length;i++){
            System.out.println(values[i]);
	            if(values.length!=0){
	            	 isDuplicated = true;
	            }
            }
        }
        
        tx.commit();
        session.flush();
        session.clear();
		return isDuplicated;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static UserAccountBean getUserAccount(SessionFactory conn, String email) {
		
		UserAccountBean user = new UserAccountBean();
		user.setEmail(email);
		try{	
			session = conn.openSession();
			tx = session.beginTransaction();
			System.out.println(email);
			Query query = session.createSQLQuery(SELECT_USER_CREDENTIALS);
			query.setParameter("userId", email);
			Query queryReward = session.createSQLQuery(SELECT_REWARD_POINTS);
			queryReward.setParameter("userId", email);
			Query queryWallet = session.createSQLQuery(SELECT_WALLET_BALANCE);
			queryWallet.setParameter("userId", email);
			List results = query.list();
			List rewards = queryReward.list();
			List wallet = queryWallet.list();
			
	        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            user.setName(values[2] + " " + values[3]);
	            }
	        }
	        for(Iterator listIterator = rewards.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            user.setReward((double) values[1]);
	            }
	        }
	        for(Iterator listIterator = wallet.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            user.setBalance((double) values[1]);
	            }
	        }
		}catch(Exception e){
			if(tx != null){
				tx.rollback();
				throw e;
			}
		}
	        tx.commit();
	        session.flush();
	        session.clear();
		
		return user;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static double getWalletBalance( SessionFactory conn, String email) {
		
		double balance = 0;
		try{
			session = conn.openSession();
			tx = session.beginTransaction();
			System.out.println(email);
			Query query = session.createSQLQuery(SELECT_WALLET_BALANCE);
			query.setParameter("userId", email);
			List results = query.list();
			
	        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            System.out.println(values[i]);
	            	balance = Double.parseDouble(values[1].toString());
	            }
	        }
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
        tx.commit();
        session.flush();
        session.clear();
        //session.close();
		
		return balance;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static double getRewardPoints(SessionFactory conn, String email) {
		
		double balance = 0;
		
		try{
			session = conn.openSession();
			tx = session.beginTransaction();
			System.out.println(email);
			Query query = session.createSQLQuery(SELECT_REWARD_POINTS);
			query.setParameter("userId", email);
			List results = query.list();
			
	        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            System.out.println(values[i]);
	            	balance = Double.parseDouble(values[1].toString());
	            }
	        }
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				throw e;
			}
		}
        tx.commit();
        session.flush();
		session.clear();
		return balance;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isActivated(SessionFactory conn, String email ) {
		
		boolean isActivated = false;
		try{
			session = conn.openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(SELECT_STATUS_SECURITYID);
			query.setParameter("userId", email);
			List results = query.list();
			
	        for(Iterator listIterator = results.iterator();listIterator.hasNext();){
	            Object[] values = (Object[]) listIterator.next();
	            for(int i=0;i<values.length;i++){
	            System.out.println(values[i]);
	            	if(values[0].equals('Y')){
	            		isActivated = true;
	            	}
	            }
	        }
		}catch(Exception e){
			if (tx != null) {
				tx.rollback();
	            throw e;
	        }
		}
        tx.commit();
        session.flush();
        session.clear();
		
		return isActivated;
		
	}
}
