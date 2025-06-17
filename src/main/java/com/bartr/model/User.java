package com.bartr.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user") // Rename if "user" is a reserved word in your DB
@Getter
@Setter
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}
}
