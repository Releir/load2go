package com.messages;

public interface QueryMessages {
	
	
	/** SELECT STATEMENTS */
	
	String SELECT_FOR_LOGIN = "select * from user where user_id= :userId";
	String SELECT_FOR_CODE = "select * from user where user_password= :userPass";
	String SELECT_USER_CREDENTIALS = "select * from user where user_id= :userId";
	String SELECT_STATUS_SECURITYID = "select user_status, security_id from user where user_id= :userId";
	String SELECT_WALLET_BALANCE = "select * from wallet where wallet_id=(select wallet_id from user where user_id= :userId)";
	String SELECT_REWARD_POINTS = "select * from rewards where rewards_id=(select rewards_id from user where user_id= :userId)";
	String SELECT_PREFIXES = "select service_providers_prefix from service_providers where service_providers_id=";
	String SELECT_TRANSACTIONS = "select transaction_id from transaction where user_id=";
	// SELECT_TRANSACTION_CONTENT ends in where so that every transaction ID from SELECT_TRANSACTIONS can be easily added by using OR
	String SELECT_TRANSACTION_CONTENT = "select * from transaction_content where "; 
	
	/** INSERT STATEMENTS */
	
	String INSERT_USER = "insert into user(user_id, user_password, user_first_name, "
			+ "user_last_name, wallet_id, security_id, rewards_id) "
			+ "values(?,?,?,?,(SELECT wallet_id from wallet where wallet_id=?),"
			+ "(SELECT security_id from security where security_id=?),"
			+ "(SELECT rewards_id from rewards where rewards_id=?))";
	String INSERT_SECURITY = "insert into security values(?,?,?,?)";
	String INSERT_WALLET_ID = "insert into wallet(wallet_id) "
			+ "values(?)";
	String INSERT_TRANSACTION = "insert into transaction "
			+ "values(?,?)";
	String INSERT_REWARDS_ID = "insert into rewards(rewards_id) "
			+ "values(?)";
	String INSERT_TRANSACTION_CONTENT = "insert into transaction_content "
			+ "values(";
	
	/** UPDATE STATEMENTS */
	
	String UPDATE_WALLET_BALANCE_PURCHASE = 
			"UPDATE wallet SET wallet_balance=:origWallet - :latestWallet where wallet_id=(select wallet_id from user where user_id=:userId)";
	String UPDATE_REWARD_POINT = "UPDATE rewards SET rewards_points= :realReward where rewards_id=(select rewards_id from user where user_id= :userId)";
	String UPDATE_REWARD_POINT_CLAIM = "UPDATE rewards SET rewards_points= :origReward - :latestReward where rewards_id=(select rewards_id from user where user_id= :userId)";
	String UPDATE_STATUS = "UPDATE user SET user_status='Y' where user_id= :userId";
	String UPDATE_WALLET_BALANCE_RELOAD = "UPDATE wallet SET wallet_balance= :origWallet + :latestWallet where wallet_id=(select wallet_id from user where user_id= :userId)";
	
	String UPDATE_PASS_FORGOT = "UPDATE user SET user_password= :newPass where user_password= :code";
	//For FeedBack
	String INSERT_FEEDBACK = "insert into feedback values(?,?)";
	String INSERT_FBCONTENT = "insert into feedback_content " + "values(?,?,CURDATE())";

}
