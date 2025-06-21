package com.bartr.model;

import jakarta.persistence.*;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;

import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // No-arg constructor
@AllArgsConstructor // All-arg constructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "imageUrl")
	private String imageUrl="https://images.unsplash.com/photo-1746105839114-fbc9c81fcb17?q=80&w=1197&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";

	@Column(name = "xpCost", nullable = false)
	private int xpCost;

	public String getPhotoUrl() {
		return imageUrl;
	}

	public void setPhotoUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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

	public int getXpCost() {
		return xpCost;
	}

	public void setXpCost(int xpCost) {
		this.xpCost = xpCost;
	}
}
