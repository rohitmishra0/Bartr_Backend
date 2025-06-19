package com.bartr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 🔹 Many enrollments can refer to one course
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseId", nullable = false)
	@JsonBackReference(value = "course-enrollments")
	private Course course;

	// 🔹 Many enrollments can refer to one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learnerId")
	@JsonBackReference(value = "user-enrollments")
	private User learner;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enrollmentDate", nullable = false, updatable = false)
	private Date enrollmentDate;

	// Automatically sets the date before persisting
	@PrePersist
	protected void onCreate() {
		this.enrollmentDate = new Date();
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getLearner() {
		return learner;
	}

	public void setLearner(User learner) {
		this.learner = learner;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}