package org.gerdoc.api.controller;

import org.gerdoc.api.model.Profesor;
import org.gerdoc.api.repository.ProfesorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController extends CrudController<Profesor> {
    public ProfesorController(ProfesorRepository repo) {
        super(repo);
    }
}
