package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.ICourseRepository;
import br.ufmg.dcc.scholar.repository.IStudentRepository;
@Component
public class StudentService extends BaseService<Student> {
	@Autowired
	private IStudentRepository service;

	@Override
	protected JpaRepository<Student, Long> getEntityRepository() {
		return service;
	}
}
