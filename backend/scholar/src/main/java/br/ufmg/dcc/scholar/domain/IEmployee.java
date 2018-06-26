package br.ufmg.dcc.scholar.domain;

interface IEmployee {
	
	public double retrieveSalary();
	
	public void updateSalary(double salary);

	public void insertCourseResponsibility(Course course);
	
	public void deleteCourseResponsibility(Course course);
}
