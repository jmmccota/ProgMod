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

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.repository.CourseRepository;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    Page<Course> list(@RequestParam int page, @RequestParam int size) {
        return this.courseRepository.findAll(PageRequest.of(page, size));
    }
    
    @GetMapping("/obterTodos")
    Iterable<Course> obterTodos() {
        return this.courseRepository.findAll();
    }
    
    @PostMapping("/salvar")
    Course salvar(@RequestBody Course dados) {
        return this.courseRepository.save(dados);
    }
}
