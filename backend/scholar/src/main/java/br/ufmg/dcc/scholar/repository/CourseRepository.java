package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.cdi.Eager;

import br.ufmg.dcc.scholar.domain.Course;

@Eager
public interface CourseRepository extends JpaRepository<Course, Long> {

    Collection<Course> findByName(String name);

    Collection<Course> findByCode(String code);

}
