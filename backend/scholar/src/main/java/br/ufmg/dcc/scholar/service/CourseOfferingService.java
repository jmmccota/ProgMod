package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.repository.CourseOfferingRepository;

@Component
public class CourseOfferingService extends BaseService<CourseOffering> {

	@Autowired
	private CourseOfferingRepository courseOfferingRepository;
	
	@Override
	protected JpaRepository<CourseOffering, Long> getEntityRepository() {
		return courseOfferingRepository;
	}
	
	public CourseOffering findByCourseAndSemester(Course course, Semester semester) {
		return courseOfferingRepository.findByCourseAndSemester(course, semester);
	}
}
