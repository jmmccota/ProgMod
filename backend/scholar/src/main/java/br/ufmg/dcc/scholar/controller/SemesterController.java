package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.service.SemesterService;

@RestController
@RequestMapping(value = "/semesters")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	Page<Semester> list(@RequestParam int page, @RequestParam int size) {
		return this.semesterService.findAll(PageRequest.of(page, size));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Semester salvar(@RequestBody Semester semester) {
		
		if(semester.getSemester() < 1 || semester.getSemester() > 2) {
			throw new IllegalArgumentException("Semestre deve estar entre 1 e 2");
		}
		
		return this.semesterService.save(semester);
	}
}
