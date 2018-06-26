package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CourseOffering extends BaseEntity {

	@ManyToOne
    private Semester semester;

    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Professor professor;
    
    public CourseOffering(Semester semester, Course course, Professor professor) {
    	this.semester = semester;
    	this.course = course;
    	this.professor = professor;
    }
    
    public CourseOffering() { }
    
    public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}