package com.tp2;

import com.tp2.Repositories.RepositoryFactory;
import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class app {
    public static void main(String[] args) {

        RepositoryFactory.getInstance("carreras");

        Estudiante e1 = new Estudiante(Long.valueOf(11111L), Long.valueOf(22222L), "Juan", "Perez", LocalDate.of(2002,10,22), 'M', "Tandil");
        Estudiante e2 = new Estudiante(Long.valueOf(11112L), Long.valueOf(22223L), "Laura", "Diaz", LocalDate.of(2000,1,2), 'F', "Azul");
        Estudiante e3 = new Estudiante(Long.valueOf(11113L), Long.valueOf(22224L), "Pedro", "Gonzalez", LocalDate.of(1999,12,2), 'M', "Olavarria");
        Estudiante e4 = new Estudiante(Long.valueOf(11114L), Long.valueOf(22225L), "Josefina", "Hernandez", LocalDate.of(2002,11,22), 'X', "Tandil");

        Carrera c1 = new Carrera("Ingenieria de Sistemas");
        Carrera c2 = new Carrera("Turismo");
        Carrera c3 = new Carrera("Veterinaria");


        //Dar de alta estudiantes
        Estudiante e;
        e=RepositoryFactory.get_repositorio_estudiante().persist(e1);
        System.out.println(e);
        e=RepositoryFactory.get_repositorio_estudiante().persist(e2);
        System.out.println(e);
        e=RepositoryFactory.get_repositorio_estudiante().persist(e3);
        System.out.println(e);
        e=RepositoryFactory.get_repositorio_estudiante().persist(e4);
        System.out.println(e);

/*
        //Dar de alta carreras
        Carrera c;
        c=RepositoryFactory.get_repositorio_carrera().persist(c1);
        System.out.println(c);
        c=RepositoryFactory.get_repositorio_carrera().persist(c2);
        System.out.println(c);
        c=RepositoryFactory.get_repositorio_carrera().persist(c3);
        System.out.println(c);
 */


        //Matricular un estudiante en una carrera
        Inscripcion i1;
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e1, c1);
        System.out.println(i1);
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e1, c2);
        System.out.println(i1);
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e2, c1);
        System.out.println(i1);

        //Matricular una lista de estudiantes a una carrera
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(e1);
        estudiantes.add(e2);
        estudiantes.add(e3);
        estudiantes.add(e4);
        Set<Inscripcion> inscripciones = RepositoryFactory.get_repositorio_carrera().matricularEstudiantes(estudiantes, c3);
        inscripciones.forEach(System.out::println);




        RepositoryFactory.cerrar_conexion();
    }
}
