package br.ufmg.dcc.scholar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.domain.Student;
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
	Course salvar(@RequestBody Course data) {
		return this.courseService.save(data);
	}
	
	@GetMapping("/{id}")
	Course retriveCourse(@PathVariable long id) {
		return this.courseService.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	void deleteCourse(@PathVariable long id) {
		this.courseService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void updateCourse(@RequestBody Course data, @PathVariable long id) {
		if(this.courseService.findOne(id) != null) {
			data.setId(id);
			this.courseService.save(data);
		}
	}
}
