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
	@Column(name = "courseId")
	private int id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String level;

	@Column
	private String features;

	@Lob
	@Column(nullable = false)
	private String courseOutLine;

	@Column
	private double price;

	@Column
	private String imageUrl = "https://plus.unsplash.com/premium_photo-1680553489384-8e3230dd1073?q=80&w=755&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";

	@Column
	private String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

	@Column
	private int enrolledUser=0;

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
