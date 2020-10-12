package ru.job4j.merkurevsergei.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            final Author author1 = new Author("Author1");
            final Author author2 = new Author("Author2");

            final Book book1 = new Book("Book1");
            final Book book2 = new Book("Book2");
            final Book book3 = new Book("Book3");

            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);
            author2.addBook(book2);
            author2.addBook(book3);

            session.persist(author1);
            session.persist(author2);

            session.remove(session.get(Author.class, 1));

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
