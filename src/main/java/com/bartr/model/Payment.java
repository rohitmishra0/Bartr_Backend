package com.bartr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "mode", nullable = false)
	private String mode;

	@Column(name = "xpPurchased")
	private int xpPurchased;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "purchasedAt")
	private Date purchasedAt;

	@PrePersist
	protected void onCreate() {
		this.purchasedAt = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getXpPurchased() {
		return xpPurchased;
	}

	public void setXpPurchased(int xpPurchased) {
		this.xpPurchased = xpPurchased;
	}

	public Date getPurchasedAt() {
		return purchasedAt;
	}

	public void setPurchasedAt(Date purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
}
