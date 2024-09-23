package com.tp2.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estudiante {
    @Id
    @Column(name="LU")
    private Long LU;

    @Column(name="DNI", unique=true, nullable=false)
    private Long dni;

    @Column(name="NOMBRE", nullable=false)
    private String nombre;

    @Column(name="APELLIDO", nullable=false)
    private String apellido;

    @Column(name="FECHA_NACIMIENTO", nullable=false)
    private LocalDate fechaNacimiento;

    @Column(name="GENERO", nullable=false)
    private Character genero;

    @Column(name="CIUDAD_RESIDENCIA", nullable=false)
    private String residencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Set<Inscripcion> carreras;

    public Estudiante() {
        super();
        this.carreras = new HashSet<>();
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

    public Set<Inscripcion> getCarreras() {
        return carreras;
    }

    public void matricular(Carrera c) {
        this.carreras.add(new Inscripcion(this, c, LocalDate.now()));

    }

    public void setEgresado() {
        //Vemos si hay que pasar insicripcion o carrera
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - LU: "+ LU;
    }
}
