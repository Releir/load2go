package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="rewards")
public class Reward {
	

	
	@Id
	@Column(name="rewards_id" ,nullable = false)
	private Integer rewards_id;
	
	@Column (name="rewards_points" ,nullable = false)
	private double rewards_points;
	
	public Reward(Integer rewards_id, double rewards_points){
		this.rewards_id = rewards_id;
		this.rewards_points = rewards_points;
		
	}
	
	
	public Integer getRewards_id() {
		return rewards_id;
	}
	public void setRewards_id(Integer rewards_id) {
		this.rewards_id = rewards_id;
	}
	public double getRewards_points() {
		return rewards_points;
	}
	public void setRewards_points(double rewards_points) {
		this.rewards_points = rewards_points;
	}
}
