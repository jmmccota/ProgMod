package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CourseOffering extends BaseEntity {

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private Course course;
    
    public CourseOffering(Semester semester, Course course) {
    	this.semester = semester;
    	this.course = course;
    }
    
    public CourseOffering() { }
}