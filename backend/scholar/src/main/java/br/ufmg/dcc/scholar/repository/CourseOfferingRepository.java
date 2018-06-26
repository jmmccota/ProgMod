package br.ufmg.dcc.scholar.repository;

import br.ufmg.dcc.scholar.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.Collection;

@Eager
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    Collection<CourseOffering> findBySemester(Semester semester);

    Collection<CourseOffering> findByCourse(Course course);
    
    CourseOffering findByCourseAndSemesterAndProfessor(Course course, Semester semester, Professor professor);

}
