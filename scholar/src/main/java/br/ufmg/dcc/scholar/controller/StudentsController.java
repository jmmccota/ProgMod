package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.StudentRepository;

@RestController
@RequestMapping(value = "/students")
public class StudentsController {
	
	private final StudentRepository studentRepository;
		
	@Autowired
	public StudentsController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	@GetMapping
	Page<Student> list(@RequestParam int page, @RequestParam int size) {
		return this.studentRepository.findAll(PageRequest.of(page, size));
	}
}
