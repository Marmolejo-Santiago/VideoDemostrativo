package org.gerdoc.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ALUMNO")
public class Alumno implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @NotNull
    @Min(0)
    @Column(name = "EDAD", nullable = false)
    private Integer edad;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("99.99")
    @Column(name = "PROMEDIO", nullable = false, precision = 4, scale = 2)
    private BigDecimal promedio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public BigDecimal getPromedio() { return promedio; }
    public void setPromedio(BigDecimal promedio) { this.promedio = promedio; }
}
