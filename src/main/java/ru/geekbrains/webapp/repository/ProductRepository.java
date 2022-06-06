package ru.geekbrains.webapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webapp.model.Product;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private SessionFactory factory;
    private List<Product> catalog;

    @Autowired
    public ProductRepository() {
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
            catalog = findProduct();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(catalog);
    }

    public List<Product> findProduct() {
        List<Product> list;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("select a from Product a", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return list;
    }

    public Product findProductById(Long id) {
        return catalog.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public void addProduct(Product product) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
        catalog = findProduct();
    }

    public void addCost(int id) {
        int count = 1;
        int beginValue = catalog.get(id).getCost();
        beginValue += count;
        catalog.get(id).setCost(beginValue);
    }

    public void subCost(int id) {
        int count = 1;
        int beginValue = catalog.get(id).getCost();
        beginValue -= count;
        catalog.get(id).setCost(beginValue);
    }


}
