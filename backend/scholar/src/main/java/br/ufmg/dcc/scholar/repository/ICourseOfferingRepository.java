package br.ufmg.dcc.scholar.repository;

import br.ufmg.dcc.scholar.domain.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.Collection;

@Eager
public interface ICourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    Collection<CourseOffering> findBySemester(Semester semester);

    Collection<CourseOffering> findByCourse(Course course);
    
    CourseOffering findByCourseAndSemesterAndProfessor(Course course, Semester semester, Professor professor);
    
    Page<CourseOffering> findBySemester(Semester semester, Pageable pageable);

}
