package ru.job4j.merkurevsergei.lazyexception;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.merkurevsergei.lazyexception.model.AutoBrand;
import ru.job4j.merkurevsergei.lazyexception.model.AutoModel;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<AutoBrand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            prepare(sf);
            Session session = sf.openSession();
            session.beginTransaction();

            list = session.createQuery("select distinct ab from AutoBrand ab left join fetch ab.models", AutoBrand.class).list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (AutoBrand brand : list) {
            for (AutoModel model : brand.getModels()) {
                System.out.println(model);
            }
        }
    }

    private static void prepare(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();

        AutoModel m1 = new AutoModel("Carina");
        AutoModel m2 = new AutoModel("Vitz");
        AutoModel m3 = new AutoModel("Prius");
        AutoModel m4 = new AutoModel("BMWx5");
        AutoModel m5 = new AutoModel("BMWx6");

        AutoBrand autoBrand1 = new AutoBrand("Toyota");
        AutoBrand autoBrand2 = new AutoBrand("BMW");

        autoBrand1.addModel(m1);
        autoBrand1.addModel(m2);
        autoBrand1.addModel(m3);
        autoBrand2.addModel(m4);
        autoBrand2.addModel(m5);

        session.save(autoBrand1);
        session.save(autoBrand2);

        session.getTransaction().commit();
        session.close();
    }
}
