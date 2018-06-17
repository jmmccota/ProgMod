package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
public class Semester {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    private Integer year;

    private Boolean semester;

    public Semester() { }

    public Semester(Long id, Integer year, Boolean semester) {
        this.id = id;
        this.year = year;
        this.semester = semester;
    }

    @Column(name = "SEMESTER_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getSemester() {
        return semester;
    }

    public void setSemester(Boolean semester) {
        this.semester = semester;
    }
}
