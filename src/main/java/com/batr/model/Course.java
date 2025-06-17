package com.batr.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	@Id
	// Uncomment if you want auto-generated ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name="categoryId", referencedColumnName = "id")
	private Category category;

	@ManyToOne
	@JoinColumn(name="creatorId", referencedColumnName = "id")
	private User creator;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "level")
	private String level;

	@Column(name = "coursePrice")
	private int coursePrice;
}
