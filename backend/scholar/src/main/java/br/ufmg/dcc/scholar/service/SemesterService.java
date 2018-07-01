package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.repository.ICourseRepository;
import br.ufmg.dcc.scholar.repository.ISemesterRepository;
@Component
public class SemesterService extends BaseService<Semester> {
	@Autowired
	private ISemesterRepository service;

	@Override
	protected JpaRepository<Semester, Long> getEntityRepository() {
		return service;
	}
}
