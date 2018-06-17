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

@RestController
@RequestMapping(value = "/semesters")
public class SemesterController {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterController(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }


    @GetMapping
    Page<Semester> list(@RequestParam int page, @RequestParam int size) {
        return this.semesterRepository.findAll(PageRequest.of(page, size));
    }
}
