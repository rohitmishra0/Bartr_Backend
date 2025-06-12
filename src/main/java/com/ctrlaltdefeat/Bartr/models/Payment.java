package com.ctrlaltdefeat.Bartr.models;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Payment {
	private String id;
	private int user_id;
	private int amount;
	private String mode;
	private int xp_purchased;
	private Date purchased_at;
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getXp_purchased() {
		return xp_purchased;
	}
	public void setXp_purchased(int xp_purchased) {
		this.xp_purchased = xp_purchased;
	}
	public Date getPurchased_at() {
		return purchased_at;
	}
	public void setPurchased_at(Date purchased_at) {
		this.purchased_at = purchased_at;
	}
}
