package com.batr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "payment")
@Data
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
}
