package br.ufmg.dcc.scholar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Monitoring;
import br.ufmg.dcc.scholar.domain.Student;

@Eager
public interface IMonitoringRepository extends JpaRepository<Monitoring, Long> {

    Page<Monitoring> findByCourseOffering(CourseOffering courseOffering, Pageable pageable);
    
    Page<Monitoring> findByStudent(Student student, Pageable pageable);
}
