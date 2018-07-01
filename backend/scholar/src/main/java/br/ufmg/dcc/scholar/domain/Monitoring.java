package br.ufmg.dcc.scholar.domain;

public class Monitoring implements IEmployee {
	
	private Student student;
	private CourseOffering courseOffering;
	private double salary;
	
	public Monitoring(Student student, CourseOffering courseOffering, double salary) {
		this.student = student;
		this.courseOffering = courseOffering;
		this.salary = salary;
	}
	
	@Override
	public double getSalary() {
		return salary;
	}

	@Override
	public void setSalary(double salary) {
		this.salary = salary;
		
	}

	public CourseOffering getCourseOffering() {
		return courseOffering;
	}

	public void setCourseOffering(CourseOffering courseOffering) {
		this.courseOffering = courseOffering;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
