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
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	private String id;

	@Column(name = "user_id", nullable = false)
	private int user_id;

	@Column(name = "course_id", nullable = false)
	private int course_id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transacted_at")
	private Date transacted_at;
}
