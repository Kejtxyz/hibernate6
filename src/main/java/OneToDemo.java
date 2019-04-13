import entity.User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

// heidi - blogex baza // jednej do jednego // jeden do wielu
public class OneToDemo {
    public static void main(String[] args) {
        //Fabryka
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate-demo");
        //Menadżer encji
        EntityManager em = factory.createEntityManager();
        //sesja
        Session se = em.unwrap(Session.class);

        Session session = Persistence.createEntityManagerFactory("hibernate-demo")
                .createEntityManager().unwrap(Session.class);

        se.beginTransaction();
        Query q = se.createQuery("from User");
        List<User> users = q.getResultList();
        users.stream().forEach(a -> System.out.println(a));
        for (User u : users) {
            System.out.println(u + " " + u.getAuthor());
        }
        //users.stream().forEach(System.out::println);
        // tworzymy liste autorow
        List<entity.Author> authors = se.createQuery("From Author").getResultList();
        // wyswietlanie zwykla petla foreach autorow
        for (entity.Author a : authors)
            System.out.println(a + " " + a.getUser());
    }
}