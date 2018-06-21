package br.ufmg.dcc.scholar.controller;

import br.ufmg.dcc.scholar.domain.OfertaCurso;
import br.ufmg.dcc.scholar.repository.OfertaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ofertacurso")
public class OfertaCursoController {

    private final OfertaCursoRepository ofertaCursoRepository;

    @Autowired
    public OfertaCursoController(OfertaCursoRepository ofertaCursoRepository) {
        this.ofertaCursoRepository = ofertaCursoRepository;
    }


    @GetMapping
    Page<OfertaCurso> list(@RequestParam int page, @RequestParam int size) {
        return this.ofertaCursoRepository.findAll(PageRequest.of(page, size));
    }
}
