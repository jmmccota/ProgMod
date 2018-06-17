package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.ufmg.dcc.scholar.domain.Semester;

public interface SemesterRepository extends PagingAndSortingRepository<Semester, Long> {

    Collection<Semester> findByYear(Integer year);

    Collection<Semester> findBySemester(Boolean semester);
}
