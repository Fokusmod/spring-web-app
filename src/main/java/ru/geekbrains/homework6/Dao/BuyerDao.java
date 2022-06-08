package ru.geekbrains.homework6.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.PrepareDataApp;
import ru.geekbrains.homework6.model.Buyer;
import ru.geekbrains.homework6.model.Order;
import ru.geekbrains.homework6.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Component
public class BuyerDao {

    private SessionFactory factory;


    public SessionFactory getFactory() {
        return factory;
    }

    @Autowired
    public void setFactory(PrepareDataApp prepareDataApp) {
        this.factory = prepareDataApp.getFactory();
    }

    public List<Product> getCart(Long id) {
        Session session;
        session = factory.getCurrentSession();
        session.beginTransaction();

        List<Order> ordersList = session.createQuery("FROM Order where buyer_id = " + id).getResultList();
        List<Product> productList = session.createQuery("from Product ").getResultList();
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId().intValue() - o2.getId().intValue();
            }
        });


        List<Product> cart = new ArrayList<>(ordersList.size());
        for (int i = 0; i < ordersList.size(); i++) {

            Product product = new Product();
            product.setId(ordersList.get(i).getProduct_id());
            product.setCost(ordersList.get(i).getCost());

            int number = ordersList.get(i).getProduct_id().intValue();
            String prodTitle = productList.get(number-1).getTitle();
            product.setTitle(prodTitle);
            cart.add(product);
        }
        session.getTransaction().commit();
        return cart;
    }

}




