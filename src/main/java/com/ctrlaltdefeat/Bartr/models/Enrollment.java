package com.ctrlaltdefeat.Bartr.models;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Enrollment {
	private String id;
	private int course_id;
	private int learner_id;
	private Date enrollment_date;
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getLearner_id() {
		return learner_id;
	}
	public void setLearner_id(int learner_id) {
		this.learner_id = learner_id;
	}
	public Date getEnrollment_date() {
		return enrollment_date;
	}
	public void setEnrollment_date(Date enrollment_date) {
		this.enrollment_date = enrollment_date;
	}
	
}
