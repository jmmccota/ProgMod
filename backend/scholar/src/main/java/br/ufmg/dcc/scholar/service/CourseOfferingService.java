package br.ufmg.dcc.scholar.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.repository.ICourseOfferingRepository;

@Component
public class CourseOfferingService extends BaseService<CourseOffering> {

	@Autowired
	private ICourseOfferingRepository courseOfferingRepository;
	
	@Override
	protected JpaRepository<CourseOffering, Long> getEntityRepository() {
		return courseOfferingRepository;
	}
	
	public CourseOffering findByCourseAndSemesterAndProfessor(Course course, Semester semester, Professor professor) {
		return courseOfferingRepository.findByCourseAndSemesterAndProfessor(course, semester, professor);
	}
	
	public CourseOffering findByCourseAndSemester(Course course, Semester semester) {
		return courseOfferingRepository.findByCourseAndSemester(course, semester);
	}
	
	public Collection<CourseOffering> findBySemesterAndProfessor(Semester semester, Professor professor) {
		return courseOfferingRepository.findBySemesterAndProfessor(semester, professor);
	}
	
	
	public Page<CourseOffering> findBySemester(Semester semester, Pageable pageable){
		return courseOfferingRepository.findBySemester(semester, pageable);
	}
}
