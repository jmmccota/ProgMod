package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.repository.ICourseRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseService extends BaseService<Course> {
	@Autowired
	private ICourseRepository pedidoRepository;

	@Override
	protected JpaRepository<Course, Long> getEntityRepository() {
		return pedidoRepository;
	}
}
