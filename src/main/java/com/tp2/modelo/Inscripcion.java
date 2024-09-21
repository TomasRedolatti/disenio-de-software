package com.tp2.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId
    private Estudiante estudiante;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId
    private Carrera carrera;
    @Column(nullable = false)
    private Boolean graduado;
    @Column(nullable = false)
    private LocalDate fechaAlta;
    @Column(nullable = true)
    private LocalDate fechaFin;

    public Inscripcion() {
    }

    public Inscripcion(Estudiante estudiante, Carrera carrera, LocalDate fechaAlta) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaAlta = fechaAlta;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public Boolean getGraduado() {
        return graduado;
    }

    public void setGraduado(Boolean graduado) {
        this.graduado = graduado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
