package br.ufmg.dcc.scholar.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Semester extends BaseEntity {

    private Integer year;

    private Integer semester;

    public Semester() { }

    public Semester(Long id, Integer year, Integer semester) {
        super.setId(id);
        this.year = year;
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
