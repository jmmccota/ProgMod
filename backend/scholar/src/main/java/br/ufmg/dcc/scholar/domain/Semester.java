package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table
public class Semester extends BaseEntity {

    private Integer year;

    private Boolean semester;

    public Semester() { }

    public Semester(Long id, Integer year, Boolean semester) {
        super.setId(id);;
        this.year = year;
        this.semester = semester;
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
