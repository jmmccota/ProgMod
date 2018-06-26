package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Enrollment extends BaseEntity {
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private CourseOffering courseOffering;
	
	public Enrollment(Student student, CourseOffering courseOffering) {
		this.student = student;
		this.courseOffering = courseOffering;
	}
	
	public Enrollment() { }

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseOffering getCourseOffering() {
		return courseOffering;
	}

	public void setCourseOffering(CourseOffering courseOffering) {
		this.courseOffering = courseOffering;
	}
}
