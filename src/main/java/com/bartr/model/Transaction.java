package com.bartr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
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

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public Date getTransactedAt() {
		return transactedAt;
	}

	public void setTransactedAt(Date transactedAt) {
		this.transactedAt = transactedAt;
	}
}
