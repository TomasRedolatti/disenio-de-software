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

        Estudiante e1 = new Estudiante(11111L, 22222L, "Juan", "Perez", LocalDate.of(2002,10,22), 'M', "Tandil");
        Estudiante e2 = new Estudiante(11112L, 22223L, "Laura", "Diaz", LocalDate.of(2000,1,2), 'F', "Azul");
        Estudiante e3 = new Estudiante(11113L, 22224L, "Pedro", "Gonzalez", LocalDate.of(1999,12,2), 'M', "Olavarria");
        Estudiante e4 = new Estudiante(11114L, 22225L, "Josefina", "Hernandez", LocalDate.of(2002,11,22), 'X', "Tandil");
        Estudiante e5 = new Estudiante(11115L, 22226L, "Franco", "Librandi", LocalDate.of(2002,5,22), 'M', "Azul");

        Carrera c1 = new Carrera("Ingenieria de Sistemas");
        Carrera c2 = new Carrera("Turismo");
        Carrera c3 = new Carrera("Veterinaria");
/*
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
        e=RepositoryFactory.get_repositorio_estudiante().persist(e5);

        //Dar de alta carreras
        Carrera c;
        c=RepositoryFactory.get_repositorio_carrera().persist(c1);
        System.out.println(c);
        c=RepositoryFactory.get_repositorio_carrera().persist(c2);
        System.out.println(c);
        c=RepositoryFactory.get_repositorio_carrera().persist(c3);
        System.out.println(c);


        //Matricular un estudiante en una carrera
        Inscripcion i1;
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e1, c1);
        System.out.println(i1);
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e1, c2);
        System.out.println(i1);
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e2, c1);
        System.out.println(i1);
        i1 = RepositoryFactory.get_repositorio_carrera().matricularEstudiante(e5, c3);

        //Matricular una lista de estudiantes a una carrera
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(e1);
        estudiantes.add(e2);
        estudiantes.add(e3);
        estudiantes.add(e4);
        Set<Inscripcion> inscripciones = RepositoryFactory.get_repositorio_carrera().matricularEstudiantes(estudiantes, c3);
        inscripciones.forEach(System.out::println);
 */

        //Traer todos los estudiantes ordenados por nombre
        ArrayList<Estudiante> estudiantesOrdenados = new ArrayList<>();
        estudiantesOrdenados = (ArrayList<Estudiante>) RepositoryFactory.get_repositorio_estudiante().findAllOrderByName();
        estudiantesOrdenados.forEach(System.out::println);

        System.out.println("\n");

        //Recuperar un estudiante en base a su nro de libreta universitaria
        Estudiante eestudianteByLU = RepositoryFactory.get_repositorio_estudiante().findById(11111L);
        System.out.println(eestudianteByLU);

        System.out.println("\n");

        //Recuperar todos los estudiantes en base a su genero
        ArrayList<Estudiante> estudiantesByGenero = new ArrayList<>();
        estudiantesByGenero = (ArrayList<Estudiante>) RepositoryFactory.get_repositorio_estudiante().findAllByGenero('M');
        estudiantesByGenero.forEach(System.out::println);

        System.out.println("\n");

        //Recuperar las carreras con estudiantes inscriptos y ordenarlas segun cantidad de inscriptos
        ArrayList<Carrera> carrerasByInscriptos = new ArrayList<>();
        carrerasByInscriptos = (ArrayList<Carrera>) RepositoryFactory.get_repositorio_carrera().carrerasSegunInscriptos();
        carrerasByInscriptos.forEach(System.out::println);

        System.out.println("\n");

        //Recuperar los estudiantes de una determinada carrera filtrados por ciudad de residencia
        Carrera ingSist = RepositoryFactory.get_repositorio_carrera().findByName("Veterinaria");
        ArrayList<Estudiante> carreraByResidencia = new ArrayList<>();
        carreraByResidencia = (ArrayList<Estudiante>) RepositoryFactory.get_repositorio_carrera()
                .getEstudiantesByResidencia("Azul", ingSist);
        carreraByResidencia.forEach(System.out::println);

        System.out.println("\n");

        //Setear un alumno como egresado
        Carrera carrera = RepositoryFactory.get_repositorio_carrera().findByName("Veterinaria");
        Inscripcion i_egresado =
        RepositoryFactory.get_repositorio_estudiante().setGraduado(e1, carrera, LocalDate.of(2024,9,25));
        System.out.println(i_egresado);
        RepositoryFactory.get_repositorio_estudiante().setGraduado(e2, carrera, LocalDate.of(2025,9,25));

    /*
        Generar un reporte de las carreras, que para cada carrera incluya info de los inscriptos y egresados por año.
        Se deben ordenar las carreras alfabeticamente y presentar los años cronológicamente
    */
        RepositoryFactory.get_repositorio_carrera().getReporteCarreras();

        System.out.println("\n");


        RepositoryFactory.cerrar_conexion();
    }
}
