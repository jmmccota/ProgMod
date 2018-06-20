package br.ufmg.dcc.scholar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.cdi.Eager;

import br.ufmg.dcc.scholar.domain.Professor;
@Eager
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Collection<Professor> findByFirstName(String firstName);

    Collection<Professor> findByLastName(String lastName);

}
