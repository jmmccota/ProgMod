package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.repository.ProfessorRepository;

@RestController
@RequestMapping(value = "/professors")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping
    Page<Professor> list(@RequestParam int page, @RequestParam int size) {
        return this.professorRepository.findAll(PageRequest.of(page, size));
    }
}
