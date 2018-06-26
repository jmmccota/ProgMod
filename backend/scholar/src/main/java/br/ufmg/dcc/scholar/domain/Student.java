package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Student extends BaseEntity {

	protected String firstName;

	protected String lastName;
	
	protected String registerNumber;
	
	public Student() { }

	public Student(Long id, String firstName, String lastName, String registerNumber) {
		super.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.registerNumber = registerNumber;
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
}