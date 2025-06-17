package com.bartr.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user") // Rename if "user" is a reserved word in your DB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	//@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "xp")
	private int xp;

	@Column(name = "avatarUrl")
	private String avatarUrl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@OneToMany
	@JoinColumn(name = "learnerId", referencedColumnName = "id") // maps to Enrollment.learner_id
	private List<Enrollment> enrollmentList;
}
