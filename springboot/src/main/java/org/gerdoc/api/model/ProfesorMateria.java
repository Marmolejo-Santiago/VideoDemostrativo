package org.gerdoc.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PROFESOR_MATERIA")
public class ProfesorMateria implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "PROFESOR_ID", nullable = false)
    private Profesor profesor;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "MATERIA_ID", nullable = false)
    private Materia materia;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
}
