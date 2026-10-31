package org.gerdoc.api.controller;

import org.gerdoc.api.model.Alumno;
import org.gerdoc.api.repository.AlumnoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CrudController<Alumno> {
    public AlumnoController(AlumnoRepository repo) {
        super(repo);
    }
}
