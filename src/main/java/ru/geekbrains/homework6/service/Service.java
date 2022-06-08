package ru.geekbrains.homework6.service;
//
//
//Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать
////   список покупателей этого товара;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.Dao.BuyerDao;
import ru.geekbrains.homework6.Dao.OrderDao;
import ru.geekbrains.homework6.Dao.ProductDao;
import ru.geekbrains.homework6.model.Buyer;
import ru.geekbrains.homework6.model.Product;

import java.util.List;

@Component
public class Service {

    private BuyerDao buyerDao;
    private ProductDao productDao;

    private OrderDao orderDao;

    public void getCartBuyers(Long id) {
        List<Product> cart = buyerDao.getCart(id);
        for (int i = 0; i < cart.size(); i++) {
            System.out.println("Product = " + cart.get(i).getTitle() + " " + cart.get(i).getCost());
        }

    }

    public void getBuyers(Long id) {
        List<Buyer> buyers = productDao.getBuyerList(id);
        for (int i = 0; i < buyers.size(); i++) {
            System.out.println("Buyers = " + buyers.get(i).getName());
        }
    }


    @Autowired
    public void setBuyerDao(BuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setPrice(long id) {
        productDao.setPrice(id);
    }

    public void addOrder(Long buyer, Long product) {
        orderDao.addOrder(buyer,product);

    }
}
