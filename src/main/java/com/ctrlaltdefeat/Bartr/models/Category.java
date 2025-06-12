package com.ctrlaltdefeat.Bartr.models;

import org.springframework.stereotype.Component;

@Component
public class Category {
	private int id;
	private String name;
	private String description;
	private int xp_cost;
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getXp_cost() {
		return xp_cost;
	}
	public void setXp_cost(int xp_cost) {
		this.xp_cost = xp_cost;
	}
	
	
}
