package com.bartr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "courseId", nullable = false)
	private int courseId;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transactedAt")
	private Date transactedAt;
}
