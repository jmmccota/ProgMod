package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table
public class Professor extends BaseEntity {

    private String firstName;

    private String lastName;

    public Professor() { }

    public Professor(Long id, String firstName, String lastName) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
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

}