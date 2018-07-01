package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Enrollment;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.IEnrollmentRepository;

@Component
public class EnrollmentService extends BaseService<Enrollment> {

	@Autowired
	private IEnrollmentRepository enrollmentRepository;
	
	@Override
	protected JpaRepository<Enrollment, Long> getEntityRepository() {
		return enrollmentRepository;
	}
	
	public Enrollment findByStudentAndCourseOffering(Student student, CourseOffering courseOffering) {
		return this.enrollmentRepository.findByStudentAndCourseOffering(student, courseOffering);
	}
	
	public Page<Enrollment> findByStudent(Student student, PageRequest pageRequest) {
		return this.enrollmentRepository.findByStudent(student, pageRequest);
	}
	
	public Page<Enrollment> findByCourseOffering(CourseOffering courseOffering, PageRequest pageRequest) {
		return this.enrollmentRepository.findByCourseOffering(courseOffering, pageRequest);
	}
}
