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
@Table(name = "enrollment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

	@Id
	private int id;

	@Column(name = "course_id", nullable = false)
	private int course_id;

	@Column(name = "learner_id", nullable = false)
	private int learner_id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enrollment_date")
	private Date enrollment_date;



}
