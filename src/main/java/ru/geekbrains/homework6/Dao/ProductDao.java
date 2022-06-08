package ru.geekbrains.homework6.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.PrepareDataApp;
import ru.geekbrains.homework6.model.Buyer;
import ru.geekbrains.homework6.model.Product;

import java.util.List;

@Component
public class ProductDao {

    private SessionFactory factory;


    public SessionFactory getFactory() {
        return factory;
    }

    @Autowired
    public void setFactory(PrepareDataApp prepareDataApp) {
        this.factory = prepareDataApp.getFactory();
    }

    public List<Buyer> getBuyerList(Long id) {
        Session session;
        List<Buyer> list;

        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        list = product.getBuyersList();
        session.getTransaction().commit();
        return list;
    }

    public void setPrice(Long id) {
        Session session;
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        System.out.println("Before cost = " + product.getCost());
        product.setCost(product.getCost() + 50);
        session.saveOrUpdate(product);
        session.getTransaction().commit();

        price(id);
    }

    public void price(Long id) {
        Session session;
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        System.out.println("Current Price = " + product.getCost());
        session.getTransaction().commit();
    }


}
