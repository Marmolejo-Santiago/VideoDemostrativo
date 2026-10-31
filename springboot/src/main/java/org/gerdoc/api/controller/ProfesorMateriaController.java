package org.gerdoc.api.controller;

import org.gerdoc.api.model.ProfesorMateria;
import org.gerdoc.api.repository.MateriaRepository;
import org.gerdoc.api.repository.ProfesorMateriaRepository;
import org.gerdoc.api.repository.ProfesorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesor-materias")
public class ProfesorMateriaController extends CrudController<ProfesorMateria> {

    private final ProfesorRepository profesores;
    private final MateriaRepository materias;

    public ProfesorMateriaController(ProfesorMateriaRepository repo,
                                     ProfesorRepository profesores,
                                     MateriaRepository materias) {
        super(repo);
        this.profesores = profesores;
        this.materias = materias;
    }

    @Override
    protected void prepare(ProfesorMateria pm) {
        pm.setProfesor(resolver(pm.getProfesor(), profesores, "profesor"));
        pm.setMateria(resolver(pm.getMateria(), materias, "materia"));
    }
}
