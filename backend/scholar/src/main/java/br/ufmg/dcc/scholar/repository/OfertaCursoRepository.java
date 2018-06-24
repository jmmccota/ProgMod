package br.ufmg.dcc.scholar.repository;

import br.ufmg.dcc.scholar.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.Collection;

@Eager
public interface OfertaCursoRepository extends JpaRepository<OfertaCurso, Long> {

    Collection<OfertaCurso> findBySemester(Semester semester);

    Collection<OfertaCurso> findByCourse(Course course);

}
