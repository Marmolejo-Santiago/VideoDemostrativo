package org.gerdoc.api.controller;

import org.gerdoc.api.model.Materia;
import org.gerdoc.api.repository.MateriaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materias")
public class MateriaController extends CrudController<Materia> {
    public MateriaController(MateriaRepository repo) {
        super(repo);
    }
}
