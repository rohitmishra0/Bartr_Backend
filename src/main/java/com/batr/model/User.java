package com.batr.model;

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
	@Column(name = "id")
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

	@Column(name = "avatar_url")
	private String avatar_url;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date created_at;

	@OneToMany
	@JoinColumn(name = "learner_id", referencedColumnName = "id") // maps to Enrollment.learner_id
	private List<Enrollment> enrollmentList;
}
