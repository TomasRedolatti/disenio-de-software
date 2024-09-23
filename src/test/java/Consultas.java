import com.tp2.entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Consultas {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query1 = "SELECT e FROM Estudiante e" +
                "ORDER BY e.dni";

        List<Estudiante> estudiantes = em.createQuery("from Estudiante", Estudiante.class).getResultList();
    }
}
