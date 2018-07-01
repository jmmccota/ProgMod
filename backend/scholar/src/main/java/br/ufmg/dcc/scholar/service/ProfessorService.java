package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.repository.ICourseRepository;
import br.ufmg.dcc.scholar.repository.IProfessorRepository;
@Component
public class ProfessorService extends BaseService<Professor> {
	@Autowired
	private IProfessorRepository service;

	@Override
	protected JpaRepository<Professor, Long> getEntityRepository() {
		return service;
	}
}
