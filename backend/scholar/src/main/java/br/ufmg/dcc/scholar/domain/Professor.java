package br.ufmg.dcc.scholar.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Professor extends BaseEntity implements IEmployee, IResearcher {

    private String firstName;

    private String lastName;
    
    private double salary;
    
    private String expertiseArea;    

	@OneToMany
	private List<Course> courseResponsibility;
    
	public Professor() { }

    public Professor(Long id, String firstName, String lastName, String expertiseArea) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.expertiseArea = expertiseArea;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
	public void setExpertiseArea(String expertiseArea) {
		this.expertiseArea = expertiseArea;
	}
	
	@Override
	public String retrieveExpertiseArea() {
		return this.expertiseArea;
	}

	@Override
	public void insertCourseResponsibility(Course course) {
		/*if(this.courseResponsibility.size() < 2) {
			this.courseResponsibility.add(course);
		}
		else {
			// TODO Criar exceção
			System.out.println("Numero de cursos excedidos para o professor");
		}*/
		
	}

	@Override
	public void deleteCourseResponsibility(Course course) {
		/*if(this.courseResponsibility.contains(course)) {
			this.courseResponsibility.remove(course);
		}*/
	}

	public List<Course> retrieveCoursesResponsability() {
		//return courseResponsibility;
		return null;
	}
}