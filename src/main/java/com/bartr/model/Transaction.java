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

	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
	private User user;

	@ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
	private Course course;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transactedAt")
	private Date transactedAt;

	@PrePersist
    protected void onCreate() {
        this.transactedAt = new Date();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourseId(Course course) {
		this.course = course;
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
