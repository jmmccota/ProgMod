package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
public class Course {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    private String name;

    private String code;

    public Course() { }

    public Course(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Column(name = "COURSE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}