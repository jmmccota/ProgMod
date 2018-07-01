package br.ufmg.dcc.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Monitoring;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.IMonitoringRepository;

public class MonitoringService extends BaseService<Monitoring> {
	
	@Autowired
	private IMonitoringRepository monitorRepository;
	
	@Override
	protected JpaRepository<Monitoring, Long> getEntityRepository() {
		return monitorRepository;
	}
	
	public Page<Monitoring> findByCourseOffering(CourseOffering courseOffering, Pageable pageable) {
		return monitorRepository.findByCourseOffering(courseOffering, pageable);
	}
	
	public Page<Monitoring> findByStudent(Student student, Pageable pageable) {
		return monitorRepository.findByStudent(student, pageable);
	}
}
