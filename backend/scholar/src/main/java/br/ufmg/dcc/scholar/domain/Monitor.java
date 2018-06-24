package br.ufmg.dcc.scholar.domain;

import java.util.List;

import javax.persistence.OneToMany;

public class Monitor extends Student implements IEmployee{
	private String cpf;

	private double salary;
	
	@OneToMany
	private Course course;	
	
	public Monitor(String cpf, double salary) {
		super();
		this.cpf = cpf;
		this.salary = salary;
	}
	
	@Override
	public double retrieveSalary() {
		return salary;
	}

	@Override
	public void updateSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public void insertCourseResponsibility(Course course) {
		this.course = course;
	}

	@Override
	public void deleteCourseResponsibility(Course course) {
		if(this.course.equals(course)) {
			this.course = null;
		}
	}

	public Course getCourseResponsability() {
		return this.course;
	}
}
