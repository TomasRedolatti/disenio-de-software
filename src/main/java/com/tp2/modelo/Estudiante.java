package com.tp2.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {
    @Id
    private Long LU;
    @Column(unique=true, nullable=false)
    private Long dni;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false)
    private String apellido;
    @Column(nullable=false)
    private LocalDate fechaNacimiento;
    @Column(nullable=false)
    private Character genero;
    @Column(nullable=false)
    private String residencia;
    @OneToMany
    private List<Inscripcion> carreras;

    public Estudiante() {
        super();
        this.carreras = new ArrayList<>();
    }

    public Estudiante(Long LU, Long dni, String nombre, String apellido, LocalDate fechaNacimiento, Character genero, String residencia) {
        this();
        this.LU = LU;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.residencia = residencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public void addCarrera(Inscripcion i) {
        this.carreras.add(i);
    }

    public Long getLU() {
        return LU;
    }

    public Long getDni() {
        return dni;
    }

    public List<Inscripcion> getCarreras() {
        return carreras;
    }

    public void matricular(Carrera c) {
        new Inscripcion(this, c, LocalDate.now());
    }

    public void setEgresado() {
        //Vemos si hay que pasar insicripcion o carrera
    }
}
