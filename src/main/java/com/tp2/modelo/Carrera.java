package com.tp2.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name",unique=true)
    private String nombre;
    @OneToMany
    private List<Inscripcion> estudiantes;

    public Carrera() {
        this.estudiantes = new ArrayList<>();
    }

    public Carrera(String nombre) {
        this();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Inscripcion> getEstudiantes() {
        return estudiantes;
    }

    public void addInscripciones(Inscripcion i) {
        this.estudiantes.add(i);
    }
}
