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
    
	@OneToMany
	private List<Course> courseResponsibility;

	private String cpf;

	private String expertiseArea;
    
	public Professor() { }

    public Professor(Long id, String firstName, String lastName, String cpf) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.setCpf(cpf);
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
	public double getSalary() {		
		return salary;
	}

	@Override
	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public void setExpertiseArea(String expertiseArea) {
		this.expertiseArea = expertiseArea;
	}

	@Override
	public String getExpertiseArea() {
		return expertiseArea;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}