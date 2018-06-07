package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Student {
	
	@javax.persistence.Id
	@GeneratedValue
	private Long id;
	
	private String name; 
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
