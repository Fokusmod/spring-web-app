package ru.geekbrains.webapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.webapp.model.Product;
import ru.geekbrains.webapp.repository.preparation.PrepareDataApp;

import java.util.List;

@Component
public class ProductDAO {

    private SessionFactory factory;

    public List<Product> findProduct() {
        List<Product> list;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("select a from Product a", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return list;
    }

    public void addProduct(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }

    @Autowired
    public void setFactory(PrepareDataApp prepareDataApp) {
        this.factory = prepareDataApp.getFactory();
    }
}
