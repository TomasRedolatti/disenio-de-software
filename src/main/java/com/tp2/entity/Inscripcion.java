package com.tp2.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ESTUDIANTE", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CARRERA", nullable = false)
    private Carrera carrera;

    @Column(name="GRADUADO", nullable = false)
    private Boolean graduado;

    @Column(name="FECHA_ALTA", nullable = false)
    private LocalDate fechaAlta;

    @Column(name="FECHA_FIN", nullable = true)
    private LocalDate fechaFin;

    public Inscripcion() {
        super();
        this.graduado = false;
    }

    public Inscripcion(Estudiante estudiante, Carrera carrera, LocalDate fechaAlta) {
        this();
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaAlta = fechaAlta;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Inscripcion [ID: " + id +
                ", Estudiante: " + estudiante +
                ", Carrera: " + carrera +
                ", graduado:" + this.graduado + "]";
    }

}
