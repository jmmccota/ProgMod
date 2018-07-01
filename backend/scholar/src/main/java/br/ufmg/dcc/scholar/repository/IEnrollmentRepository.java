package br.ufmg.dcc.scholar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Enrollment;
import br.ufmg.dcc.scholar.domain.Student;

public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {
	
	Enrollment findByStudentAndCourseOffering(Student student, CourseOffering courseOffering);
	
	Page<Enrollment> findByStudent(Student student, Pageable pageable);
	
	Page<Enrollment> findByCourseOffering(CourseOffering courseOffering, Pageable pageable);
}
