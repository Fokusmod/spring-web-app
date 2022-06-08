package ru.geekbrains.homework6.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.PrepareDataApp;
import ru.geekbrains.homework6.model.Order;
import ru.geekbrains.homework6.model.Product;

@Component
public class OrderDao {

    SessionFactory factory;


    public void addOrder(Long buyer, Long product) {
        Session session;
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product1 = session.get(Product.class, product);
        Order order = new Order(buyer, product, product1.getCost());
        session.save(order);
        session.getTransaction().commit();
    }

    @Autowired
    public void setFactory(PrepareDataApp prepareDataApp) {
        this.factory = prepareDataApp.getFactory();
    }
}
