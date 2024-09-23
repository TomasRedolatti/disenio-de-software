import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class Matricular {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Estudiante e1 = em.find(Estudiante.class, 11111L);
        Carrera c1 = em.find(Carrera.class, 4);

        Set<Inscripcion> inscripciones = new HashSet<>();
        inscripciones = c1.getEstudiantes();
        //System.out.println(inscripciones);
        inscripciones.forEach(x -> System.out.println(x));

        e1.matricular(c1);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
