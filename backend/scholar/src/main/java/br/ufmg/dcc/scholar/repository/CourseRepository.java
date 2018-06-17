package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.ufmg.dcc.scholar.domain.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    Collection<Course> findByName(String name);

    Collection<Course> findByCode(String code);

}
