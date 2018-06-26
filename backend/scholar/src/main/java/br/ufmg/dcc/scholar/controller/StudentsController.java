package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.service.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentsController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	Page<Student> list(@RequestParam int page, @RequestParam int size) {
		return this.studentService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/obterTodos")
	@ResponseStatus(HttpStatus.OK)
	Iterable<Student> obterTodos() {
		return this.studentService.findAll();
	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	Student salvar(@RequestBody Student data) {
		return this.studentService.save(data);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Student retrieveStudent(@PathVariable long id) {
		Student student = this.studentService.findOne(id);
		return student;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable long id) {
		this.studentService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateStudent(@RequestBody Student data, @PathVariable long id) {
		// TODO validar se nao houver student com esse id
		if(this.studentService.findOne(id) != null) {
			data.setId(id);
			this.studentService.save(data);
		}
	}
}
