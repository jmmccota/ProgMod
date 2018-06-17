package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.ufmg.dcc.scholar.domain.Professor;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    Collection<Professor> findByFirstName(String firstName);

    Collection<Professor> findByLastName(String lastName);

}
