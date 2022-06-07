package ru.geekbrains.webapp.repository.preparation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class PrepareDataApp {

    private SessionFactory factory;

    @Autowired
    public void createFactory() {
        this.factory = new Configuration()
                .configure("config/hibernate.xml")
                .buildSessionFactory();
    }


    @PostConstruct
    public void init() {

        Session session;

        try {
            String sql = Files.lines(Paths.get("CreateTableProducts.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
