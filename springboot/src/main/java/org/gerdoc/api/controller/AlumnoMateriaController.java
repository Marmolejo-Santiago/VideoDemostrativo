package org.gerdoc.api.controller;

import org.gerdoc.api.model.AlumnoMateria;
import org.gerdoc.api.repository.AlumnoMateriaRepository;
import org.gerdoc.api.repository.AlumnoRepository;
import org.gerdoc.api.repository.MateriaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumno-materias")
public class AlumnoMateriaController extends CrudController<AlumnoMateria> {

    private final AlumnoRepository alumnos;
    private final MateriaRepository materias;

    public AlumnoMateriaController(AlumnoMateriaRepository repo,
                                   AlumnoRepository alumnos,
                                   MateriaRepository materias) {
        super(repo);
        this.alumnos = alumnos;
        this.materias = materias;
    }

    @Override
    protected void prepare(AlumnoMateria am) {
        am.setAlumno(resolver(am.getAlumno(), alumnos, "alumno"));
        am.setMateria(resolver(am.getMateria(), materias, "materia"));
    }
}
