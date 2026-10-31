package org.gerdoc.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ALUMNO_MATERIA")
public class AlumnoMateria implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ALUMNO_ID", nullable = false)
    private Alumno alumno;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "MATERIA_ID", nullable = false)
    private Materia materia;

    // Puede ser nulo (materia aun sin calificar), igual que en la tabla
    @DecimalMin("0.00")
    @DecimalMax("99.99")
    @Column(name = "CALIFICACION", precision = 4, scale = 2)
    private BigDecimal calificacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
    public BigDecimal getCalificacion() { return calificacion; }
    public void setCalificacion(BigDecimal calificacion) { this.calificacion = calificacion; }
}
