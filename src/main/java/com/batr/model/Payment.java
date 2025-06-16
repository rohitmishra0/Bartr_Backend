package com.batr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	private String id;

	@Column(name = "user_id", nullable = false)
	private int user_id;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "mode", nullable = false)
	private String mode;

	@Column(name = "xp_purchased")
	private int xp_purchased;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "purchased_at")
	private Date purchased_at;
}
