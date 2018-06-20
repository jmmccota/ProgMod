package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.repository.ProfessorRepository;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.ProfessorService;

@RestController
@RequestMapping(value = "/professors")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;

	@GetMapping
	Page<Professor> list(@RequestParam int page, @RequestParam int size) {
		return this.professorService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/obterTodos")
	Iterable<Professor> obterTodos() {
		return this.professorService.findAll();
	}

	@PostMapping("/salvar")
	Professor salvar(@RequestBody Professor dados) {
		return this.professorService.save(dados);
	}
}
