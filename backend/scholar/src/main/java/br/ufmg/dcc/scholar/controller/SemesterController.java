package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.repository.SemesterRepository;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.SemesterService;

@RestController
@RequestMapping(value = "/semesters")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	@Autowired
	public SemesterController(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@GetMapping
	Page<Semester> list(@RequestParam int page, @RequestParam int size) {
		return this.semesterService.findAll(PageRequest.of(page, size));
	}
}
