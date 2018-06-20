package br.ufmg.dcc.scholar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.repository.CourseRepository;
import br.ufmg.dcc.scholar.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	Page<Course> list(@RequestParam int page, @RequestParam int size) {
		return this.courseService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/obterTodos")
	Collection<Course> obterTodos() {
		return this.courseService.findAll();
	}

	@PostMapping("/salvar")
	Course salvar(@RequestBody Course dados) {
		return this.courseService.save(dados);
	}
}
