package com.bartr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user") // Rename if "user" is a reserved word in your DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Slf4j
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
	private int xp=100;



	@Column(name = "avatarUrl")
	private String avatarUrl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@OneToMany
	@JoinColumn(name = "learnerId", referencedColumnName = "id") // maps to Enrollment.learner_id
	@JsonManagedReference(value = "user-enrollments")
	private List<Enrollment> enrollmentList;

    public int getXp() {
        return xp;
    }

	public void setXp(int xp){
		this.xp = xp;
	}
}
