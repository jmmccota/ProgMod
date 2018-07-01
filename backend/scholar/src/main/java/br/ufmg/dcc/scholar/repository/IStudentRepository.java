package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.cdi.Eager;

import br.ufmg.dcc.scholar.domain.Student;
@Eager
public interface IStudentRepository extends JpaRepository<Student, Long> {
	
	Collection<Student> findByFirstName(String firstName);

	Collection<Student> findByLastName(String lastName);
	
	Collection<Student> findByRegisterNumber(String registerNumber);

}
