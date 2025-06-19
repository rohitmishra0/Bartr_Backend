package com.bartr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "course_id")
	private int id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String level;

	@Column(nullable = false)
	private String features;

	@Lob
	@Column(nullable = false)
	private String courseOutLine;

	@Column(nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "creatorId", nullable = false)
	private User creator;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enrollmentDate", nullable = false, updatable = false)
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "course-enrollments")
	private List<Enrollment> enrollments;
}
