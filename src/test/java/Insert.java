import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Insert {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Estudiante e1 = new Estudiante(11111L, 22222L, "Juan", "Perez", LocalDate.of(2002,10,22), 'M', "Tandil");
        Estudiante e2 = new Estudiante(11112L, 22223L, "Laura", "Diaz", LocalDate.of(2000,1,2), 'F', "Azul");
        Estudiante e3 = new Estudiante(11113L, 22224L, "Pedro", "Gonzalez", LocalDate.of(1999,12,2), 'M', "Olavarria");
        Estudiante e4 = new Estudiante(11114L, 22225L, "Josefina", "Hernandez", LocalDate.of(2002,11,22), 'X', "Tandil");

        Carrera c1 = new Carrera("Ingenieria de Sistemas");
        Carrera c2 = new Carrera("Turismo");
        Carrera c3 = new Carrera("Veterinaria");

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);


        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
