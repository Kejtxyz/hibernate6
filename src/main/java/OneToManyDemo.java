import entity.Author;
import javafx.scene.media.AudioTrack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Set;

public class OneToManyDemo {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("hibernate-demo");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        // wyswietla wszystkie artykuly
        em.createQuery("From Article").getResultList().stream().forEach(System.out::println);

        // wyswietlenie artukulow dla dnaego autora z paramterami
        Query q = em.createQuery("From Author where nick=:podaj_nick ");
        q.setParameter("podaj_nick", "An");
        // zapytanie parametryczne, wynik zapytania umieszczamyw liscie autors// zagladamy kandemu artorowi i sprawdzmya czy jest rozne od null


        System.out.println("Artykuly dla autora o podanym nicku");
        List<Author> authors = q.getResultList();
        for (Author a : authors) {
            if (a.getArticles() != null) {
                System.out.println("Artykuly autora: " + a.getNick());
                a.getArticles().forEach(System.out::println);
            }
        }
        em.close();
        factory.close();
    }
}
