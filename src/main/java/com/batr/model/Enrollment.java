package com.batr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "enrollment")
@Data
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



}
