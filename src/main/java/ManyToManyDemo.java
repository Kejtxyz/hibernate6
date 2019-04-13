import entity.Article;
import entity.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ManyToManyDemo {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("hibernate-demo");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("From Article");
        List<Article> arts = q.getResultList();
        for (Article a : arts) {
            System.out.println("Tytul artykulu " + a.getTitle());
            System.out.println("Tagi");
            for (Tag t : a.getTags()) {
                System.out.println(t + ",");
            }
            System.out.println();
        }

        q = em.createQuery("From Tag");
        List<Tag> tags = q.getResultList();
        for (Tag t : tags) {
            System.out.println("Artykuly z tagami " + t.getName());
            for (Article a : t.getArticles()) {
                System.out.println(a.getTitle());
            }
        }
    }
}
