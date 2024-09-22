import com.tp2.modelo.Carrera;
import com.tp2.modelo.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Matricular {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Estudiante e1 = em.find(Estudiante.class, 11111L);
        Carrera c1 = em.find(Carrera.class, 1);

        e1.matricular(c1);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
