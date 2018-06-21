package br.ufmg.dcc.scholar.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@IdClass(OfertaCurso.class)
public class OfertaCurso implements Serializable {

    @Id
    @ManyToOne
    //@JoinColumn(name="professor_id")
    private Professor professor;

    @Id
    @ManyToOne
    //@JoinColumn(name="semester_id")
    private Semester semester;

    @Id
    @ManyToOne
    //@JoinColumn(name="course_id")
    private Course course;


}