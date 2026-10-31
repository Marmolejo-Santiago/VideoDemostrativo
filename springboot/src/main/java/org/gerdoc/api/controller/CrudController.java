package org.gerdoc.api.controller;

import jakarta.validation.Valid;
import org.gerdoc.api.model.HasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Operaciones CRUD comunes. Cada catalogo hereda de aqui y solo define su ruta (@RequestMapping).
 * GET /  GET /{id}  POST /  PUT /{id}  DELETE /{id}
 */
public abstract class CrudController<T extends HasId> {

    protected final JpaRepository<T, Long> repo;

    protected CrudController(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    /** Gancho para completar o validar relaciones antes de guardar. */
    protected void prepare(T entity) {
    }

    @GetMapping
    public List<T> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public T obtener(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> noEncontrado(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T crear(@Valid @RequestBody T body) {
        body.setId(null);
        prepare(body);
        return repo.save(body);
    }

    @PutMapping("/{id}")
    public T actualizar(@PathVariable Long id, @Valid @RequestBody T body) {
        if (!repo.existsById(id)) {
            throw noEncontrado(id);
        }
        body.setId(id);
        prepare(body);
        return repo.save(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw noEncontrado(id);
        }
        // Si hay registros que dependen de este, la BD lo rechaza y el
        // ApiExceptionHandler responde 409 Conflict.
        repo.deleteById(id);
    }

    /** Busca la entidad referenciada (por su id) o responde 400 si no existe. */
    protected static <R extends HasId> R resolver(R ref, JpaRepository<R, Long> repo, String nombre) {
        if (ref == null || ref.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Falta " + nombre + ".id");
        }
        return repo.findById(ref.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No existe " + nombre + " con id " + ref.getId()));
    }

    private ResponseStatusException noEncontrado(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el registro con id " + id);
    }
}
