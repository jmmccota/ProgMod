package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.ProfessorRepository;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.ProfessorService;

@RestController
@RequestMapping(value = "/professors")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	Page<Professor> list(@RequestParam int page, @RequestParam int size) {
		return this.professorService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/obterTodos")
	@ResponseStatus(HttpStatus.OK)
	Iterable<Professor> obterTodos() {
		return this.professorService.findAll();
	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	Professor salvar(@RequestBody Professor data) {
		return this.professorService.save(data);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Professor retrieveProfessor(@PathVariable long id) {
		Professor professor = null;
		try{
			 professor = this.professorService.findOne(id);
		}
		catch(Exception e){
			// Tratar exceção
			// throw new ProfessorNotFoundException("Nao encontrado professor com id "+id);
			System.out.println("Nao encontrado professor com id \"+id");
		}
		
		return professor;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProfessor(@PathVariable long id) {
		this.professorService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateProfessor(@RequestBody Professor data, @PathVariable long id) {
		if(this.professorService.findOne(id) != null) {
			data.setId(id);
			this.professorService.save(data);
		}
	}
}
