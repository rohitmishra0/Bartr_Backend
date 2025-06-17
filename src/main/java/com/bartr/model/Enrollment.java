package com.bartr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "courseId", nullable = false)
	private int courseId;

	@Column(name = "learnerId", nullable = false)
	private int learnerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enrollmentDate")
	private Date enrollmentDate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getLearnerId() {
		return learnerId;
	}

	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}
