package ru.geekbrains.homework6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrepareDataApp {

    private SessionFactory factory;

    @Autowired
    public void createFactory() {
        factory = new Configuration()
                .configure("config/ConfigForHomework6.xml")
                .buildSessionFactory();
    }
    @PostConstruct
    public void setData() {
        Session session;

        try {
            String sql = Files.lines(Paths.get("TableForHomework6.sql")).collect(Collectors.joining(" "));
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
