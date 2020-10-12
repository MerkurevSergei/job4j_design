package ru.job4j.merkurevsergei.tomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.merkurevsergei.tomany.model.Brand;
import ru.job4j.merkurevsergei.tomany.model.Model;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.agent.builder.AgentBuilder.Default.of;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model m1 = new Model("Carina");
            session.save(m1);
            Model m2 = new Model("Vitz");
            Model m3 = new Model("Prius");
            Model m4 = new Model("Avensis");
            Model m5 = new Model("Corona");

            Brand brand = new Brand("Toyota");

            brand.setModels(new ArrayList<>(List.of(m2, m3, m4)));
            brand.addModel(session.load(Model.class, 1));
            brand.addModel(m5);

            session.save(brand);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
