package com.tp2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NOMBRE",unique=true)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrera")
    private Set<Inscripcion> estudiantes;

    public Carrera() {
        this.estudiantes = new HashSet<>();
    }

    public Carrera(String nombre) {
        this();
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Inscripcion> getEstudiantes() {
        return estudiantes;
    }

    public void addInscripciones(Inscripcion i) {
        this.estudiantes.add(i);
    }

    @Override
    public String toString() {
        return nombre +
                " - ID: " + id;
    }
}
