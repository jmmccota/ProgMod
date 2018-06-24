package br.ufmg.dcc.scholar.domain;

import java.util.List;

interface IEmployee {
	
	public double retrieveSalary();
	
	public void updateSalary(double salary);

	public void insertCourseResponsibility(Course course);
	
	public void deleteCourseResponsibility(Course course);
}
