package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;
import br.ufmg.dcc.scholar.domain.Semester;

@Eager
public interface ISemesterRepository extends JpaRepository<Semester, Long> {

    Collection<Semester> findByYear(Integer year);

    Collection<Semester> findBySemester(Boolean semester);
}
