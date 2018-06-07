package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.ufmg.dcc.scholar.domain.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	Collection<Student> findByName(String name);
}
