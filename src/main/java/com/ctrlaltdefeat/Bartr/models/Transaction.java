package com.ctrlaltdefeat.Bartr.models;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Transaction {
	private String id;
	private int user_id;
	private int course_id;
	private String type;
	private int amount;
	private Date transacted_at;
	
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
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getTransacted_at() {
		return transacted_at;
	}
	public void setTransacted_at(Date transacted_at) {
		this.transacted_at = transacted_at;
	}
}
